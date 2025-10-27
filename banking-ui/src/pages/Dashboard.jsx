import React, { useEffect, useState } from 'react'
import api from '../api/axios'
import AccountCard from '../components/AccountCard'
import TransactionTable from '../components/TransactionTable'

export default function Dashboard(){
  const [account, setAccount] = useState(null)
  const [tx, setTx] = useState([])

  useEffect(()=>{
    (async ()=>{
      const me = await api.get('/accounts/me')
      setAccount(me.data)
      const t = await api.get('/accounts/me/transactions')
      setTx(t.data)
    })()
  },[])

  return (
    <div className="space-y-4">
      <AccountCard account={account} />
      <div>
        <h2 className="font-semibold mb-2">Recent Transactions</h2>
        <TransactionTable rows={tx} />
      </div>
    </div>
  )
}
