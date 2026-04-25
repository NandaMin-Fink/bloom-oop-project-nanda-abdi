import { GardenApp } from './garden-app.js';

function mount() {
    const gridEl    = document.getElementById('garden-grid');
    const statsEl   = document.getElementById('stats-header');
    const emptyEl   = document.getElementById('empty-state');
    const modalRoot = document.getElementById('modal-root');

    const app = new GardenApp({ gridEl, statsEl, emptyEl, modalRoot });

    document.getElementById('new-habit-btn').addEventListener('click', () => app.openAddModal());
    document.getElementById('empty-state-btn').addEventListener('click', () => app.openAddModal());

    app.start();

    // devtools convenience — lets you poke at state from the console
    window.bloom = app;
}

document.addEventListener('DOMContentLoaded', mount);
