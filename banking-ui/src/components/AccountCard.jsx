import React from 'react'

export default function AccountCard({ account }){
  if(!account) return null
  return (
    <div className="rounded-xl border p-4 bg-white shadow-sm">
      <p className="text-sm text-gray-500">Account</p>
      <p className="font-semibold text-lg">{account.accountNo}</p>
      <p className="text-sm text-gray-500 mt-2">Holder</p>
      <p className="font-medium">{account.holderName}</p>
      <p className="text-sm text-gray-500 mt-2">Balance</p>
      <p className="font-bold text-2xl">₹ {Number(account.balance).toLocaleString('en-IN')}</p>
    </div>
  )
}
