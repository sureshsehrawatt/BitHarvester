import React from "react";
import img4 from "../../../assets/images/img4.svg";

const PageFour = () => {
  return (
    <div className="pageParentDiv">
      <div className="pageFirstDiv">
        <h1>Cross-Language Support</h1>
        <p>
          No matter if you're coding in Python, Java, C++, or any other
          language, BitHarvester has got you covered.
        </p>
        <p style={{ fontSize: '25px', color: 'black' ,backgroundColor: 'yellow'  }}>
          (currently available for <b>JAVA</b>)
        </p>
      </div>
      <div className="pageSecondDiv">
        <img
          src={img4}
          alt="fgfhj"
          style={{ width: "600px", height: "600px" }}
        />
      </div>
    </div>
  );
};

export default PageFour;
