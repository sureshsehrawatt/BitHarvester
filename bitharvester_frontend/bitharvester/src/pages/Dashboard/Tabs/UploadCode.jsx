import React, { useState, useContext } from "react";
import BitButton from "../../../components/Assets/BitButton";
import CloudUploadRoundedIcon from "@mui/icons-material/CloudUploadRounded";
import axios from "axios";
import { backendBaseUrl } from "../../../conf/config";
import DataContext from "./DataContext";
import { useNavigate } from "react-router-dom";

const UploadCode = () => {
  const [file, setFile] = useState(null);
  const { setDataValue } = useContext(DataContext); // Access context
  const navigate = useNavigate();

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const onUpload = async () => {
    const URL = `${backendBaseUrl}/api/processcode`;
    try {
      const formData = new FormData();
      formData.append("file", file, "Solution.java");

      const config = {
        headers: {
          "Content-Type": `multipart/form-data;`,
        },
      };

      const response = await axios.post(URL, formData, config);
      console.log("File uploaded successfully:", response.data);

      setDataValue(response.data); // Set data in context
      setFile(null);

      navigate("/bitdashboard/dashboard");
    } catch (error) {
      console.error(
        "Error while signin :",
        error.response ? error.response.data : error.message
      );
    }
  };

  return (
    <div className="uploadCode">
      <div className="uploadCodeFirst">
        <div className="uploadCodeFileDropDiv">
          <form
            className="uploadCodeFileDrop"
            onClick={() => document.querySelector(".codeUpload").click()}
          >
            <input
              type="file"
              accept=".java, text/plain"
              className="codeUpload"
              onChange={handleFileChange}
              hidden
            />
            <CloudUploadRoundedIcon sx={{ fontSize: 80 }} />

            {file && (
              <div>
                <p>Selected File: {file.name}</p>
                <p>File Size: {file.size} bytes</p>
              </div>
            )}
          </form>
        </div>
        <p className="uploadCodeP">Or</p>
        <div className="uploadCodeTextPaste">
          <textarea
            name="message"
            placeholder="Message..."
            className="uploadCodeTextarea"
          />
        </div>
      </div>
      <div className="uploadCodeButtonDiv">
        <div className="uploadCodeButton">
          <BitButton label="Upload" onClick={onUpload} />
        </div>
      </div>
    </div>
  );
};

export default UploadCode;
