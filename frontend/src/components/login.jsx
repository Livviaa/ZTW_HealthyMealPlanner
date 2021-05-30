import React from "react";
import AbstractBase from "./abstractBase";
import "./login.css";
import diet1 from "./../images/diet1.png";

export default class Login extends AbstractBase {
  componentDidMount() {
    this.isUserLogged().then((res) => this.setState({ isUserLogged: res }))
  }

  leftColumn = () => {
    return (
      <div>
        <h2>Healthy Meal Planner</h2>
        <h3>Zadbaj o swoje zdrowie! </h3>
        <h3>Zadbaj o swoją dietę razem z nami. </h3>
        <img src={diet1} alt="Diet" style={{ width: "100%" }} />
      </div>
    );
  };

  rightColumn = () => {
    return (
      <a
        className="nav-link btn btn-primary fb-btn"
        //onClick={this.tryToLogin}
        href="http://localhost:8080/login"
      >
        Zaloguj się z Facebookiem
      </a>
    );
  };

  render() {
    return (
      <div style={{ minHeight: "100%" }}>
        {this.navbar()}
        <div className="center">
          <div className="row">
            <div className="column">{this.leftColumn()}</div>
            <div className="column">{this.rightColumn()}</div>
          </div>
        </div>
        {this.footer()}
        {this.redirectToProfile()}
      </div>
    );
  }
}
