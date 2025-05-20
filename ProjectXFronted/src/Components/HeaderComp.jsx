import React from 'react'
import '../Styling/Header.css'
import { Link } from 'react-router-dom'

function HeaderComp() {
  return (
    <header>
        <div className='flex items-end place-content-around'>
      <div className='Heading'>
            <h1>ProjectXplorer</h1>
        </div>
        <div className='NavLinks'>
          <Link to='/'>
          <span >Home</span>
          </Link>
          <Link to='/Fav'>
          <span >Favorites</span>
          </Link>
          <Link to='/About'>
          <span >About</span>
          </Link>
        </div>
      </div>
        
    </header>
  )
}

export default HeaderComp