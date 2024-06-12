import React from 'react';

function MyPage({ isLogin }) {
  return (
    <div>
      <h1>마이 페이지</h1>
      {isLogin ? <p>로그인 되었습니다.</p> : <p>로그인이 필요합니다.</p>}
    </div>
  );
}

export default MyPage;
