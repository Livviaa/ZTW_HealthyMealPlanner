import React from "react";
import AbstractBase from "../abstract_base/abstractBase";
import "./recipes.css";
import { Card, Image } from "react-bootstrap";
import ReactMarkdown from "react-markdown";

export default class Recipes extends AbstractBase {
  constructor(props) {
    super(props);
    this.state = {
      image: null,
      recipes: [],
    };
  }

  componentDidMount() {
    this.setState({ isUserLogged: true });
    document.body.style.backgroundColor = "#a7f5f5";
    this.handleGetRecipes();
  }

  handleGetRecipes = async () => {
    fetch("http://localhost:8080/recipes", {
      credentials: "include",
    })
      .then((res) => res.json())
      .then((res) => {
        this.setState({ recipes: res }, () => this.updateImages());
      })
      .catch((error) => {
        console.log("error", error);
        window.location.href = "http://localhost:3000/";
      });
  };

  parseToFile = (image) => {
    image = image.substring(0, 4) + ":" + image.substring(4, image.length);
    var idx1 = image.search("base64");
    image =
      image.substring(0, idx1) + ";" + image.substring(idx1, image.length);
    image =
      image.substring(0, idx1 + 7) +
      "," +
      image.substring(idx1 + 7, image.length);

    fetch(image)
      .then((res) => res.blob())
      .then((res) => {
        return res;
      });
  };

  parseToFileAsync(image) {
    return new Promise((resolve, reject) => {
      image = image.substring(0, 4) + ":" + image.substring(4, image.length);
      var idx1 = image.search("base64");
      image =
        image.substring(0, idx1) + ";" + image.substring(idx1, image.length);
      image =
        image.substring(0, idx1 + 7) +
        "," +
        image.substring(idx1 + 7, image.length);

      fetch(image)
        .then((res) => res.blob())
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          console.log(err);
        });
    });
  }

  updateImages = () => {
    const items = this.state.recipes;
    console.log(items);
    items.forEach((item, index) => {
      if (item.image !== null) {
        this.parseToFileAsync(item.image).then((res) => {
          console.log(res);
          console.log("asd");
          items[index].imageParsed = URL.createObjectURL(res);
          this.setState({ recipes: items });
        });
      }
    });
  };

  singleRecipe = (item) => {
    return (
      <Card className="card-re" style={{ width: "100%" }}>
        <Card.Header className="card-header-re">{item.name}</Card.Header>
        <Card.Body>
          <Card.Subtitle className="mb-2 text-muted">
            Autor: {item.author}
          </Card.Subtitle>
          <hr className="hr-re" />
          <Card.Text>
            <label className="label-re">Instrukcja przygotowania:</label>
            <ReactMarkdown source={item.instruction}></ReactMarkdown>
          </Card.Text>
          <hr className="hr-re" />
          <Image className="image-re" src={item.imageParsed} thumbnail />
        </Card.Body>
        <Card.Footer className="card-footer-re">
          {this.recipeFooter(item)}
        </Card.Footer>
      </Card>
    );
  };

  recipeFooter = (item) => {
    return (
      <div>
        <label className="label-re label-center-re">
          <span className="conclusion-re">
            Łącznie: {this.fireSvg(20)} {item.sumKcal} kcal
          </span>
          <span className="conclusion-re">
            {this.bugSvg(20)} {item.sumProtein} g
          </span>
          <span className="conclusion-re">
            {this.waterSvg(20)} {item.sumFats} g
          </span>
          <span className="conclusion-re">
            {this.bbqSvg(20)} {item.sumCarbohydrates} g{" "}
          </span>
        </label>
      </div>
    );
  };

  render() {
    return (
      <div style={{ minHeight: "100%" }}>
        {this.navbar()}
        <div className="center-re">
          {this.state.recipes.map((item, index) => {
            return (
              <div className="mb-5" key={index}>
                {this.singleRecipe(item)}
              </div>
            );
          })}
        </div>
      </div>
    );
  }
}
