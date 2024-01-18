import React from "react";
import Sidebar from "./Sidebar";
import { Outlet } from "react-router-dom";
// import BitNavbar from "./BitNavbar";

const BitDashboard = () => {

  return (
    <div className="bitDashboard">
      <Sidebar />
      <div className="bitDashboardOutlet">
        {/* <BitNavbar /> */}
        <Outlet />
      </div>
    </div>
  );
};

export default BitDashboard;
