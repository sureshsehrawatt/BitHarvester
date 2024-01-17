import React from "react";
import { NavLink } from "react-router-dom";
import {
  GridViewRounded,
  SettingsRounded,
  UploadRounded,
  PersonRounded,
} from "@mui/icons-material";

const Sidebar = () => {
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
          <p className="sidebarItemsProfileText">Demo Singh</p>
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
