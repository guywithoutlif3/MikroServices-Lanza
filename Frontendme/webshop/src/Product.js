import React from 'react';
import BsktIcn from './shopping-basket-basket.svg'
import './Product.css'

const Product = ({ imageUrl, price, name }) => {
    return (
        <div className="product">
            <img className='productImage' src={imageUrl} alt={name} />
            <h2 className='name'>{name}</h2>
            <p className='price'>Price: ${price}</p>
            <button className='basketButton'><img className='bsktBtnImg' alt="basket" src={BsktIcn}></img></button>
        </div>
    );
};

export default Product;
