import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import useAuth from '../hooks/useAuth'

export default function Login(){
  const [accountNo, setAccountNo] = useState('ACC1001')
  const [pin, setPin] = useState('1234')
  const [error, setError] = useState('')
  const { login } = useAuth()
  const nav = useNavigate()

  async function onSubmit(e){
    e.preventDefault()
    setError('')
    try{
      await login(accountNo, pin)
      nav('/dashboard')
    }catch(err){
      setError(err.response?.data?.error || 'Login failed')
    }
  }

  return (
    <div className="max-w-md mx-auto mt-12">
      <h1 className="text-2xl font-bold mb-4">Sign in</h1>
      <form onSubmit={onSubmit} className="space-y-3 bg-white p-4 border rounded-xl">
        <div>
          <label className="block text-sm mb-1">Account Number</label>
          <input value={accountNo} onChange={e=>setAccountNo(e.target.value)}
                 className="w-full border rounded-md p-2" placeholder="ACC1001"/>
        </div>
        <div>
          <label className="block text-sm mb-1">PIN</label>
          <input type="password" value={pin} onChange={e=>setPin(e.target.value)}
                 className="w-full border rounded-md p-2" placeholder="****"/>
        </div>
        {error && <p className="text-red-600 text-sm">{error}</p>}
        <button className="w-full bg-blue-600 hover:bg-blue-700 text-white rounded-md p-2">Login</button>
      </form>
    </div>
  )
}
