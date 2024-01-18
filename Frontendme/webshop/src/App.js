import logo from './logo.svg';
import './App.css';
import Navigation from './Nav';
import 'bootstrap/dist/css/bootstrap.min.css';
import Banner from './Banner.svg'
import TitleP from './TitleP.svg'
import Product from './Product'; // Import the Product component

function App() {
  return (
    <div className="App">
      <header>
        <Navigation></Navigation>
        <div className='Banner'>
          <img alt='Banner' src={Banner}></img>
        </div>
        <br></br>
        <div className='Content'>
        <img className='TitleP' alt='TitleProducts' src={TitleP}></img>
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
        </div>
      </header>
    </div>
  );
}

export default App;
