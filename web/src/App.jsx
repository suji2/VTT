import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './HOME/Home'; // Home 컴포넌트 불러오기
import About from './ABOUT/About'; // About 컴포넌트 불러오기
import Version from './Version/Version';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} /> 
        <Route path="/about" element={<About />} />
        <Route path="/Version" element={<Version />} />     
      </Routes>
    </Router>
  );
}

export default App;
