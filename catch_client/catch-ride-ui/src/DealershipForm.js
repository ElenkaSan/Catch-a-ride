import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";
import axios from "./axiosConfig";

const LOCATION_DEFAULT = {
  address: "",
  city: "",
  state: "",
  zipCode: ""
};

const DEALERSHIP_DEFAULT = {
  name: "",
  description: "",
  location: LOCATION_DEFAULT
};

function DealershipForm() {
  const [dealership, setDealership] = useState(DEALERSHIP_DEFAULT);
  const [errors, setErrors] = useState([]);
  const { dealershipId } = useParams();
  const navigate = useNavigate();
  const isEdit = !!dealershipId;

  useEffect(() => {
    if (isEdit) {
      axios
        .get(`/api/dealership/id/${dealershipId}`)
        .then(res => {
          const loadedDealership = res.data;
          // Ensure location exists
          loadedDealership.location = loadedDealership.location || LOCATION_DEFAULT;
          setDealership(loadedDealership);
        })
        .catch(err => console.log("Fetch error:", err));
    }
  }, [dealershipId, isEdit]);

  const handleChange = (event) => {
    const { name, value } = event.target;

    if (["address", "city", "state", "zipCode"].includes(name)) {
      setDealership({
        ...dealership,
        location: {
          ...dealership.location,
          [name]: name === "zipCode" ? parseInt(value, 10) : value,
        },
      });
    } else {
      setDealership({
        ...dealership,
        [name]: value,
      });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      if (isEdit) {
        console.log("Updating location:", dealership.location);
        await axios.put(`/api/location/${dealership.location.locationId}`, dealership.location);
        await axios.put(`/api/dealership/${dealershipId}`, {
          name: dealership.name,
          description: dealership.description,
          location: { locationId: dealership.location.locationId }
        });
      } else {
        console.log("Creating location:", dealership.location);
        const locationRes = await axios.post("/api/location", dealership.location);
        const locationId = locationRes.data.locationId;

        const dealershipPayload = {
          name: dealership.name,
          description: dealership.description,
          locationId
        };

        await axios.post("/api/dealership", dealershipPayload);
      }

      navigate("/admin");
    } catch (err) {
      if (err.response && err.response.data) {
        setErrors(err.response.data);
      } else {
        console.log(err);
      }
    }
  };

  return (
    <div className="container p-4 mt-4">
      <section className="container justify-content-md-center">
        <div className="jumbotron col-md-6 offset-md-3">
          <h2 className="text-center text-info mb-4">
            {isEdit ? "Edit Dealership" : "Add Dealership"}
          </h2>

          {errors.length > 0 && (
            <div className="alert alert-danger">
              <p>The following errors were found:</p>
              <ul>{errors.map((err, idx) => <li key={idx}>{err}</li>)}</ul>
            </div>
          )}

          <form onSubmit={handleSubmit}>
            {/* Dealership Info */}
            <fieldset className="form-group mb-3">
              <label htmlFor="name">Name</label>
              <input
                id="name"
                name="name"
                type="text"
                className="form-control"
                value={dealership.name}
                onChange={handleChange}
              />
            </fieldset>

            <fieldset className="form-group mb-3">
              <label htmlFor="description">Description</label>
              <textarea
                id="description"
                name="description"
                className="form-control"
                value={dealership.description}
                onChange={handleChange}
              />
            </fieldset>

            {/* Location Info */}
            <h5 className="text-info mt-4">Location</h5>

            <fieldset className="form-group mb-3">
              <label htmlFor="address">Address</label>
              <input
                id="address"
                name="address"
                type="text"
                className="form-control"
                value={dealership.location?.address || ""}
                onChange={handleChange}
              />
            </fieldset>

            <fieldset className="form-group mb-3">
              <label htmlFor="city">City</label>
              <input
                id="city"
                name="city"
                type="text"
                className="form-control"
                value={dealership.location?.city || ""}
                onChange={handleChange}
              />
            </fieldset>

            <fieldset className="form-group mb-3">
              <label htmlFor="state">State</label>
              <input
                id="state"
                name="state"
                type="text"
                className="form-control"
                value={dealership.location?.state || ""}
                onChange={handleChange}
              />
            </fieldset>

            <fieldset className="form-group mb-3">
              <label htmlFor="zipCode">Zip Code</label>
              <input
                id="zipCode"
                name="zipCode"
                type="text"
                className="form-control"
                value={dealership.location?.zipCode || ""}
                onChange={handleChange}
              />
            </fieldset>

            <div className="d-flex justify-content-between mt-4">
              <button type="submit" className="btn btn-info btn-lg">
                {isEdit ? "Update Dealership" : "Add Dealership"}
              </button>
              <Link to="/admin" className="btn btn-secondary btn-lg">Cancel</Link>
            </div>
          </form>
        </div>
      </section>
    </div>
  );
}

export default DealershipForm;
