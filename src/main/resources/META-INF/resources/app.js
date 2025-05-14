// Connect to the SSE endpoint
const evtSource = new EventSource('/products/listen');
const tbody = document.querySelector('#products-table tbody');
const productMap = new Map();

function renderTable() {
    tbody.innerHTML = '';
    Array.from(productMap.values())
        .sort((a, b) => a.id - b.id)
        .forEach(p => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
        <td>${p.id}</td>
        <td>${p.sku}</td>
        <td>${p.description}</td>
        <td>${p.category}</td>
        <td>${p.basePrice}</td>
      `;
            tbody.appendChild(tr);
        });
}

// On each SSE message, update our map and re-render
evtSource.onmessage = e => {
    const p = JSON.parse(e.data);
    productMap.set(p.id, p);
    renderTable();
};

evtSource.onerror = err => {
    console.error('SSE error:', err);
};
