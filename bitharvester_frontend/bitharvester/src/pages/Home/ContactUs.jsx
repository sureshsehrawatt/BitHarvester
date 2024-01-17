import React, { useState } from "react";
import BitButton from "../../components/Assets/BitButton";

const ContactUs = () => {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    message: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // You can add your logic here to handle form submission, such as sending the data to a server or performing validation.
    console.log("Form submitted:", formData);
    // Reset the form after submission
    setFormData({
      name: "",
      email: "",
      message: "",
    });
  };

  return (
    <div className="contactUs">
      <h1 className="usH1">Contact Us</h1>
      <div className="form-container">
        <form onSubmit={handleSubmit} className="usForm">
          <input
            type="text"
            name="name"
            placeholder="Full Name"
            value={formData.name}
            onChange={handleChange}
            className="usInput"
          />
          <br />
          <input
            type="email"
            name="email"
            placeholder="Email Address"
            value={formData.email}
            onChange={handleChange}
            className="usInput"
          />
          <br />
          <textarea
            name="message"
            placeholder="Message..."
            value={formData.message}
            onChange={handleChange}
            className="usTextarea"
            rows={6} 
          />
          <br />
          <div className="submit">
            <BitButton
              label="Submit"
              onClick={handleSubmit}
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default ContactUs;
