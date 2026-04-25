import { timeAgo } from './time-ago.js';

// Placeholder emoji-sprite map until real PNG sprites are generated.
const SPRITES = {
    rose:  { SEEDLING: '🌱', GROWING: '🌿', MATURE: '🌹', DEAD: '🥀' },
    apple: { SEEDLING: '🌱', GROWING: '🌿', MATURE: '🍎', DEAD: '🥀' },
    mango: { SEEDLING: '🌱', GROWING: '🌿', MATURE: '🥭', DEAD: '🥀' },
};

const FALLBACK_SPECIES = 'rose';
const FALLBACK_SPRITE = '🌱';

function spriteFor(plantType, stage) {
    const byType = SPRITES[plantType] || SPRITES[FALLBACK_SPECIES];
    return byType[stage] || FALLBACK_SPRITE;
}

export class HabitCard {
    constructor(habitData) {
        this.data = habitData;
        this.el = this.build();
    }

    build() {
        const card = document.createElement('div');
        card.className = 'habit-card';
        card.dataset.habit = this.data.habitName;
        card.innerHTML = `
            <div class="habit-card-header">
                <div>
                    <p class="habit-card-title"></p>
                    <div class="habit-card-plant-name"></div>
                </div>
                <button class="habit-card-delete" data-action="delete" title="Delete habit">×</button>
            </div>
            <div class="plant-display" data-role="plant"></div>
            <div class="habit-card-stats">
                <span class="stage-badge"></span>
                <span class="streak"></span>
            </div>
            <div class="last-watered"></div>
            <div class="habit-card-actions">
                <button class="btn btn-primary btn-sm" data-action="water">💧 Water</button>
                <button class="btn btn-outline-secondary btn-sm" data-action="miss">Skip</button>
            </div>
        `;

        this.refreshDom(card);
        return card;
    }

    update(newData) {
        const prev = this.data;
        this.data = newData;
        this.refreshDom(this.el);
        return { prevStage: prev.stage, nextStage: newData.stage };
    }

    refreshDom(card) {
        const d = this.data;
        const isDead = d.stage === 'DEAD';

        card.classList.toggle('neglected', d.neglected);
        card.classList.toggle('dead', isDead);

        card.querySelector('.habit-card-title').textContent = d.habitName;
        card.querySelector('.habit-card-plant-name').textContent =
            `${d.plantName} · ${d.plantType} · ${d.refreshPeriod || 'daily'}`;

        card.querySelector('[data-role="plant"]').textContent = spriteFor(d.plantType, d.stage);

        const badge = card.querySelector('.stage-badge');
        badge.textContent = d.stage;
        badge.className = 'stage-badge ' + d.stage.toLowerCase();

        card.querySelector('.streak').innerHTML =
            `🔥 ${d.streak} <span class="small">(best ${d.longestStreak})</span>`;

        card.querySelector('.last-watered').textContent =
            'last watered: ' + timeAgo(d.lastCompleted);

        // Once a plant is dead, water/skip shouldn't do anything — only delete is useful.
        const waterBtn = card.querySelector('[data-action="water"]');
        const missBtn  = card.querySelector('[data-action="miss"]');
        waterBtn.disabled = isDead;
        missBtn.disabled  = isDead;
        waterBtn.title = isDead ? 'This plant has died — remove it and plant a new one' : '';
        missBtn.title  = isDead ? 'This plant has died — remove it and plant a new one' : '';
    }

    plantEl() {
        return this.el.querySelector('[data-role="plant"]');
    }
}
