import React, { useState, useEffect } from "react";
import BitButton from "../../../components/Assets/BitButton";
import FilterTiltShiftRoundedIcon from "@mui/icons-material/FilterTiltShiftRounded";
import { backendBaseUrl } from "../../../conf/config";
import axios from "axios";
import { useFormik } from "formik";
import * as Yup from "yup";

const Profile = () => {
  const [image, setImage] = useState(null);
  const [isSomethingChanged, setIsSomethingChanged] = useState(false);

  useEffect(() => {
    fetchData();
  }, []);

  async function fetchData() {
    const URL = `${backendBaseUrl}/api/user/${localStorage.getItem("userId")}`;
    try {
      const response = await axios.get(URL);
      setFormData({
        fullname: response.data.fullname,
        email: response.data.email,
        password: response.data.password,
      });
      setCopyOfFormData({
        fullname: response.data.fullname,
        email: response.data.email,
        password: response.data.password,
      });
    } catch (error) {
      console.error(
        "Error while data fetching :",
        error.response ? error.response.data : error.message
      );
    }
  }

  const [formData, setFormData] = useState({
    fullname: "",
    email: "",
    password: "",
  });

  const [copyOfFormData, setCopyOfFormData] = useState({
    fullname: "",
    email: "",
    password: "",
  });

  const combinedChangeHandler = (e) => {
    formik.handleChange(e);
    handleChange(e);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
    if (copyOfFormData[name] !== value) {
      setIsSomethingChanged(true);
    } else {
      setIsSomethingChanged(false);
    }
  };

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
    onSubmit: async (values) => {
      const URL = `${backendBaseUrl}/api/user/${localStorage.getItem(
        "userId"
      )}`;
      try {
        await axios.put(URL, values);
        setIsSomethingChanged(false);
        setCopyOfFormData({
          fullname: values.fullname,
          email: values.email,
          password: values.password,
        });
      } catch (error) {
        console.error(
          "Error while updating user profile",
          error.response ? error.response.data : error.message
        );
      }
      console.log("Profile Form submitted");
    },
  });

  useEffect(() => {
    if (copyOfFormData) {
      formik.setValues(copyOfFormData);
    }
  }, [copyOfFormData]);

  const handleReset = () => {
    formik.resetForm({
      values: copyOfFormData || {},
    });
  };

  return (
    <div className="profile">
      <div className="profileFirstDiv">
        <div className="profilePhotoDiv">
          <input
            type="file"
            accept="image/*"
            className="codeUpload"
            onChange={({ target: { files } }) => {
              if (files) {
                setImage(URL.createObjectURL(files[0]));
              }
            }}
            hidden
          />

          {image ? (
            <img src={image} alt="avatar" className="profileImg" />
          ) : (
            <FilterTiltShiftRoundedIcon sx={{ fontSize: 150 }} />
          )}

          <p
            className="profileChangePhotoButton"
            onClick={() => document.querySelector(".codeUpload").click()}
          >
            Change Photo
          </p>
        </div>
        <div className="profileInputsDiv">
          <div className="signInUpFormContainer profileFormContainer">
            <form onSubmit={formik.handleSubmit} className="signInUpForm">
              {["fullname", "email", "password"].map((field) => (
                <React.Fragment key={field}>
                  <input
                    type={field === "password" ? "password" : "text"}
                    name={field}
                    className="signInUpInput"
                    onChange={combinedChangeHandler}
                    onBlur={formik.handleBlur}
                    value={formik.values[field]}
                  />
                  {formik.touched[field] && formik.errors[field] && (
                    <div className="formErrorDiv">{formik.errors[field]}</div>
                  )}
                  <br />
                </React.Fragment>
              ))}
            </form>
          </div>
        </div>
      </div>
      <div className="profileButtonsDiv">
        <div className="profileButtonsFirstDiv">
          {isSomethingChanged ? (
            <>
              <div className="profileButtonsFirstDivItem1">
                <BitButton label="Save" onClick={formik.handleSubmit} />
              </div>
              <div>
                <button className="customBitButton" onClick={handleReset}>
                  Reset
                </button>
              </div>
            </>
          ) : null}
        </div>
        <div className="profileButtonsFirstDiv">
          <BitButton label="Delete Account" onClick={handleReset} />
        </div>
      </div>
    </div>
  );
};

export default Profile;
