import { describe, it, expect, vi, beforeEach } from 'vitest'
import { getAllBookings, getBookingById, createBooking, cancelBooking } from './bookingService'

global.fetch = vi.fn()

describe('bookingService', () => {
  beforeEach(() => {
    fetch.mockClear()
  })

  it('fetches bookings successfully', async () => {
    const bookings = [{ id: '1', passengerName: 'John Doe', status: 'created' }]
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(bookings)
    })

    const result = await getAllBookings()
    expect(result).toEqual(bookings)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/bookings')
  })

  it('fetches a booking by id successfully', async () => {
    const booking = { id: '1', passengerName: 'John Doe', status: 'created' }
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(booking)
    })

    const result = await getBookingById('1')
    expect(result).toEqual(booking)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/bookings/1')
  })

  it('creates a booking successfully', async () => {
    const booking = { launchId: 'l1', passengerName: 'John Doe', passengerEmail: 'john@doe.com', passengerPhone: '123456' }
    const createdBooking = { id: 'b1', ...booking, status: 'created' }
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(createdBooking)
    })

    const result = await createBooking(booking)
    expect(result).toEqual(createdBooking)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/bookings', expect.objectContaining({
      method: 'POST',
      body: JSON.stringify(booking)
    }))
  })

  it('cancels a booking successfully', async () => {
    const id = '1'
    const cancelledBooking = { id, passengerName: 'John Doe', status: 'cancelled' }
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(cancelledBooking)
    })

    const result = await cancelBooking(id)
    expect(result).toEqual(cancelledBooking)
    expect(fetch).toHaveBeenCalledWith(`http://localhost:8080/api/bookings/${id}`, expect.objectContaining({
      method: 'PATCH'
    }))
  })
})
