const API_URL = 'http://localhost:8080/api';

export async function getAllLaunches() {
  const response = await fetch(`${API_URL}/launches`);
  if (!response.ok) throw new Error('Failed to fetch launches');
  return await response.json();
}

export async function getLaunchById(id) {
  const response = await fetch(`${API_URL}/launches/${id}`);
  if (!response.ok) throw new Error('Failed to fetch launch');
  return await response.json();
}

export async function createLaunch(launch) {
  const response = await fetch(`${API_URL}/launches`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(launch)
  });
  if (!response.ok) throw new Error('Failed to create launch');
  return await response.json();
}

export async function updateLaunch(id, launch) {
  const response = await fetch(`${API_URL}/launches/${id}`, {
    method: 'PATCH',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(launch)
  });
  if (!response.ok) throw new Error('Failed to update launch');
  return await response.json();
}
