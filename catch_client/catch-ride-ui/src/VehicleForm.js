import { useState, useEffect } from "react";
import { useNavigate, useParams, Link } from "react-router-dom";

const VIHICLE_DEFAULT = {
    make: "",
    model: "",
    year: 0,
    color: "",
    trim: "",
    bookStatus: false,
}

function VihicleForm() {
    const [vihicle, setVihicle] = useState(VIHICLE_DEFAULT);
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
            setVihicle(data);
          })
          .catch(console.log);
      } else {
        setVihicle(VIHICLE_DEFAULT);
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

export default VihicleForm;