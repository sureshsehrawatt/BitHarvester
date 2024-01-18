import React from "react";
import Logo from "../Assets/logo.jsx";
import BitButton from "../Assets/BitButton.jsx";
import { NavLink } from "react-router-dom";

const Navbar = () => {
  const handleClick = () => {
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
