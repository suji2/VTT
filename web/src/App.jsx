import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Home from './HOME/Home';
import About from './ABOUT/About';
import Version from './Version/Version';
import WriteForm from './Version/WriteForm';
import UserProfile from './LOGIN/UserProfile';
import EditForm from './Version/EditForm'; // EditForm 임포트 추가
import Login from './LOGIN/login';
import MyPage from './LOGIN/MyPage';
import { getUserInfo } from './api/getUserInfo';
import Nav from './components/Nav'; // Nav 임포트 추가
import LoginButton from './components/LoginButton'; // LoginButton 임포트 추가

function App() {
  const [data, setData] = useState([]);
  const [isLogin, setIsLogin] = useState(false);
  console.log("data = ", data);

  useEffect(() => {
    const initLogin = async () => {
      const name = await getUserInfo();
      setIsLogin(!!name);
    };
    initLogin();

    axios.get('/api/question')
      .then(res => {
        console.log(res.data);
        setData(res.data);
      })
      .catch(err => console.log(err));
  }, []);

  return (
    <Router>
      <div>
        {isLogin ? (
          <>
            <Nav isLogin={isLogin} setIsLogin={setIsLogin} /> {/* Nav 컴포넌트 추가 */}
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/about" element={<About />} />
              <Route path="/version" element={<Version />} />
              <Route path="/write" element={<WriteForm />} />
              <Route path="/userprofile" element={<UserProfile />} />
              <Route path="/edit/:questionId" element={<EditForm />} /> {/* EditForm 라우트 추가 */}
              <Route path="/mypage" element={isLogin ? <MyPage isLogin={isLogin} /> : <Navigate to="/" />} /> {/* MyPage 라우트 추가 */}
            </Routes>
          </>
        ) : (
          <Routes>
            <Route path="/" element={<LoginButton />} />
            <Route path="/login" element={<Login isLogin={isLogin} setIsLogin={setIsLogin} />} /> {/* Login 라우트 추가 */}
          </Routes>
        )}
      </div>
    </Router>
  );
}

export default App;
