import React from 'react';
import PropTypes from 'prop-types';
import '../../css/BitButton.css';

const BitButton = ({ onClick, label }) => {
  return (
    <button className="bitButton" onClick={onClick}>
      {label}
    </button>
  );
};

BitButton.propTypes = {
  onClick: PropTypes.func.isRequired,
  label: PropTypes.string.isRequired,
};

export default BitButton;
