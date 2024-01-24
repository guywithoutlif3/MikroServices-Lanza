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
  const [customerUsername, setCustomerUsername] = useState(sessionStorage.getItem('username'));



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
  // User registration Code ------
  const [formData, setFormData] = useState({
    firstname: '',
    lastname: '',
    username: '',
    postalCode: '',
    address: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const requestData = JSON.stringify(formData);

    const config = {
      method: 'post',
      maxBodyLength: Infinity,
      url: 'http://localhost:8084/product-catalog/customer',
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*', // Add this header for CORS
      },
      data: requestData,
    };

    axios
      .request(config)
      .then((response) => {
        console.log(JSON.stringify(response.data));
      })
      .catch((error) => {
        console.log(error);
      });
      console.log(formData.username)
      sessionStorage.setItem('username',formData.username);
      setTimeout(window.location.replace("http://localhost:3000/"), 1000);

  };
  // ------------------------------------------------------------------
  return (

    <div>
      {/* Your common layout/header goes here */}

      {/* Add more buttons or links for other routes if needed */}

      {/* Conditional rendering based on the current route */}
      {currentRoute === '/Cart' && <Cart costumerUsername={customerUsername} />}

      {currentRoute === '/NewUser' && <div>

        <h2>User Registration</h2>

        <form onSubmit={handleSubmit}>
          <label>
            First Name:
            <input
              type="text"
              name="firstname"
              value={formData.firstname}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Last Name:
            <input
              type="text"
              name="lastname"
              value={formData.lastname}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Username:
            <input
              type="text"
              name="username"
              value={formData.username}
              onChange={handleChange}
            />
          </label>
          <br />
          <label>
            Postal Code:
            <input
              type="text"
              name="postalCode"
              value={formData.postalCode}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <label>
            Address:
            <input
              type="text"
              name="address"
              value={formData.address}
              onChange={handleChange}
              required
            />
          </label>
          <br />
          <button type="submit">Submit</button>
        </form>


      </div>}

      {currentRoute !== '/Cart' && currentRoute !== '/NewUser' && <div className="App">

        {customerUsername === null ? (
          // Render this when customerUsername is empty
          <p>Please go to the <a href="/NewUser">new user page</a>.</p>
        ) : (
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
        )}


      </div>}
    </div>

  );
}

export default App;
