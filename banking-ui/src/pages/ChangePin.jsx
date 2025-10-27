import React, { useState } from 'react'
import api from '../api/axios'

export default function ChangePin() {
  const [oldPin, setOld] = useState('')
  const [newPin, setNew] = useState('')
  const [msg, setMsg] = useState('')

  async function handleSubmit(e) {
    e.preventDefault()
    setMsg('Feature not implemented yet in backend')
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="bg-white border p-4 rounded-xl max-w-md"
    >
      <h2 className="font-semibold mb-2">Change PIN</h2>
      <input
        type="password"
        value={oldPin}
        onChange={(e) => setOld(e.target.value)}
        placeholder="Old PIN"
        className="border rounded-md p-2 w-full mb-2"
      />
      <input
        type="password"
        value={newPin}
        onChange={(e) => setNew(e.target.value)}
        placeholder="New PIN"
        className="border rounded-md p-2 w-full mb-2"
      />
      <button className="bg-blue-600 text-white rounded-md px-4 py-2">
        Submit
      </button>
      {msg && <p className="mt-2 text-sm">{msg}</p>}
    </form>
  )
}
