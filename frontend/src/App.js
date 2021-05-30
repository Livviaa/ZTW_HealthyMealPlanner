import React, { Component } from "react";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Login from "./components/login";
import Profile from "./components/profile";

export default class App extends Component {
  state = {
    currencies: ["USD", "EUR", "HUF", "CHF", "GBP", "JPY", "CZK", "AED", "BOB"],
  };

  render() {
    return ( 
      <Router>
        <Route exact path="/" render={() => <Login />} />
        <Route path="/profile" render={() => <Profile />} />
      </Router>
    );
  }
}
