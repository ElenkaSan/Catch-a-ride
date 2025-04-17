import React, { useState, useEffect } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import axios from './axiosConfig';

const RegisterComponent = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
        firstName: '',
        lastName: '',
        email: '',
        locationId: ''
    });

    const [locations, setLocations] = useState([]);
    const [message, setMessage] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('/api/location')
            .then(res => setLocations(res.data))
            .catch(err => console.error('Failed to fetch locations:', err));
    }, []);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const registerRes = await axios.post('/api/auth/register', {
                username: formData.username,
                password: formData.password
            });

            const appUserId = registerRes.data.appUserId;
            if (!appUserId) throw new Error("Missing app_user_id from registration response");

            await axios.post('/api/user', {
                firstName: formData.firstName,
                lastName: formData.lastName,
                email: formData.email,
                locationId: parseInt(formData.locationId),
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
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">Register</div>
                        <div className="card-body">
                            {message && <div className="alert alert-info">{message}</div>}
                            <form onSubmit={handleRegister}>
                                <div className="form-group">
                                    <label>Username</label>
                                    <input type="text" name="username" className="form-control" value={formData.username} onChange={handleChange} required />
                                </div>
                                <div className="form-group">
                                    <label>Password</label>
                                    <input type="password" name="password" className="form-control" value={formData.password} onChange={handleChange} required />
                                </div>
                                <div className="form-group">
                                    <label>First Name</label>
                                    <input type="text" name="firstName" className="form-control" value={formData.firstName} onChange={handleChange} required />
                                </div>
                                <div className="form-group">
                                    <label>Last Name</label>
                                    <input type="text" name="lastName" className="form-control" value={formData.lastName} onChange={handleChange} required />
                                </div>
                                <div className="form-group">
                                    <label>Email</label>
                                    <input type="email" name="email" className="form-control" value={formData.email} onChange={handleChange} required />
                                </div>
                                <div className="form-group">
                                    <label>Choose a Location</label>
                                    <select name="locationId" className="form-control" value={formData.locationId} onChange={handleChange} required>
                                        <option value="">-- Select a Location --</option>
                                        {locations.map(loc => (
                                            <option key={loc.locationId} value={loc.locationId}>
                                                {loc.address}, {loc.city}, {loc.state} {loc.zipCode}
                                            </option>
                                        ))}
                                    </select>
                                </div>
                                <button type="submit" className="btn btn-primary mt-3">Register</button>
                            </form>
                            <div className="mt-3">
                                <span>Already registered? <Link to="/login">Login here</Link></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterComponent;