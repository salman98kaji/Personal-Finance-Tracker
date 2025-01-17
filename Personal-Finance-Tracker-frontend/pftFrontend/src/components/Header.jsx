
import {NavLink} from 'react-router-dom'
import Logout from './Logout'

function Header() {
  return (
    <header className="bg-blue-600 text-white py-4 shadow-xl">
      <div className="container px-4 mx-auto flex justify-between items-center">
        <h1 className="text-lg font-bold">Finance Tracker</h1>
        <nav>
          <ul className="flex space-x-4">
            <li><NavLink to="/dashboard" className="hover:underline">Home</NavLink></li>
            <li><NavLink to="/accounts" className="hover:underline">Accounts</NavLink></li>
            <li><NavLink to="/budgets" className="hover:underline">Budgets</NavLink></li>
            <li><NavLink to="/transactions" className="hover:underline">Transactions</NavLink></li>
            <li><NavLink to="/categories" className="hover:underline">Categories</NavLink></li>
            <li><Logout/></li>
          </ul>
        </nav>
      </div>
    </header>
  )
}

export default Header