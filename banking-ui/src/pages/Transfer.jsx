import React, { useState } from 'react'
import api from '../api/axios'

export default function Transfer() {
  const [toAccountNo, setTo] = useState('')
  const [amount, setAmount] = useState('')
  const [msg, setMsg] = useState('')

  async function handleSubmit(e) {
    e.preventDefault()
    setMsg('')
    try {
      const { data } = await api.post('/accounts/me/transfer', {
        toAccountNo,
        amount: Number(amount),
      })
      setMsg(`Transferred ₹${amount} to ${toAccountNo}. New balance ₹${data.balance}`)
      setAmount('')
      setTo('')
    } catch (err) {
      setMsg(err.response?.data?.error || 'Transfer failed')
    }
  }

  return (
    <form
      onSubmit={handleSubmit}
      className="bg-white border p-4 rounded-xl max-w-md"
    >
      <h2 className="font-semibold mb-2">Transfer Funds</h2>
      <input
        value={toAccountNo}
        onChange={(e) => setTo(e.target.value)}
        placeholder="To Account No"
        className="border rounded-md p-2 w-full mb-2"
      />
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
