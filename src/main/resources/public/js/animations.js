const CONFETTI_COLORS = ['#4a8c5a', '#6fb77e', '#e8c44a', '#e87e4a', '#4a8cd6', '#c05a5a'];
const CONFETTI_COUNT = 32;
const CONFETTI_LIFESPAN_MS = 1500;
const CARD_ANIM_MS = 500;

function retrigger(el, className) {
    el.classList.remove(className);
    void el.offsetWidth; // force reflow so re-adding the class restarts the animation
    el.classList.add(className);
    setTimeout(() => el.classList.remove(className), CARD_ANIM_MS);
}

export function bounce(el) {
    retrigger(el, 'animate-bounce');
}

export function wilt(el) {
    retrigger(el, 'animate-wilt');
}

export function confetti(originEl) {
    const rect = originEl.getBoundingClientRect();
    const originX = rect.left + rect.width / 2;
    const originY = rect.top + rect.height / 2;

    for (let i = 0; i < CONFETTI_COUNT; i++) {
        const piece = document.createElement('div');
        piece.className = 'confetti-piece';

        const color = CONFETTI_COLORS[Math.floor(Math.random() * CONFETTI_COLORS.length)];
        const tx = (Math.random() - 0.5) * 500;
        const tr = (Math.random() * 720) + 'deg';

        piece.style.setProperty('--start-x', originX + 'px');
        piece.style.setProperty('--start-y', originY + 'px');
        piece.style.setProperty('--tx', tx + 'px');
        piece.style.setProperty('--tr', tr);
        piece.style.setProperty('--color', color);

        document.body.appendChild(piece);
        setTimeout(() => piece.remove(), CONFETTI_LIFESPAN_MS);
    }
}
