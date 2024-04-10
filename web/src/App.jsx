import React, { useEffect, useState } from 'react';
import './App.css';
import axios from 'axios';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './HOME/Home';
import About from './ABOUT/About';
import Version from './Version/Version';
import WriteForm from './Version/WriteForm';
import Login from './LOGIN/login';

function App() {
  const [data, setData] = useState([]);
  console.log("data = ", data)

  useEffect(() => {
    axios.get('/videos') // URL 변경
      .then(res => {
        console.log(res.data);
        setData(res.data);
      })
      .catch(err => console.log(err));
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home data={data} />} />
        <Route path="/about" element={<About />} />
        <Route path="/version" element={<Version />} />
        <Route path="/write" element={<WriteForm />} />
        <Route path="/login" element={<Login />} /> {/* 'Login' 컴포넌트 이름을 대문자로 수정 */}
      </Routes>
    </Router>
  );
}

export default App;
