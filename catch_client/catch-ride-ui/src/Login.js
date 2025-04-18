import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import AuthService from './AuthService';
import axios from './axiosConfig'

const LoginComponent = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const navigate = useNavigate();
  
    const handleLogin = async (e) => {
      e.preventDefault();
      try {
        const response = await AuthService.login({ username, password });
  
        const { jwt_token, roles, appUserId } = response.data;
  
        localStorage.setItem('token', jwt_token);
        localStorage.setItem('roles', JSON.stringify(roles));
        localStorage.setItem('appUserId', appUserId);
        localStorage.setItem('username', username); // save the login name

        // Fetch user info from User table
        const userResponse = await axios.get(
          `http://localhost:8080/api/user/${appUserId}`,
          {
            headers: {
              Authorization: `Bearer ${jwt_token}`
            }
          }
        );
  
        const { firstName, lastName, email, locationId } = userResponse.data;
  
        localStorage.setItem('firstName', firstName);
        localStorage.setItem('lastName', lastName);
        localStorage.setItem('email', email);
        localStorage.setItem('locationId', locationId);
        if (localStorage.getItem('roles').includes('ADMIN')){
            navigate('/admin');
        }
        else{
            navigate('/user');
        }
        
      } catch (error) {
        console.error('Login error:', error);
        setMessage('Invalid credentials');
      }
    };
  

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">Login Form</div>
                        <div className="card-body">
                            {message && <div className="alert alert-danger">{message}</div>}
                            <form onSubmit={handleLogin}>
                                <div className="form-group">
                                    <label>Username</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                    />
                                </div>
                                <div className="form-group">
                                    <label>Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                </div>
                                <button type="submit" className="btn btn-primary">Login</button>
                            </form>
                            <div className="mt-3">
                                <span>Not registered? <Link to="/register/">Register here</Link></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginComponent;