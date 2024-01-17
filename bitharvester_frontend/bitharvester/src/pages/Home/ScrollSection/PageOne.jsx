import React from 'react'
import img1 from '../../../assets/images/img1.svg';

const PageOne = () => {
  return (
    <div className='pageParentDiv'>
        <div className='pageFirstDiv'>
        <h1>Code Visualization</h1>
        <p>BitHarvester turns complex code into vibrant visuals, simplifying algorithms. Easily navigate your codebase for a deeper understanding of your software architecture</p>
        </div>
        <div className='pageSecondDiv'>
          <img src={img1} alt="fgfhj" style={{"width" : "600px", "height" : "600px"}}/>
        </div>
    </div>
  )
}

export default PageOne