import React, { Component } from "react";
import Badge from "react-bootstrap/Badge";
import { NavBarItems } from "./constants/navBarItems";
import { Redirect } from "react-router-dom";
import { Button } from "react-bootstrap";
import "./Navbar.css";

export default class AbstractBase extends Component {
  state = {
    isUserLogged: false,
  };

  // Universal components
  infoBadge = (text, variant) => {
    return (
      <h2>
        <Badge className="mx-2 mt-2" variant={variant}>
          {text}
        </Badge>
      </h2>
    );
  };

  navbar = () => {
    return (
      <nav className="navbar navbar-expand-md bg-primary">
        <div className="navbar-collapse collapse order-1 order-md-0 dual-collapse2">
          <div className="navbar-brand mr-auto">Healthy Meal Planner</div>
        </div>
        <div className="navbar-collapse collapse order-3 dual-collapse2">
          <ul className="navbar-nav ml-auto">
            {this.state.isUserLogged &&
              NavBarItems.map((item, index) => {
                return (
                  <li key={index} className="nav-item">
                    <a className="nav-link" href={item.url}>
                      {item.title}
                    </a>
                  </li>
                );
              })}
          </ul>
        </div>
      </nav>
    );
  };

  footer = () => (
    <div className="footer">
      © 2021 Copyright: Agnieszka Bojda, Maciej Wasilewski
    </div>
  );

  redirectToProfile = () => {
    console.log("LOGGED: ", this.state.isUserLogged);
    if (this.state.isUserLogged) {
      return <Redirect to="/profile" />;
    }
  };

  logoutButton = (text, variant) => {
    return (
      <h2>
        <Button
          onClick={this.logoutCall}
          className="mx-2 mt-2"
          variant={variant}
        >
          Wyloguj
        </Button>
      </h2>
    );
  };

  // Utils
  logoutCall() {
    console.log("WYLOGUJ");
    fetch("http://localhost:8080/logout", {
      credentials: "include",
      method: "POST",
      headers: {
        "Access-Control-Allow-Origin": "*",
      },
    })
      .then((res) => res.json())
      .then((res) => window.location.reload())
      .catch((error) => window.location.reload())
      .then((res) => window.location.href='/');
  }

  formatDate = (date) => {
    var d = new Date(date),
      month = "" + (d.getMonth() + 1),
      day = "" + d.getDate(),
      year = d.getFullYear();
    if (month.length < 2) month = "0" + month;
    if (day.length < 2) day = "0" + day;
    return [year, month, day].join("-");
  };

  isUserLogged() {
    return new Promise((resolve, reject) => {
      fetch("http://localhost:8080/", {
        credentials: "include",
      })
        .then((response) => {
          response.json().then((res) => {
            if (response.status === 200) {
              resolve(true);
            } else {
              resolve(false);
            }
          });
        })
        .catch((err) => {
          // reject(err)
        });
    });
  }
}
