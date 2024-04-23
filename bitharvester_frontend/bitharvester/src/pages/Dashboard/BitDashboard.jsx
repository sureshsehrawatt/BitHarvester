import React from "react";
import Sidebar from "./Sidebar";
import { Outlet } from "react-router-dom";
import { DataProvider } from "./Tabs/DataContext";
// import BitNavbar from "./BitNavbar";

const BitDashboard = () => {
  return (
    <div className="bitDashboard">
      <Sidebar />
      <div className="bitDashboardOutlet">
        {/* <BitNavbar /> */}
        <DataProvider>
          <Outlet />
        </DataProvider>
      </div>
    </div>
  );
};

export default BitDashboard;
