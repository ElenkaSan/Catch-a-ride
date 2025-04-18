import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from './axiosConfig';

const RegisterComponent = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        address: '',
        city: '',
        state: '',
        zipCode: ''
    });

    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            // Step 1: Register app user
            const registerRes = await axios.post('/api/auth/register', {
                username: formData.username,
                password: formData.password
            });
            const appUserId = registerRes.data.appUserId;
            if (!appUserId) throw new Error("Missing app_user_id");

            // Step 2: Submit new location
            const locationRes = await axios.post('/api/location', {
                address: formData.address,
                city: formData.city,
                state: formData.state,
                zipCode: formData.zipCode
            });
            const locationId = locationRes.data.locationId;
            if (!locationId) throw new Error("Missing location_id");

            // Step 3: Submit full user details
            await axios.post('/api/user', {
                firstName: formData.firstName,
                lastName: formData.lastName,
                email: formData.email,
                locationId,
                appUserId
            });

            setMessage("Registration successful!");
            navigate('/login');
        } catch (error) {
            console.error("Registration error:", error);
            setMessage("Registration failed. Please try again.");
        }
    };

    return (
        <div className="container p-4 mt-4">
          <section className="container justify-content-md-center">
            <div className="jumbotron col-md-6 offset-md-3">
                    {/* <div className="card"> */}
                    <h2 className="text-center text-info mb-4">Register</h2>
                        <div className="card-body">
                            {message && <div className="alert alert-info">{message}</div>}
                            <form onSubmit={handleRegister}>
                                {/* USER CREDENTIALS */}
                                <div className="form-group mb-3">
                                    <label>Username</label>
                                    <input type="text" name="username" className="form-control" value={formData.username} onChange={handleChange} required />
                                </div>
                                <div className="form-group mb-3">
                                    <label>Password</label>
                                    <input type="password" name="password" className="form-control" value={formData.password} onChange={handleChange} required />
                                </div>
                                {/* PERSONAL INFO */}
                                <div className="form-group mb-3">
                                    <label>First Name</label>
                                    <input type="text" name="firstName" className="form-control" value={formData.firstName} onChange={handleChange} required />
                                </div>
                                <div className="form-group mb-3">
                                    <label>Last Name</label>
                                    <input type="text" name="lastName" className="form-control" value={formData.lastName} onChange={handleChange} required />
                                </div>
                                <div className="form-group mb-3">
                                    <label>Email</label>
                                    <input type="email" name="email" className="form-control" value={formData.email} onChange={handleChange} required />
                                </div>
                                {/* LOCATION INFO */}
                                <div className="form-group mb-3">
                                    <label>Street Address</label>
                                    <input type="text" name="address" className="form-control" value={formData.address} onChange={handleChange} required />
                                </div>
                                <div className="form-group mb-3">
                                    <label>City</label>
                                    <input type="text" name="city" className="form-control" value={formData.city} onChange={handleChange} required />
                                </div>
                                <div className="form-group mb-3">
                                    <label>State</label>
                                    <input type="text" name="state" className="form-control" value={formData.state} onChange={handleChange} required />
                                </div>
                                <div className="form-group mb-3">
                                    <label>Zip Code</label>
                                    <input type="number" name="zipCode" className="form-control" value={formData.zipCode} onChange={handleChange} required />
                                </div>
                                <button type="submit" className="btn btn-info btn-lg mt-4">Register</button>
                            </form>
                            <div className="mt-4">
                                <span>Already registered? <Link to="/login">Login here</Link></span>
                            </div>
                        </div>
                    {/* </div> */}
                </div>
            </section>
        </div>
    );
};

export default RegisterComponent;