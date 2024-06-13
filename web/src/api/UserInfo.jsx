import React from 'react';

const UserInfo = ({ userInfo }) => {
  return (
    <div>
      <h1>사용자 프로필</h1>
      <p>이름: {userInfo.name}</p>
      <p>이메일: {userInfo.email}</p>
      <p>권한: {userInfo.role}</p>
      <img src={userInfo.picture} alt="프로필" />
    </div>
  );
};

export default UserInfo;
