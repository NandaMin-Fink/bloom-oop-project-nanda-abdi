import { api, ApiError } from './api.js';
import { HabitCard } from './habit-card.js';
import { AddHabitModal } from './add-habit-modal.js';
import { toast } from './toast.js';
import * as animate from './animations.js';

const STAGE_ORDER = ['DEAD', 'SEEDLING', 'GROWING', 'MATURE'];

function stageDirection(prev, next) {
    if (prev === next) return 'same';
    return STAGE_ORDER.indexOf(next) > STAGE_ORDER.indexOf(prev) ? 'up' : 'down';
}

export class GardenApp {
    constructor({ gridEl, statsEl, emptyEl, modalRoot }) {
        this.gridEl = gridEl;
        this.statsEl = statsEl;
        this.emptyEl = emptyEl;
        this.modalRoot = modalRoot;
        this.cards = new Map();
        this.wireGridEvents();
    }

    async start() {
        try {
            const snapshot = await api.getGarden();
            this.render(snapshot);
        } catch (err) {
            toast.error('Could not load garden: ' + err.message);
        }
    }

    openAddModal() {
        const modal = new AddHabitModal({
            onSubmit: async (data) => {
                try {
                    const snapshot = await api.addHabit(data);
                    this.render(snapshot);
                    modal.close();
                    toast.info('Planted ' + data.habitName);
                } catch (err) {
                    const msg = err instanceof ApiError ? err.message : 'Could not plant habit';
                    toast.error(msg);
                }
            }
        });
        modal.open(this.modalRoot);
    }

    async waterHabit(name) {
        if (this.isDead(name)) return;
        await this.applyAction(name, () => api.water(name), 'up');
    }

    async missHabit(name) {
        if (this.isDead(name)) return;
        await this.applyAction(name, () => api.miss(name), 'down');
    }

    isDead(name) {
        const card = this.cards.get(name);
        return card && card.data.stage === 'DEAD';
    }

    async deleteHabit(name) {
        if (!confirm(`Delete "${name}"? This cannot be undone.`)) return;
        try {
            const snapshot = await api.remove(name);
            this.render(snapshot);
            toast.info('Removed ' + name);
        } catch (err) {
            const msg = err instanceof ApiError ? err.message : 'Could not delete';
            toast.error(msg);
        }
    }

    async applyAction(name, apiCall, expected) {
        const card = this.cards.get(name);
        if (!card) return;

        try {
            const snapshot = await apiCall();
            const updated = snapshot.habits.find(h => h.habitName === name);
            const { prevStage, nextStage } = card.update(updated);

            this.playFeedback(card, prevStage, nextStage, expected);
            this.render(snapshot);
        } catch (err) {
            const msg = err instanceof ApiError ? err.message : 'Action failed';
            toast.error(msg);
        }
    }

    playFeedback(card, prevStage, nextStage, expected) {
        const dir = stageDirection(prevStage, nextStage);

        if (expected === 'up') {
            animate.bounce(card.plantEl());
            if (nextStage === 'MATURE' && dir === 'up') {
                animate.confetti(card.el);
                toast.info('🎉 Your plant reached maturity!');
            }
        } else if (expected === 'down') {
            animate.wilt(card.el);
            if (nextStage === 'DEAD' && dir === 'down') {
                toast.error('Your plant died — plant a new one');
            }
        }
    }

    render(snapshot) {
        this.renderStats(snapshot);
        this.renderGrid(snapshot);
        this.toggleEmpty(snapshot.habits.length === 0);
    }

    renderStats(snapshot) {
        const count = snapshot.habits.length;
        const mature = snapshot.habits.filter(h => h.stage === 'MATURE').length;
        let longest = 0;
        for (const h of snapshot.habits) {
            if (h.longestStreak > longest) longest = h.longestStreak;
        }
        this.statsEl.innerHTML = count === 0 ? '' : `
            <div class="stat"><span class="stat-label">Habits</span><span class="stat-value">${count}</span></div>
            <div class="stat"><span class="stat-label">Mature</span><span class="stat-value">${mature}</span></div>
            <div class="stat"><span class="stat-label">Best Streak</span><span class="stat-value">${longest}</span></div>
        `;
    }

    renderGrid(snapshot) {
        const seen = new Set();

        for (const habit of snapshot.habits) {
            seen.add(habit.habitName);
            const existing = this.cards.get(habit.habitName);
            if (existing) {
                existing.update(habit);
            } else {
                const card = new HabitCard(habit);
                this.cards.set(habit.habitName, card);
                this.gridEl.appendChild(card.el);
            }
        }

        // remove cards for habits that no longer exist server-side
        for (const [name, card] of this.cards) {
            if (!seen.has(name)) {
                card.el.remove();
                this.cards.delete(name);
            }
        }
    }

    toggleEmpty(isEmpty) {
        this.emptyEl.hidden = !isEmpty;
        this.gridEl.hidden = isEmpty;
        this.statsEl.hidden = isEmpty;
    }

    wireGridEvents() {
        this.gridEl.addEventListener('click', (e) => {
            const btn = e.target.closest('button[data-action]');
            if (!btn) return;
            const card = btn.closest('.habit-card');
            if (!card) return;

            const name = card.dataset.habit;
            const action = btn.dataset.action;

            if      (action === 'water')  this.waterHabit(name);
            else if (action === 'miss')   this.missHabit(name);
            else if (action === 'delete') this.deleteHabit(name);
        });
    }
}
