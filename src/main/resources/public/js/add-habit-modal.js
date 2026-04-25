const SPECIES = [
    { value: 'rose',  label: '🌹 Rose' },
    { value: 'apple', label: '🍎 Apple Tree' },
    { value: 'mango', label: '🥭 Mango Tree' },
];

const PERIODS = [
    { value: 'daily',  label: 'Daily' },
    { value: 'weekly', label: 'Weekly' },
    { value: '12h',    label: 'Twice a day (12h)' },
];

function optionsHtml(items) {
    return items.map(i => `<option value="${i.value}">${i.label}</option>`).join('');
}

export class AddHabitModal {
    constructor({ onSubmit }) {
        this.onSubmit = onSubmit;
        this.el = this.build();
        this.wire();
    }

    build() {
        const wrap = document.createElement('div');
        wrap.className = 'modal-wrap';
        wrap.innerHTML = `
            <div class="modal-backdrop-custom"></div>
            <div class="modal-card">
                <h3>🌱 Plant a new habit</h3>
                <form class="modal-form">
                    <label>
                        Habit name
                        <input class="form-control" name="habitName" required
                               placeholder="Read 20 minutes" autocomplete="off">
                    </label>
                    <label>
                        Plant name
                        <input class="form-control" name="plantName" required
                               placeholder="Morning Rose" autocomplete="off">
                    </label>
                    <label>
                        Species
                        <select class="form-select" name="plantType">
                            ${optionsHtml(SPECIES)}
                        </select>
                    </label>
                    <label>
                        Refresh period
                        <select class="form-select" name="refreshPeriod">
                            ${optionsHtml(PERIODS)}
                        </select>
                    </label>
                    <div class="modal-actions">
                        <button type="button" class="btn btn-outline-secondary" data-action="cancel">Cancel</button>
                        <button type="submit" class="btn btn-primary">Plant it</button>
                    </div>
                </form>
            </div>
        `;
        return wrap;
    }

    wire() {
        const form = this.el.querySelector('form');
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            const data = Object.fromEntries(new FormData(form).entries());
            this.onSubmit(data);
        });
        this.el.querySelector('[data-action="cancel"]').addEventListener('click', () => this.close());
        this.el.querySelector('.modal-backdrop-custom').addEventListener('click', () => this.close());
        document.addEventListener('keydown', this.handleEsc = (e) => {
            if (e.key === 'Escape') this.close();
        });
    }

    open(root) {
        root.appendChild(this.el);
        requestAnimationFrame(() => this.el.classList.add('open'));
        setTimeout(() => {
            const firstInput = this.el.querySelector('input[name="habitName"]');
            if (firstInput) firstInput.focus();
        }, 100);
    }

    close() {
        this.el.classList.remove('open');
        document.removeEventListener('keydown', this.handleEsc);
        setTimeout(() => this.el.remove(), 200);
    }
}
