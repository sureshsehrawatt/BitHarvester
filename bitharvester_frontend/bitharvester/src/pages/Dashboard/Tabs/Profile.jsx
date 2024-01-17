import React, { useState } from "react";
// import { NavLink } from "react-router-dom";
import BitButton from "../../../components/Assets/BitButton";
import FilterTiltShiftRoundedIcon from "@mui/icons-material/FilterTiltShiftRounded";

const Profile = () => {
  const [image, setImage] = useState(null);
  // const [setFileName] = useState("No selected fileName");

  const [formData, setFormData] = useState({
    fullname: "",
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("SignUp Form submitted:", formData);
    setFormData({
      fullname: "",
      email: "",
      password: "",
    });
  };
  return (
    <div className="profile">
      <div className="profileFirstDiv">
        <div className="profilePhotoDiv">
          <input
            type="file"
            accept="image/*"
            className="codeUpload"
            onChange={({ target: { files } }) => {
              if (files) {
                setImage(URL.createObjectURL(files[0]));
              }
            }}
            hidden
          />

          {image ? (
            <img src={image} alt="avtar" className="profileImg" />
          ) : (
            <FilterTiltShiftRoundedIcon sx={{ fontSize: 150 }} />
          )}

          <p
            className="profileChangePhotoButton"
            onClick={() => document.querySelector(".codeUpload").click()}
          >
            Change Photo
          </p>
        </div>
        <div className="profileInputsDiv">
          <div className="signInUpFormContainer profileFormContainer">
            <form onSubmit={handleSubmit} className="signInUpForm">
              <input
                type="text"
                name="fullname"
                placeholder="Full Name"
                value={formData.fullname}
                onChange={handleChange}
                className="signInUpInput"
              />
              <br />
              <input
                type="email"
                name="email"
                placeholder="Email Address"
                value={formData.email}
                onChange={handleChange}
                className="signInUpInput"
              />
              <br />
              <input
                type="password"
                name="password"
                placeholder="***********"
                value={formData.password}
                onChange={handleChange}
                className="signInUpInput"
              />
            </form>
          </div>
        </div>
      </div>
      <div className="profileButtonsDiv">
        <div className="profileButtonsFirstDiv">
          <div className="profileButtonsFirstDivItem1">
            <BitButton label="Save" onClick={handleSubmit} />
          </div>
          <div>
            <button className="customBitButton">Reset</button>
          </div>
        </div>
        <div className="profileButtonsFirstDiv">
          <BitButton label="Delete Account" />
        </div>
      </div>
    </div>
  );
};

export default Profile;
