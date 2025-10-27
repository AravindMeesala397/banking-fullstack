import React from 'react'
import { Link, useLocation } from 'react-router-dom'
import useAuth from '../context/AuthContext'


export default function Navbar() {
  const { user, logout } = useAuth()
  const loc = useLocation()

  const link = (to, label) => (
    <Link
      to={to}
      className={`px-3 py-2 rounded-md ${
        loc.pathname === to
          ? 'bg-blue-600 text-white'
          : 'text-blue-600 hover:bg-blue-100'
      }`}
    >
      {label}
    </Link>
  )

  return (
    <nav className="border-b bg-white">
      <div className="max-w-5xl mx-auto flex items-center justify-between p-3">
        <div className="font-bold text-xl">🏦 Banking</div>

        {/* 👇 this is the full updated block */}
        <div className="flex items-center gap-2">
          {user ? (
            <>
              {link('/dashboard', 'Dashboard')}
              {link('/deposit', 'Deposit')}
              {link('/withdraw', 'Withdraw')}
              {link('/transfer', 'Transfer')}
              {link('/change-pin', 'Change PIN')}
              <button
                onClick={logout}
                className="ml-2 px-3 py-2 rounded-md bg-gray-200 hover:bg-gray-300"
              >
                Logout
              </button>
            </>
          ) : (
            link('/login', 'Login')
          )}
        </div>
      </div>
    </nav>
  )
}
