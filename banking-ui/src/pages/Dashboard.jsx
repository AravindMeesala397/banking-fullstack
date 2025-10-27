import React, { useEffect, useState } from 'react'
import api from '../api/axios'
import useAuth from '../context/AuthContext'
import AccountCard from '../components/AccountCard'
import TransactionTable from '../components/TransactionTable'

export default function Dashboard() {
  const { user } = useAuth()
  const [account, setAccount] = useState(null)
  const [tx, setTx] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    async function loadData() {
      if (!user) return
      try {
        const me = await api.get('/accounts/me')
        setAccount(me.data)
        const t = await api.get('/accounts/me/transactions')
        setTx(t.data)
      } catch (err) {
        console.error(err)
      } finally {
        setLoading(false)
      }
    }
    loadData()
  }, [user])

  if (loading) return <p className="text-center p-8">Loading...</p>

  return (
    <div className="space-y-4">
      <AccountCard account={account} />
      <div>
        <h2 className="font-semibold mb-2">Recent Transactions</h2>
        {tx.length === 0 ? (
          <p className="text-gray-500">No transactions found</p>
        ) : (
          <TransactionTable rows={tx} />
        )}
      </div>
    </div>
  )
}
