import { useState } from 'react'
import api from '../api/axios'

export default function useAuth(){
  const [user, setUser] = useState(() => {
    const u = localStorage.getItem('user')
    return u ? JSON.parse(u) : null
  })

  async function login(accountNo, pin){
    const { data } = await api.post('/auth/login', { accountNo, pin })
    localStorage.setItem('token', data.token)
    localStorage.setItem('user', JSON.stringify({ accountNo: data.accountNo, holderName: data.holderName }))
    setUser({ accountNo: data.accountNo, holderName: data.holderName })
    return data
  }

  function logout(){
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    setUser(null)
  }

  return { user, setUser, login, logout }
}
