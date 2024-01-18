import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import {
  GridViewRounded,
  SettingsRounded,
  UploadRounded,
  PersonRounded,
} from "@mui/icons-material";
import { backendBaseUrl } from "../../conf/config";
import axios from "axios";

const Sidebar = () => {
  const[userName, setUserName] = useState("");

  useEffect(() => {
    fetchData();
  }, []); 

  async function fetchData() {
    const URL = `${backendBaseUrl}/api/user/${localStorage.getItem("userId")}`;
    try {
      const response = await axios.get(URL);
      console.log(response)
      console.log(response.data.fullname)
      setUserName(response.data.fullname);
    } catch (error) {
      console.error(
        "Error while data fetching :",
        error.response ? error.response.data : error.message
      );
    }
  }

  const navLinkStyles = ({ isActive }) => {
    return {
      color: isActive ? "#5be49b" : "",
      backgroundColor: isActive ? "#1A3230" : "",
    };
  };
  return (
    <div className="sidebar">
      <div className="sidebarLogo">BitHarvester</div>
      <div className="sidebarItems">
        <Item
          text="Dashboard"
          icon={<GridViewRounded sx={{ fontSize: 24 }} />}
          path="/bitdashboard/dashboard"
        />
        <Item
          text="Upload Code"
          icon={<UploadRounded sx={{ fontSize: 24 }} />}
          path="/bitdashboard/uploadcode"
        />
        <Item
          text="Settings"
          icon={<SettingsRounded sx={{ fontSize: 24 }} />}
          path="/bitdashboard/settings"
        />
        <Item
          text="Dummy"
          icon={<SettingsRounded sx={{ fontSize: 24 }} />}
          path="/bitdashboard/dummy"
        />
      </div>
      <div className="sidebarItemsProfile">
        <NavLink
          to="/bitdashboard/profile"
          className="sidebarItemsProfileInner"
          style={navLinkStyles}
        >
          <PersonRounded sx={{ fontSize: 60 }} />
          <p className="sidebarItemsProfileText">{!userName ? "Demo Singh" : userName}</p>
        </NavLink>
      </div>
    </div>
  );
};
export default Sidebar;

const Item = ({ text, icon, path }) => {
  const navLinkStyles = ({ isActive }) => {
    return {
      color: isActive ? "#5be49b" : "",
      backgroundColor: isActive ? "#1A3230" : "",
    };
  };
  return (
    <NavLink to={path} className="sidebarItemDiv" style={navLinkStyles}>
      <div className="sidebarItemIcon">{icon}</div>
      <div className="sidebarItemText">
        <span>{text}</span>
      </div>
    </NavLink>
  );
};
