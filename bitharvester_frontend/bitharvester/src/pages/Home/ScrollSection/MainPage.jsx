import React from "react";
import main from "../../../assets/images/main.svg";

const MainPage = () => {
  return (
    <div className="pageParentDiv">
      <div className="pageFirstDiv">
        <p>
          Meet <span className="mainPageSpan">BitHarvester</span>, our
          cutting-edge Java performance project. It optimizes IT efficiency,
          digital transformation, and innovation, elevating Java code
          performance standards in software development.
        </p>
      </div>

      <div className="pageSecondDiv">
        <img
          src={main}
          alt="fgfhj"
          style={{ width: "600px", height: "600px" }}
        />
      </div>
    </div>
  );
};

export default MainPage;
