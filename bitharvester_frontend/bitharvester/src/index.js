import React from "react";
import ReactDOM from "react-dom/client";
import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";
import "./index.css";
import App from "./pages/App";
import SignIn from "./pages/SignInUp/SignIn";
import SignUp from "./pages/SignInUp/SignUp";
import Home from "./pages/Home/home";
import About from "./pages/Home/About";
import PrivacyPolicy from "./pages/Home/PrivacyPolicy";
import BitDashboard from "./pages/Dashboard/BitDashboard";
import DashboardOutlet from "./pages/Dashboard/DashboardOutlet";
import Dashboard from "./pages/Dashboard/Tabs/Dashboard";
import UploadCode from "./pages/Dashboard/Tabs/UploadCode";
import Settings from "./pages/Dashboard/Tabs/Settings";
import Dummy from "./components/Demo/Dummy";
import Profile from "./pages/Dashboard/Tabs/Profile";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route exact path="/" element={<App />}>
      <Route exact path="" element={<Home />} />
      <Route exact path="aboutus" element={<About />} />
      <Route exact path="privacypolicy" element={<PrivacyPolicy />} />
      <Route exact path="signin" element={<SignIn />} />
      <Route exact path="signup" element={<SignUp />} />
      <Route exact path="bitdashboard/*" element={<BitDashboard />}>
        <Route exact index element={<DashboardOutlet />} />
        <Route exact path="dashboard" element={<Dashboard />} />
        <Route exact path="uploadcode" element={<UploadCode />} />
        <Route exact path="settings" element={<Settings />} />
        <Route exact path="profile" element={<Profile />} />
        <Route exact path="dummy" element={<Dummy />} />
      </Route>
    </Route>
  )
);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
