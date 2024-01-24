import React from 'react';
import BsktIcn from './shopping-basket-basket.svg';
import './Product.css';
import axios from 'axios';

const Product = ({ imgLink, name, price, productId, rating, size, customerUsername }) => {

    const addToBasket = () => {
        let data = JSON.stringify({
            "productId": productId,
            "imgLink": imgLink,
            "name": name,
            "price": price,
            "rating": rating,
            "size": size,
            "customerUsername": customerUsername
          });
          
          let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'http://localhost:8084/shopping-cart/items',
            headers: { 
              'Content-Type': 'application/json'
            },
            data : data
          };
          
          axios.request(config)
          .then((response) => {
            console.log(JSON.stringify(response.data));
            alert("Added successfully")
          })
          .catch((error) => {
            console.log(error);
          });
    }

    return (
        <div className="product">
            <img className='productImage' src={imgLink} alt={name} />
            <h2 className='name'>{name}</h2>
            <p className='price'>Price: ${price}</p>
            <p className='productDetails'>Rating: {rating}</p>
            <p className='productDetails'>Size: {size}</p>
            <button className='basketButton' onClick={addToBasket}>
                <img className='bsktBtnImg' alt="basket" src={BsktIcn}></img>
            </button>
        </div>
    );
};

export default Product;
