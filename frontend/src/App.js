import React, { Component } from "react";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Login from "./components/login/login";
import Profile from "./components/profile/profile";
import NewRecipe from "./components/new_recipe/new_recipe";
import Recipes from "./components/recipes/recipes";
import Menus from "./components/menus/menus";
import Diagrams from "./components/diagrams/diagrams";
import ShoppingList from "./components/list_shopping/shopping_list";

export default class App extends Component {
  state = {
    currencies: ["USD", "EUR", "HUF", "CHF", "GBP", "JPY", "CZK", "AED", "BOB"],
  };

  render() {
    return ( 
      <Router>
        <Route exact path="/" render={() => <Login />} />
        <Route path="/profile" render={() => <Profile />} />
        <Route path="/addrecipe" render={() => <NewRecipe />} />
        <Route path="/recipes" render={() => <Recipes />} />
        <Route path="/menu" render={() => <Menus />} />
        <Route path="/diagrams" render={() => <Diagrams />} />
        <Route path="/shoppinglist" render={() => <ShoppingList />} />
      </Router>
    );
  }
}
