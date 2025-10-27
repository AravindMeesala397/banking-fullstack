import React, { useState } from 'react'
import api from '../api/axios'

export default function Deposit() {
  const [amount, setAmount] = useState('')
  const [msg, setMsg] = useState('')

  async function handleSubmit(e) {
    e.preventDefault()
    setMsg('')
    try {
      const { data } = await api.post('/accounts/me/deposit', {
        amount: Number(amount),
      })
      setMsg(`Deposited successfully. New balance ₹${data.balance}`)
      setAmount('')
    } catch (err) {
      setMsg(err.response?.data?.error || 'Deposit failed')
    }
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="bg-white border p-4 rounded-xl max-w-md"
    >
      <h2 className="font-semibold mb-2">Deposit Money</h2>
      <input
        type="number"
        step="0.01"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
        placeholder="Amount"
        className="border rounded-md p-2 w-full mb-2"
      />
      <button className="bg-blue-600 text-white rounded-md px-4 py-2">
        Submit
      </button>
      {msg && <p className="mt-2 text-sm">{msg}</p>}
    </form>
  )
}
