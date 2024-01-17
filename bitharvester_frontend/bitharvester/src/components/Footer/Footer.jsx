import React from "react";
import { Link, NavLink } from "react-router-dom";

const Footer = () => {
  const linkedinUrl = "https://www.linkedin.com/in/sureshsehrawat/";
  const boldbitUrl = "https://www.boldbiturl.com";
  return (
    <div className="footer">
      <div>
        <aside className="footerAside">
          <h2 className="footerH2">BitHarvester</h2>
          <h3 className="footerH3">Code Investigation + Monitoring</h3>
        </aside>

        <footer className="footerF">
          <p className="footerCells">
            <NavLink to="/aboutus" >
              About Us
            </NavLink>
          </p>
          <p className="footerCells">
            <NavLink to="/privacypolicy" >
              Privacy Policy
            </NavLink>
          </p>
          <p className="footerCells">
            <Link to={linkedinUrl} target="_blank">
              LinkedIn
            </Link>
          </p>
          <p className="footerCells">
            <Link to={boldbitUrl} target="_blank">
              BoldBit.com
            </Link>
          </p>
        </footer>
      </div>
    </div>
  );
};

export default Footer;
