import { useEffect, useState } from "react";
import axios from "axios";

const Cart = ({costumerUsername}) => {

    const [productsInCart, setProductsInCart] = useState([]);



    useEffect(() => {
          console.log(costumerUsername)
          let config = {
            method: 'get',
            maxBodyLength: Infinity,
            url: `http://localhost:8084/shopping-cart/items/${costumerUsername}`,
            headers: { 
              'Content-Type': 'application/json'
            }
          };
          
          axios.request(config)
          .then((response) => {
            setProductsInCart(response.data)
          })
          .catch((error) => {
            console.log(error);
          });
          
    }, [])

    const placeOrder = () => {
        
        let data = JSON.stringify({
            "customerName": `${costumerUsername}`
          });
          
          let config = {
            method: 'post',
            maxBodyLength: Infinity,
            url: 'http://localhost:8084/shopping-cart/items/placeorder',
            headers: { 
              'Content-Type': 'application/json'
            },
            data : data
          };
          
          axios.request(config)
          .then((response) => {
            console.log(JSON.stringify(response.data));
            alert("Order successfull!!!");
          })
          .catch((error) => {
            console.log(error);
          });
          
    }

    return (
        <>
        <h1>Your cart {costumerUsername}: </h1>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Size</th>
                        <th>Rating</th>
                        <th>Image</th>
                        <th>Customer Username</th>
                    </tr>
                </thead>
                <tbody>
                    {productsInCart.map(product => (
                        <tr key={product.productId}>
                            <td>{product.name}</td>
                            <td>${product.price}</td>
                            <td>{product.size}</td>
                            <td>{product.rating}</td>
                            <td>
                                <img src={product.imgLink} alt={product.name} style={{ width: '100px' }} />
                            </td>
                        </tr>
                    ))}
                </tbody>
        </table>
        <p>Order for: {costumerUsername}</p>
        <button onClick={placeOrder}>Place Order</button>
        </>
    )
}

export default Cart;