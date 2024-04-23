import React from "react";
import ReactDOM from "react-dom/client";
import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
  Navigate,
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

const isAuthenticated = !!localStorage.getItem("userId");

const publicRoutes = [
  { path: "/", element: <Home /> },
  { path: "/aboutus", element: <About /> },
  { path: "/privacypolicy", element: <PrivacyPolicy /> },
  { path: "/signin", element: <SignIn /> },
  { path: "/signup", element: <SignUp /> },
];

const authenticatedRoutes = [
  {
    path: "/bitdashboard/*",
    element: isAuthenticated ? <BitDashboard /> : <Navigate to="/" />,
    children: [
      { index: true, element: <DashboardOutlet /> },
      { path: "dashboard", element: <Dashboard /> },
      { path: "uploadcode", element: <UploadCode /> },
      { path: "settings", element: <Settings /> },
      { path: "profile", element: <Profile /> },
      { path: "dummy", element: <Dummy /> },
    ],
  },
];

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<App />}>
      {publicRoutes.map(({ path, element }) => (
        <Route
          key={path}
          path={path}
          element={isAuthenticated ? <Navigate to="/bitdashboard" /> : element}
        />
      ))}

      {authenticatedRoutes.map(({ path, element, children }) => (
        <Route key={path} path={path} element={element}>
          {children.map(({ index, path, element }) => (
            <Route
              key={path || "index"}
              index={index}
              path={path}
              element={element}
            />
          ))}
        </Route>
      ))}
    </Route>
  )
);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
