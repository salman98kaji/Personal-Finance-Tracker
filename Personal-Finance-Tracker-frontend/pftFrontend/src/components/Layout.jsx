import React from 'react'
import Header from './Header'
import Footer from './Footer'

const Layout = ({children}) => {
  return (
    <div className="flex flex-col min-h-screen bg-gray-500">
      <Header />
      <main className="flex-grow container mx-auto py-6">
        {children} {/* Render the Dashboard or any other page here */}
      </main>
      <Footer />
    </div>
  )
}

export default Layout