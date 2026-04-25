export class ApiError extends Error {
    constructor(message, status) {
        super(message);
        this.status = status;
    }
}

async function request(method, path, body) {
    const opts = {
        method,
        headers: body ? { 'Content-Type': 'application/json' } : {},
    };
    if (body) opts.body = JSON.stringify(body);

    const res = await fetch(path, opts);
    const text = await res.text();

    if (!res.ok) {
        throw new ApiError(text || res.statusText, res.status);
    }
    return text ? JSON.parse(text) : null;
}

function encode(name) {
    return encodeURIComponent(name);
}

export const api = {
    getGarden:    ()     => request('GET',    '/api/garden'),
    addHabit:     (req)  => request('POST',   '/api/garden/habits', req),
    water:        (name) => request('POST',   `/api/garden/habits/${encode(name)}/water`),
    miss:         (name) => request('POST',   `/api/garden/habits/${encode(name)}/miss`),
    remove:       (name) => request('DELETE', `/api/garden/habits/${encode(name)}`),
    refresh:      ()     => request('POST',   '/api/garden/refresh'),
};
