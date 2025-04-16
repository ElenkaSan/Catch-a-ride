import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

const VEHICLE_DEFAULT = {
    make: "",
    model: "",
    year: 0,
    color: "",
    trim: "",
    bookStatus: false,
}

function VehicleForm() {
    const [vehicle, setVehicle] = useState(VEHICLE_DEFAULT);
    const [errors, setErrors] = useState([]);
  
    const url = "http://localhost:8080/api/vehicle";
    const navigate = useNavigate();
    const { id } = useParams();
    
    // useEffect
    useEffect(() => {
      if (id) {
        fetch(`${url}/${id}`)
          .then((response) => {
            if (response.status === 200) {
              return response.json();
            } else {
              return Promise.reject(`Unexpected StatusCode: ${response.status}`);
            }
          })
          .then((data) => {
            setVehicle(data);
          })
          .catch(console.log);
      } else {
        setVehicle(VEHICLE_DEFAULT);
      }
    }, [id]); // Hey React, please call my useEffect function every time the id route in the url parameter changes
    
    return (
        <div>
            <section>
                <h1>Vehicle Form</h1>
            </section>        
        </div>
    );
}

export default VehicleForm;