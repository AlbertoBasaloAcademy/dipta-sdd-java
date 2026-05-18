const API_URL = 'http://localhost:8080/api';

export async function getAllBookings() {
  const response = await fetch(`${API_URL}/bookings`);
  if (!response.ok) throw new Error('Failed to fetch bookings');
  return await response.json();
}

export async function getBookingById(id) {
  const response = await fetch(`${API_URL}/bookings/${id}`);
  if (!response.ok) throw new Error('Failed to fetch booking');
  return await response.json();
}

export async function createBooking(booking) {
  const response = await fetch(`${API_URL}/bookings`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(booking)
  });
  if (!response.ok) throw new Error('Failed to create booking');
  return await response.json();
}

export async function cancelBooking(id) {
  const response = await fetch(`${API_URL}/bookings/${id}`, {
    method: 'PATCH'
  });
  if (!response.ok) throw new Error('Failed to cancel booking');
  return await response.json();
}
