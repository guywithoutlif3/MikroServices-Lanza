import logo from './logo.svg';
import './App.css';
import Navigation from './Nav';
import 'bootstrap/dist/css/bootstrap.min.css';
import Banner from './Banner.svg'
import TitleP from './TitleP.svg'
import Product from './Product'; // Import the Product component
import React, { useEffect, useState } from 'react';
import axios from 'axios';

function App() {

    // State to manage the current route
    const [currentRoute, setCurrentRoute] = useState(window.location.pathname);

    // Function to handle route changes
    const handleRouteChange = (route) => {
      setCurrentRoute(route);
      window.history.pushState({}, '', route);
    };

    useEffect(() => {
      axios.get('http://localhost:8084/product-catalog/product')
     .then(response => {
         console.log(response.data)
     })
     .catch(error => {
         console.log(error)
     });

    })
  
  return (

    <div>
    {/* Your common layout/header goes here */}
    
    {/* Add more buttons or links for other routes if needed */}
    
    {/* Conditional rendering based on the current route */}
    {currentRoute === '/Cart' && <div> Sugma </div>}

    {currentRoute !== '/Cart'  && <div className="App">
      <header>
        <Navigation></Navigation>
        <div className='Banner'>
          <img alt='Banner' src={Banner}></img>
        </div>
        <br></br>
        <img className='TitleP' alt='TitleProducts' src={TitleP}></img>
        <br></br>
        <br></br>
        <div className='Content'>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <br></br>
          <Product
            imageUrl="https://ih1.redbubble.net/image.4164480772.2740/ra,kids_tee,x900,FFFFFF:97ab1c12de,front-pad,750x1000,f8f8f8.jpg"
            price={19.99}
            name="Sample Product"
          />
          <Product
            imageUrl="https://ih1.redbubble.net/image.4164480772.2740/ra,kids_tee,x900,FFFFFF:97ab1c12de,front-pad,750x1000,f8f8f8.jpg"
            price={19.99}
            name="Sample Product"
          />
          <Product
            imageUrl="https://ih1.redbubble.net/image.4164480772.2740/ra,kids_tee,x900,FFFFFF:97ab1c12de,front-pad,750x1000,f8f8f8.jpg"
            price={19.99}
            name="Sample Product"
          />
          <Product
            imageUrl="https://ih1.redbubble.net/image.4164480772.2740/ra,kids_tee,x900,FFFFFF:97ab1c12de,front-pad,750x1000,f8f8f8.jpg"
            price={19.99}
            name="Sample Product"
          />
          <Product
            imageUrl="https://ih1.redbubble.net/image.4164480772.2740/ra,kids_tee,x900,FFFFFF:97ab1c12de,front-pad,750x1000,f8f8f8.jpg"
            price={19.99}
            name="Sample Product"
          />
        </div>
      </header>
    </div>}
  </div>
    
  );
}

export default App;
