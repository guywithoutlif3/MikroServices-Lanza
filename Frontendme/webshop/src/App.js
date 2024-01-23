import logo from './logo.svg';
import './App.css';
import Navigation from './Nav';
import 'bootstrap/dist/css/bootstrap.min.css';
import Banner from './Banner.svg'
import TitleP from './TitleP.svg'
import Product from './Product'; // Import the Product component
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Cart from './Cart';

function App() {

    // State to manage the current route
    const [currentRoute, setCurrentRoute] = useState(window.location.pathname);
    const [products, setProducts] = useState([]);
    const [customerUsername, setCustomerUsername] = useState('test');
    // Function to handle route changes
    const handleRouteChange = (route) => {
      setCurrentRoute(route);
      window.history.pushState({}, '', route);
    };

    useEffect(() => {
      console.log(customerUsername);
      axios.get('http://localhost:8084/product-catalog/product')
     .then(response => {
        setProducts(response.data)
        console.log(response.data)
     })
     .catch(error => {
         console.log(error)
     });

    }, []);
  
  return (

    <div>
    {/* Your common layout/header goes here */}
    
    {/* Add more buttons or links for other routes if needed */}
    
    {/* Conditional rendering based on the current route */}
    {currentRoute === '/Cart' && <Cart costumerUsername={customerUsername}/>}

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
          {products.map(product => (
                <Product 
                    key={product.productId}
                    imgLink={product.imgLink}
                    name={product.name}
                    price={product.price}
                    productId={product.productId}
                    rating={product.rating}
                    size={product.size}
                    customerUsername={customerUsername}
                />
            ))}
        </div>
      </header>
    </div>}
  </div>
    
  );
}

export default App;
