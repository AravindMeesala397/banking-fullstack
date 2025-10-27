import React, { createContext, useContext, useEffect, useState } from 'react'
import api from '../api/axios'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null)

  // Restore user from localStorage on reload
  useEffect(() => {
    const stored = localStorage.getItem('user')
    if (stored) setUser(JSON.parse(stored))
  }, [])

  // Login API
  async function login(accountNo, pin) {
    const { data } = await api.post('/auth/login', { accountNo, pin })
    localStorage.setItem('token', data.token)
    localStorage.setItem(
      'user',
      JSON.stringify({
        accountNo: data.accountNo,
        holderName: data.holderName
      })
    )
    setUser({ accountNo: data.accountNo, holderName: data.holderName })
    return data
  }

  // Logout and clear data
  function logout() {
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    setUser(null)
  }

  return (
    <AuthContext.Provider value={{ user, setUser, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

// Hook for components
export default function useAuth() {
  return useContext(AuthContext)
}
