
import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Register from './components/Register'
import Login from './components/Login'
import Layout from './components/Layout';
import Dashboard from './pages/Dashboard';
import Accounts from './pages/Accounts';
import Budget from './pages/Budget';
import Transaction from './pages/Transaction';
import Categories from './pages/Categories';

function App() {

  return (
    <Router>
      <Routes>
        <Route path='/' element={<Register/>}/>
        <Route path='/login' element={<Login/>}/>
      </Routes>

      <Routes>
        <Route path='/dashboard' element={
          <Layout>
            <Dashboard/>
          </Layout>
        }
        />
        <Route path='/accounts' element={
          <Layout>
            <Accounts/>
          </Layout>
        }
        />
        <Route path='/budgets' element={
          <Layout>
            <Budget/>
          </Layout>
        }
        />
        <Route path='/transactions' element={
          <Layout>
            <Transaction/>
          </Layout>
        }
        />
        <Route path='/categories' element={
          <Layout>
            <Categories/>
          </Layout>
        }
        />
      </Routes>
    </Router>
  )
}

export default App
