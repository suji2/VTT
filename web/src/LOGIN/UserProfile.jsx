// UserProfile.jsx
import React from 'react';

const UserProfile = ({ userInfo }) => {
  return (
    <div>
      <h1>사용자 프로필</h1>
      <p>이름: {userInfo.name}</p>
      <p>이메일: {userInfo.email}</p>
      <img src={userInfo.picture} alt="프로필" />
    </div>
  );
};

export default UserProfile;
