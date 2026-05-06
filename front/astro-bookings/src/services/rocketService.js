const API_URL = 'http://localhost:8080';

export async function getAllRockets() {
  const response = await fetch(`${API_URL}/rockets`);
  if (!response.ok) throw new Error('Failed to fetch rockets');
  return await response.json();
}

export async function createRocket(rocket) {
  const response = await fetch(`${API_URL}/rockets`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(rocket)
  });
  if (!response.ok) throw new Error('Failed to create rocket');
  return await response.json();
}

export async function updateRocket(id, rocket) {
  const response = await fetch(`${API_URL}/rockets/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(rocket)
  });
  if (!response.ok) throw new Error('Failed to update rocket');
  return await response.json();
}

export async function decommissionRocket(id) {
  const response = await fetch(`${API_URL}/rockets/${id}`, {
    method: 'DELETE'
  });
  if (!response.ok) throw new Error('Failed to decommission rocket');
}
