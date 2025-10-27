import React from 'react'
import { Routes, Route, Navigate, Link } from 'react-router-dom'
import useAuth from './hooks/useAuth'
import Navbar from './components/Navbar'
import Login from './pages/Login'
import Dashboard from './pages/Dashboard'
import Deposit from './pages/Deposit'
import Withdraw from './pages/Withdraw'
import Transfer from './pages/Transfer'

function PrivateRoute({ children }){
  const { user } = useAuth()
  return user ? children : <Navigate to="/login" replace />
}

export default function App(){
  return (
    <div className="min-h-screen">
      <Navbar />
      <div className="max-w-3xl mx-auto p-4">
        <Routes>
          <Route path="/" element={<Navigate to="/dashboard" replace />} />
          <Route path="/login" element={<Login />} />
          <Route path="/dashboard" element={<PrivateRoute><Dashboard/></PrivateRoute>} />
          <Route path="/deposit" element={<PrivateRoute><Deposit/></PrivateRoute>} />
          <Route path="/withdraw" element={<PrivateRoute><Withdraw/></PrivateRoute>} />
          <Route path="/transfer" element={<PrivateRoute><Transfer/></PrivateRoute>} />
          <Route path="*" element={<div className="text-center p-8">
            <p className="text-xl">404</p>
            <Link className="text-blue-600 underline" to="/dashboard">Go Home</Link>
          </div>} />
        </Routes>
      </div>
    </div>
  )
}
