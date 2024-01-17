import React from "react";
import Logo from "../Assets/logo.jsx";
import BitButton from "../Assets/BitButton.jsx";
import { NavLink } from "react-router-dom";

const Navbar = () => {
  const handleClick = (e) => {
    console.log("Get started button clicked");
  };

  return (
    <div className="navbar">
      <div>
        <Logo />
      </div>
      <div className="getButton">
        <NavLink to="signinup">
          <BitButton label="Get started" onClick={handleClick} />
        </NavLink>
      </div>
    </div>
  );
};

export default Navbar;

// import { useNavigate } from 'react-router-dom'
// const navigate = useNavigate()
// <BitButton label="Get started" onClick={() => navigate('/bitdashboard')} /> 
