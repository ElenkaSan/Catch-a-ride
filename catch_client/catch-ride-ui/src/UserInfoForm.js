import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

const USER_DEFAULT = {
    firstName: "",
    lastName: "",
    email: "",
    dateCreatedAt: "",
    locationId: "",
    appUserId: ""
};

function UserInfoForm() {
    const [user, setUser] = useState(USER_DEFAULT);
    const [error, setError] = useState([]);
    const [getMessage, setGetMessage] = useState("");
    const navigate = useNavigate();
    const { userId } = useParams();
    const url = "http://localhost:8080/api/user";

    useEffect(() => {
        if (userId) {
            fetch(`${url}/id/${userId}`)
                .then(res => {
                    if (res.status === 200) return res.json();
                    else return Promise.reject(`Unexpected Status: ${res.status}`);
                })
                .then(data => setUser(data))
                .catch(console.error);
        } else {
            setUser(USER_DEFAULT);
        }
    }, [userId]);

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const method = userId ? "PUT" : "POST";
        const endpoint = userId ? `${url}/${userId}` : url;

        fetch(endpoint, {
            method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user)
        })
        .then(res => {
            if (method === "POST" && res.status === 201) return res.json();
            if (method === "PUT" && res.status === 204) return null;
            if (res.status === 400) return res.json();
            return Promise.reject(`Unexpected status: ${res.status}`);
        })
        .then(data => {
            if (data && Array.isArray(data)) {
                setError(data);
            } else {
                setGetMessage(`User ${user.firstName} ${user.lastName} was successfully ${userId ? "updated" : "added"}!`);
                setTimeout(() => {
                    setGetMessage("");
                    navigate("/admin");
                }, 1500);
            }
        })
        .catch(console.error);
    };

    return (
        <div className="container mt-4">
            {getMessage && (
                <div className="alert alert-success text-center">{getMessage}</div>
            )}
            {error.length > 0 && (
                <div className="alert alert-danger">
                    <strong>Errors:</strong>
                    <ul>
                        {error.map((err, idx) => <li key={idx}>{err}</li>)}
                    </ul>
                </div>
            )}
            <section className="container justify-content-md-center">
              <div className="col-md-6 offset-md-3">
               <h2 className="text-center text-info mb-4">{userId ? "Edit User" : "Add User"}</h2>
               <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="firstName" className="form-label">First Name</label>
                    <input type="text" className="form-control" id="firstName" name="firstName"
                        value={user.firstName} onChange={handleChange} required />
                </div>

                <div className="mb-3">
                    <label htmlFor="lastName" className="form-label">Last Name</label>
                    <input type="text" className="form-control" id="lastName" name="lastName"
                        value={user.lastName} onChange={handleChange} required />
                </div>

                <div className="mb-3">
                    <label htmlFor="email" className="form-label">Email</label>
                    <input type="email" className="form-control" id="email" name="email"
                        value={user.email} onChange={handleChange} required />
                </div>

                <div className="mb-3">
                    <label htmlFor="locationId" className="form-label">Location ID</label>
                    <input type="number" className="form-control" id="locationId" name="locationId"
                        value={user.locationId} onChange={handleChange} />
                </div>

                <div className="mb-3">
                    <label htmlFor="appUserId" className="form-label">App User ID</label>
                    <input type="number" className="form-control" id="appUserId" name="appUserId"
                        value={user.appUserId} onChange={handleChange} />
                </div>

                <div className="d-flex justify-content-between mt-4">
                    <button type="submit" className={`btn btn-${userId ? "warning" : "info"}`}>
                        {userId ? "Save Updates" : "Add User"}
                    </button>
                    <Link to="/admin" className="btn btn-secondary">Cancel</Link>
                </div>
               </form>
              </div>
            </section>
        </div>
    );
}

export default UserInfoForm;