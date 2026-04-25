export function timeAgo(iso) {
    if (!iso) return 'never';
    const then = new Date(iso).getTime();
    const diffSec = Math.max(0, (Date.now() - then) / 1000);

    if (diffSec < 60)    return 'just now';
    if (diffSec < 3600)  return `${Math.floor(diffSec / 60)}m ago`;
    if (diffSec < 86400) return `${Math.floor(diffSec / 3600)}h ago`;

    const days = Math.floor(diffSec / 86400);
    return days === 1 ? 'yesterday' : `${days}d ago`;
}
