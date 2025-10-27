import React from 'react'

export default function TransactionTable({ rows }){
  return (
    <div className="overflow-x-auto rounded-xl border bg-white">
      <table className="min-w-full text-sm">
        <thead className="bg-gray-50">
          <tr>
            <th className="p-3 text-left">Date</th>
            <th className="p-3 text-left">Type</th>
            <th className="p-3 text-right">Amount</th>
            <th className="p-3 text-right">Post Balance</th>
            <th className="p-3 text-left">Note</th>
          </tr>
        </thead>
        <tbody>
          {rows?.map(tx => (
            <tr key={tx.id} className="border-t">
              <td className="p-3">{new Date(tx.createdAt).toLocaleString()}</td>
              <td className="p-3">{tx.type}</td>
              <td className="p-3 text-right">₹ {Number(tx.amount).toLocaleString('en-IN')}</td>
              <td className="p-3 text-right">₹ {Number(tx.postBalance).toLocaleString('en-IN')}</td>
              <td className="p-3">{tx.note}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
