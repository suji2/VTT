import React, { useState, useEffect } from 'react';
import './App.css';
import axios from './api/axiosConfig'; 
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './HOME/Home';
import About from './ABOUT/About';
import Version from './Q&A/Version';
import WriteForm from './Q&A/WriteForm';
import EditForm from './Q&A/EditForm';
import getUserInfo from './api/getUserInfo';
import Nav from './components/Nav';
import LoginButton from './components/LoginButton';
import PostLoginToken from './api/postLoginToken';

function App() {
  const [data, setData] = useState([]);
  const [isLogin, setIsLogin] = useState(false);

  useEffect(() => {
    const initLogin = async () => {
      const userInfo = await getUserInfo();
      setIsLogin(!!userInfo);
    };
    initLogin();

    axios.get('/api/user')
      .then(res => {
        setData(res.data);
      })
      .catch(err => console.log(err));
  }, []);

  return (
    <Router>
      <div>
        {isLogin ? (
          <>
            <Nav isLogin={isLogin} setIsLogin={setIsLogin} />
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/about" element={<About />} />
              <Route path="/version" element={<Version />} />
              <Route path="/write" element={<WriteForm />} />
              <Route path="/edit/:questionId" element={<EditForm />} />
            </Routes>
          </>
        ) : (
          <Routes>
            <Route path="/" element={<LoginButton />} />
            <Route path="/login/oauth2/code/google" element={<PostLoginToken setIsLogin={setIsLogin} />} />
          </Routes>
        )}
      </div>
    </Router>
  );
}

export default App;
