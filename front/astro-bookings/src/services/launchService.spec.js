import { describe, it, expect, vi, beforeEach } from 'vitest'
import { getAllLaunches, getLaunchById, createLaunch, updateLaunch } from './launchService'

global.fetch = vi.fn()

describe('launchService', () => {
  beforeEach(() => {
    fetch.mockClear()
  })

  it('fetches launches successfully', async () => {
    const launches = [{ id: '1', rocketId: 'r1', status: 'created' }]
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(launches)
    })

    const result = await getAllLaunches()
    expect(result).toEqual(launches)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/launches')
  })

  it('fetches a launch by id successfully', async () => {
    const launch = { id: '1', rocketId: 'r1', status: 'created' }
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(launch)
    })

    const result = await getLaunchById('1')
    expect(result).toEqual(launch)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/launches/1')
  })

  it('creates a launch successfully', async () => {
    const launch = { rocketId: 'r1', launchTime: '2026-05-11T12:00', pricePerTicket: 1000, minimumOccupancy: 5 }
    const createdLaunch = { id: '1', ...launch, status: 'created' }
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(createdLaunch)
    })

    const result = await createLaunch(launch)
    expect(result).toEqual(createdLaunch)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/api/launches', expect.objectContaining({
      method: 'POST',
      body: JSON.stringify(launch)
    }))
  })

  it('updates a launch successfully', async () => {
    const id = '1'
    const updateData = { status: 'confirmed' }
    const updatedLaunch = { id, rocketId: 'r1', status: 'confirmed' }
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(updatedLaunch)
    })

    const result = await updateLaunch(id, updateData)
    expect(result).toEqual(updatedLaunch)
    expect(fetch).toHaveBeenCalledWith(`http://localhost:8080/api/launches/${id}`, expect.objectContaining({
      method: 'PATCH',
      body: JSON.stringify(updateData)
    }))
  })
})
