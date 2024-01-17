import React, { useState } from "react";
import BitButton from "../../../components/Assets/BitButton";
import CloudUploadRoundedIcon from '@mui/icons-material/CloudUploadRounded';

const UploadCode = () => {
  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setSelectedFile(file);
  };

  return (
    <div className="uploadCode">
      <div className="uploadCodeFirst">
        <div className="uploadCodeFileDropDiv">
          <form className="uploadCodeFileDrop" onClick={()=>document.querySelector(".codeUpload").click()}>
            <input type="file" accept=".java, text/plain" className="codeUpload" onChange={handleFileChange} hidden />
            <CloudUploadRoundedIcon sx={{ fontSize: 80 }} />

            {selectedFile && (
              <div>
                <p>Selected File: {selectedFile.name}</p>
                <p>File Size: {selectedFile.size} bytes</p>
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
          <BitButton label="Upload" />
        </div>
      </div>
    </div>
  );
};

export default UploadCode;
