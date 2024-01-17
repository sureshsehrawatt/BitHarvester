import React from "react";
import { NavLink } from "react-router-dom";
import Particle from "../../components/Assets/Particle";
import Logo from "../../components/Assets/logo";
import { useFormik } from "formik";
import * as Yup from "yup";
import axios from "axios";
import { backendBaseUrl } from "../../conf/config";

const SignUp = () => {
  const formik = useFormik({
    initialValues: {
      fullname: "",
      email: "",
      password: "",
    },
    validationSchema: Yup.object({
      fullname: Yup.string()
        .min(3, "Name should be at least 3 characters")
        .required("Name is required"),
      password: Yup.string()
        .min(8, "Password should be at least 8 characters")
        .required("Password is required"),
      email: Yup.string()
        .email("Invalid email address")
        .required("Email is required"),
    }),
    onSubmit: async (user, { resetForm }) => {
      // preventDefault();
      const URL = `${backendBaseUrl}/api/user/signup`;
      try {
        const response = await axios.post(URL, user);
        console.log("User registered successfully:", response.data);
        resetForm();
      } catch (error) {
        console.error(
          "Error registering user:",
          error.response ? error.response.data : error.message
        );
      }
    },
  });

  return (
    <>
      <Particle />
      <div className="signInUpLogo">
        <NavLink to="/">
          <Logo />
        </NavLink>
      </div>
      <div className="signInUp">
        <div className="signIn">
          <h1 className="signInUpTitle">Sign Up</h1>
          <div className="signInUpFormContainer">
            <form onSubmit={formik.handleSubmit} className="signInUpForm">
              {["fullname", "email", "password"].map((field) => (
                <React.Fragment key={field}>
                  <input
                    type={field === "password" ? "password" : "text"}
                    name={field}
                    placeholder={
                      field === "fullname"
                        ? "Full Name"
                        : field === "email"
                        ? "Email Address"
                        : "***********"
                    }
                    className="signInUpInput"
                    onChange={formik.handleChange}
                    onBlur={formik.handleBlur}
                    value={formik.values[field]}
                  />
                  {formik.touched[field] && formik.errors[field] && (
                    <div className="formErrorDiv">{formik.errors[field]}</div>
                  )}
                  <br />
                </React.Fragment>
              ))}
              <div className="submit">
                <button
                  type="submit"
                  className="customBitButton customBitButtonSignUp"
                >
                  Sign Up
                </button>
              </div>
            </form>
            <div className="signInUpBottom">
              <p className="signInUpP">
                Already have an account?{" "}
                <b>
                  <NavLink to="/signin" className="signInUpNavLink">
                    Sign In
                  </NavLink>
                </b>
              </p>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default SignUp;
