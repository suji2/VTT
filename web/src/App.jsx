import React, { useState, useEffect } from 'react';
import axios from './api/axiosConfig'; // axiosConfig.js 파일 import
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './HOME/Home';
import About from './ABOUT/About';
import Version from './Q&A/Version';
import WriteForm from './Q&A/WriteForm';
import EditForm from './Q&A/EditForm'; // EditForm 임포트 추가
import getUserInfo from './api/getUserInfo'; // getUserInfo 올바르게 임포트
import Nav from './components/Nav'; // Nav 임포트 추가
import LoginButton from './components/LoginButton'; // LoginButton 임포트 추가
import PostLoginToken from './api/postLoginToken'; // PostLoginToken 임포트 추가
import Channel from './HOME/Chammel'; // Channel 임포트 추가
import VideoPlayer from './HOME/VideoPlayer'; // VideoPlayer 임포트 추가

function App() {
  const [data, setData] = useState([]);
  const [isLogin, setIsLogin] = useState(false);

  useEffect(() => {
    const initLogin = async () => {
      const userInfo = await getUserInfo();
      setIsLogin(!!userInfo);
    };
    initLogin();

    axios.get('/api/question')
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
            <Nav isLogin={isLogin} setIsLogin={setIsLogin} /> {/* Nav 컴포넌트 추가 */}
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/about" element={<About />} />
              <Route path="/version" element={<Version />} />
              <Route path="/write" element={<WriteForm />} />
              <Route path="/edit/:questionId" element={<EditForm />} /> {/* EditForm 라우트 추가 */}
              <Route path="/channel/:channelId" element={<Channel />} /> {/* Channel 라우트 추가 */}
              <Route path="/video/:videoId/:title" element={<VideoPlayer />} /> {/* VideoPlayer 라우트 추가 */}
            </Routes>
          </>
        ) : (
          <Routes>
            <Route path="/" element={<LoginButton />} />
            <Route path="/login/oauth2/code/google" element={<PostLoginToken />} /> {/* OAuth2 로그인 콜백 라우트 추가 */}
          </Routes>
        )}
      </div>
    </Router>
  );
}

export default App;
