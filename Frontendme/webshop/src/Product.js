import React from 'react';

const Product = ({ imageUrl, price, name }) => {
    return (
        <div className="product">
            <img src={imageUrl} alt={name} />
            <h2>{name}</h2>
            <p>Price: ${price}</p>
        </div>
    );
};

export default Product;
