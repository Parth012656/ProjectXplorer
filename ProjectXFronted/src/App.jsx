import React from 'react';
import './App.css';
import FilterComponent from './Components/ProjectFilter.jsx';
import GeminiChat from './Components/GeminiChat';
import HeaderComp from './Components/HeaderComp.jsx';
import FooterComp from './Components/FooterComp.jsx'
import FavoritesComp from './Components/FavoritesComp.jsx'
import { Route,Routes } from 'react-router-dom';
import About from './Components/About.jsx';

function App() {
  return (
    <>
      <div className="app-container">      
        <HeaderComp/>
        <div className='FilterComponent'>
        <Routes>
          <Route path='/' element={<FilterComponent />}/>
          <Route path='/Fav' element={<FavoritesComp />}/>
          <Route path='/About' element={<About />}/>

        </Routes>
        </div>
      </div> 
    </>
  );
}

export default App;
