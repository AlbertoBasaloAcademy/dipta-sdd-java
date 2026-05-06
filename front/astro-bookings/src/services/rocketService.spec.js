import { describe, it, expect, vi, beforeEach } from 'vitest'
import { getAllRockets, createRocket } from './rocketService'

global.fetch = vi.fn()

describe('rocketService', () => {
  beforeEach(() => {
    fetch.mockClear()
  })

  it('fetches rockets successfully', async () => {
    const rockets = [{ id: '1', name: 'Falcon 9' }]
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(rockets)
    })

    const result = await getAllRockets()
    expect(result).toEqual(rockets)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/rockets')
  })

  it('creates a rocket successfully', async () => {
    const rocket = { name: 'Falcon Heavy', capacity: 9, range: 'Mars' }
    const createdRocket = { id: '2', ...rocket }
    fetch.mockResolvedValue({
      ok: true,
      json: () => Promise.resolve(createdRocket)
    })

    const result = await createRocket(rocket)
    expect(result).toEqual(createdRocket)
    expect(fetch).toHaveBeenCalledWith('http://localhost:8080/rockets', expect.objectContaining({
      method: 'POST',
      body: JSON.stringify(rocket)
    }))
  })
})
