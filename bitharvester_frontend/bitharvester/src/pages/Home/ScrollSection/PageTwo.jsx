import React from 'react'
import img2 from '../../../assets/images/img2.svg'

const PageTwo = () => {
  return (
    <div className='pageParentDiv'>
        <div className='pageFirstDiv'>
        <h1>Code Profiling</h1>
        <p>Identify bottlenecks and performance issues by visualizing code execution times and resource usage</p>
        </div>
        <div className='pageSecondDiv'>
          <img src={img2} alt="fgfhj" style={{"width" : "600px", "height" : "600px"}}/>
        </div>
    </div>
  )
}

export default PageTwo