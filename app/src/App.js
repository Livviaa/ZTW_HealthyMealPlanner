import React from 'react';
import './App.css';

import Navbar from './constants/Header/Navbar';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom'; 
import Footer from './constants/Footer/Footer';
import AddRecipe from './components/AddRecipe/AddRecipe'
import LoginForm from './components/LoginForm/LoginForm';
import RegistrationForm from './components/RegistrationForm/RegistrationForm';
import Recipes from './components/Recipes/Recipes';
import Diagrams from './components/Diagrams/Diagrams';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
          <Switch>
            <Route path='/' exact component={LoginForm} />
            <Route path='/addrecipe'  component={AddRecipe} />
            <Route path='/recipes'  component={Recipes} />
            <Route path='/diagrams'  component={Diagrams} />
            <Route path='/register'  component={RegistrationForm} />
          </Switch>
        <Footer />
      </Router>
    </div>
  )
}
export default App;