import HeroSection from "./HeroSection.jsx";
import ScrollSection from "./ScrollSection/ScrollSection.jsx";
import Q from "./Q.jsx";
import ContactUs from "./ContactUs.jsx";
import Footer from "../../components/Footer/Footer.jsx";
import Particle from "../../components/Assets/Particle.jsx";
import Logo from "../../components/Assets/logo.jsx";
import BitButton from "../../components/Assets/BitButton.jsx";
import { NavLink } from "react-router-dom";

function Home() {
  const handleClick = () => {
    console.log();
  };
  return (
    <div>
      <Particle />
      <div className="homeNavDiv">
        <Logo />
        <NavLink to="signin" className="getButton">
          <BitButton label="Get started" onClick={handleClick} />
        </NavLink>
      </div>
      <HeroSection />
      <ScrollSection />
      <Q />
      <ContactUs />
      <Footer />
    </div>
  );
}

export default Home;
