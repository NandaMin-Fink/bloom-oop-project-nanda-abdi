const ROOT_ID = 'toast-root';
const LIFESPAN_MS = 3500;

function show(message, variant) {
    const root = document.getElementById(ROOT_ID);
    if (!root) return;

    const el = document.createElement('div');
    el.className = 'toast ' + variant;
    el.textContent = message;
    root.appendChild(el);

    setTimeout(() => el.remove(), LIFESPAN_MS);
}

export const toast = {
    info:  (msg) => show(msg, 'info'),
    error: (msg) => show(msg, 'error'),
};
