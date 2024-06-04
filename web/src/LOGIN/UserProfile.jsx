import React from 'react';
import { useLocation } from 'react-router-dom';

function UserProfile() {
  const location = useLocation();
  const user = location.state?.user;

  return (
    <div>
      <h1>User Profile</h1>
      {user ? (
        <div>
          <p>Name: {user.name}</p>
          <p>Email: {user.email}</p>
          {user.picture && <img src={user.picture} alt="Profile" />}
        </div>
      ) : (
        <p>No user data available.</p>
      )}
    </div>
  );
}

export default UserProfile;
