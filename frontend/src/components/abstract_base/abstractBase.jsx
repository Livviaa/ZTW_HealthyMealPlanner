import React, { Component } from "react";
import Badge from "react-bootstrap/Badge";
import { NavBarItems } from "../constants/navBarItems";
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
      <nav className="navbar fixed-top navbar-expand-md">
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
                      <span className="mr-2">{item.svg}</span>
                      <span>{item.title}</span>
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
      .then((res) => (window.location.href = "/"));
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
      console.log("SPRAWDZANIE");
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
          console.log(err);
        });
    });
  }

  // SVGS
  fireSvg = (sizePx) => {
    return (
      <svg
        xmlns="http://www.w3.org/2000/svg"
        enableBackground="new 0 0 24 24"
        height={sizePx + "px"}
        viewBox="0 0 24 24"
        width={sizePx + "px"}
        fill="#000000"
      >
        <g>
          <rect fill="none" height="24" width="24" y="0" />
        </g>
        <g>
          <path d="M19.48,12.35c-1.57-4.08-7.16-4.3-5.81-10.23c0.1-0.44-0.37-0.78-0.75-0.55C9.29,3.71,6.68,8,8.87,13.62 c0.18,0.46-0.36,0.89-0.75,0.59c-1.81-1.37-2-3.34-1.84-4.75c0.06-0.52-0.62-0.77-0.91-0.34C4.69,10.16,4,11.84,4,14.37 c0.38,5.6,5.11,7.32,6.81,7.54c2.43,0.31,5.06-0.14,6.95-1.87C19.84,18.11,20.6,15.03,19.48,12.35z M10.2,17.38 c1.44-0.35,2.18-1.39,2.38-2.31c0.33-1.43-0.96-2.83-0.09-5.09c0.33,1.87,3.27,3.04,3.27,5.08C15.84,17.59,13.1,19.76,10.2,17.38z" />
        </g>
      </svg>
    );
  };

  bugSvg = (sizePx) => {
    return (
      <svg
        xmlns="http://www.w3.org/2000/svg"
        height={sizePx + "px"}
        viewBox="0 0 24 24"
        width={sizePx + "px"}
        fill="#000000"
      >
        <path d="M0 0h24v24H0V0z" fill="none" />
        <path d="M20 8h-2.81c-.45-.78-1.07-1.45-1.82-1.96L17 4.41 15.59 3l-2.17 2.17C12.96 5.06 12.49 5 12 5s-.96.06-1.41.17L8.41 3 7 4.41l1.62 1.63C7.88 6.55 7.26 7.22 6.81 8H4v2h2.09c-.05.33-.09.66-.09 1v1H4v2h2v1c0 .34.04.67.09 1H4v2h2.81c1.04 1.79 2.97 3 5.19 3s4.15-1.21 5.19-3H20v-2h-2.09c.05-.33.09-.66.09-1v-1h2v-2h-2v-1c0-.34-.04-.67-.09-1H20V8zm-4 4v3c0 .22-.03.47-.07.7l-.1.65-.37.65c-.72 1.24-2.04 2-3.46 2s-2.74-.77-3.46-2l-.37-.64-.1-.65C8.03 15.48 8 15.23 8 15v-4c0-.23.03-.48.07-.7l.1-.65.37-.65c.3-.52.72-.97 1.21-1.31l.57-.39.74-.18c.31-.08.63-.12.94-.12.32 0 .63.04.95.12l.68.16.61.42c.5.34.91.78 1.21 1.31l.38.65.1.65c.04.22.07.47.07.69v1zm-6 2h4v2h-4zm0-4h4v2h-4z" />
      </svg>
    );
  };

  waterSvg = (sizePx) => {
    return (
      <svg
        xmlns="http://www.w3.org/2000/svg"
        enableBackground="new 0 0 24 24"
        height={sizePx + "px"}
        viewBox="0 0 24 24"
        width={sizePx + "px"}
        fill="#000000"
      >
        <rect fill="none" height="24" width="24" />
        <path d="M12,2c-5.33,4.55-8,8.48-8,11.8c0,4.98,3.8,8.2,8,8.2s8-3.22,8-8.2C20,10.48,17.33,6.55,12,2z M12,20c-3.35,0-6-2.57-6-6.2 c0-2.34,1.95-5.44,6-9.14c4.05,3.7,6,6.79,6,9.14C18,17.43,15.35,20,12,20z M7.83,14c0.37,0,0.67,0.26,0.74,0.62 c0.41,2.22,2.28,2.98,3.64,2.87c0.43-0.02,0.79,0.32,0.79,0.75c0,0.4-0.32,0.73-0.72,0.75c-2.13,0.13-4.62-1.09-5.19-4.12 C7.01,14.42,7.37,14,7.83,14z" />
      </svg>
    );
  };

  bbqSvg = (sizePx) => {
    return (
      <svg
        xmlns="http://www.w3.org/2000/svg"
        enableBackground="new 0 0 24 24"
        height={sizePx + "px"}
        viewBox="0 0 24 24"
        width={sizePx + "px"}
        fill="#000000"
      >
        <g>
          <rect fill="none" height="24" width="24" />
        </g>
        <g>
          <g>
            <path d="M17,22c1.66,0,3-1.34,3-3s-1.34-3-3-3c-1.3,0-2.4,0.84-2.82,2H9.14l1.99-3.06C11.42,14.98,11.71,15,12,15 s0.58-0.02,0.87-0.06l1.02,1.57c0.42-0.53,0.96-0.95,1.6-1.21l-0.6-0.93C17.31,13.27,19,10.84,19,8H5c0,2.84,1.69,5.27,4.12,6.37 l-3.95,6.08c-0.3,0.46-0.17,1.08,0.29,1.38h0c0.46,0.3,1.08,0.17,1.38-0.29l1-1.55h6.34C14.6,21.16,15.7,22,17,22z M17,18 c0.55,0,1,0.45,1,1c0,0.55-0.45,1-1,1s-1-0.45-1-1C16,18.45,16.45,18,17,18z M7.42,10h9.16c-0.77,1.76-2.54,3-4.58,3 S8.19,11.76,7.42,10z" />
            <path d="M9.41,7h1c0.15-1.15,0.23-1.64-0.89-2.96C9.1,3.54,8.84,3.27,9.06,2H8.07C7.86,3.11,8.1,4.05,8.96,4.96 C9.18,5.2,9.75,5.63,9.41,7z" />
            <path d="M11.89,7h1c0.15-1.15,0.23-1.64-0.89-2.96c-0.42-0.5-0.68-0.78-0.46-2.04h-0.99c-0.21,1.11,0.03,2.05,0.89,2.96 C11.67,5.2,12.24,5.63,11.89,7z" />
            <path d="M14.41,7h1c0.15-1.15,0.23-1.64-0.89-2.96C14.1,3.54,13.84,3.27,14.06,2h-0.99c-0.21,1.11,0.03,2.05,0.89,2.96 C14.18,5.2,14.75,5.63,14.41,7z" />
          </g>
        </g>
      </svg>
    );
  };

  plusSvg = (sizePx) => {
    return (
      <svg
        xmlns="http://www.w3.org/2000/svg"
        height={sizePx + "px"}
        viewBox="0 0 24 24"
        width={sizePx + "px"}
        fill="#007bff"
      >
        <path d="M0 0h24v24H0V0z" fill="none" />
        <path d="M13 7h-2v4H7v2h4v4h2v-4h4v-2h-4V7zm-1-5C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 18c-4.41 0-8-3.59-8-8s3.59-8 8-8 8 3.59 8 8-3.59 8-8 8z" />
      </svg>
    );
  };
}
