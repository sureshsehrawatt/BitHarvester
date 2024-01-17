import React from 'react'
import img3 from '../../../assets/images/img3.svg'

const PageThree = () => {
  return (
    <div className='pageParentDiv'>
        <div className='pageFirstDiv'>
        <h1>Code Metrics</h1>
        <p>Display code metrics such as complexity, lines of code, and test coverage visually. This can assist in identifying areas that may need refactoring or optimization</p>
        </div>
        <div className='pageSecondDiv'>
          <img src={img3} alt="fgfhj" style={{"width" : "600px", "height" : "600px"}}/>
        </div>
    </div>
  )
}

export default PageThree