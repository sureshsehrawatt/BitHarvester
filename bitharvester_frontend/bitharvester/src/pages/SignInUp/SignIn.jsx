import React from "react";
import { NavLink } from "react-router-dom";
import Particle from "../../components/Assets/Particle";
import Logo from "../../components/Assets/logo";
import { useFormik } from "formik";
import * as Yup from "yup";

const SignIn = () => {
  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    validationSchema: Yup.object({
      password: Yup.string().min(8, "Password should be at least 8 characters").required("Password is required"),
      email: Yup.string().email("Invalid email address").required("Email is required"),
    }),
    onSubmit: (values) => {
      console.log(values); // Handle form submission
    },
  });

  const fields = ["email", "password"];

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
          <h1 className="signInUpTitle">Sign In</h1>
          <div className="signInUpFormContainer">
            <form onSubmit={formik.handleSubmit} className="signInUpForm">
              {fields.map((field) => (
                <React.Fragment key={field}>
                  <input
                    type={field === "password" ? "password" : "text"}
                    name={field}
                    placeholder={field === "email" ? "Email Address" : "***********"}
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
              <br />
              <div className="submit">
                <button type="submit" className="customBitButton customBitButtonSignUp">
                  Sign In
                </button>
              </div>
            </form>
            <div className="signInUpBottom">
              <NavLink to="/forgotpassword" className="signInUpNavLink">
                Forgot password?
              </NavLink>
              <NavLink to="/signup" className="signInUpNavLink">
                Sign UP
              </NavLink>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default SignIn;
