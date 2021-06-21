import React from "react";
import AbstractBase from "../abstract_base/abstractBase";
import "./shopping_list.css";
import { Modal, Button, Image } from "react-bootstrap";
import MDEditor from "@uiw/react-md-editor";

export default class ShoppingList extends AbstractBase {
  constructor(props) {
    super(props);
    this.state = {
      kcal: 0,
      protein: 0,
      carbohydrates: 0,
      fats: 0,
      recipeText: null,
      indexOfIngredient: null,
      showModalErase: false,
      showModal: false,
      recipeName: null,
      allIngredients: [],
      actualIngredients: [],
      image: null,
      imageBase64: null,
      errors: {
        ingredientsList: "Lista zakupów jest pusta!",
      },
    };
  }

  componentDidMount() {
    this.setState({ isUserLogged: true });
    document.body.style.backgroundColor = "#a7f5f5";
    this.handleGetIngredients();
  }

  sortAllIngredients() {
    const ingredients = this.state.allIngredients;
    ingredients.sort((a, b) =>
      a.name > b.name ? 1 : b.name > a.name ? -1 : 0
    );
    this.setState({ allIngredients: ingredients }, this.updateConclusionData);
  }

  updateConclusionData() {
    var sumKcal = 0;
    var sumProtein = 0;
    var sumFats = 0;
    var sumCarbohydrates = 0;

    this.state.actualIngredients.forEach((item, index) => {
      var grams = 0;
      var units = item.units;
      var measureUnit = item.measureUnit;
      switch (measureUnit) {
        case "mg":
          grams = 0.001 * units;
          break;
        case "g":
        case "ml":
          grams = units;
          break;
        case "dag":
          grams = 10 * units;
          break;
        case "kg":
        case "l":
          grams = 1000 * units;
          break;
        case "szklanka (200g)":
          grams = 200 * units;
          break;
        case "łyżeczka (5g)":
          grams = 5 * units;
          break;
        case "łyżka (15g)":
          grams = 15 * units;
          break;
        case "garść (50g)":
          grams = 50 * units;
          break;
        default:
          grams = 0;
          break;
      }

      sumKcal += (grams / 100) * item.kcalPer100g;
      sumProtein += (grams / 100) * item.proteinPer100g;
      sumFats += (grams / 100) * item.fatsPer100g;
      sumCarbohydrates += (grams / 100) * item.carbohydratesPer100g;
    });

    this.setState({
      kcal: sumKcal,
      fats: sumFats,
      carbohydrates: sumCarbohydrates,
      protein: sumProtein,
    });
  }

  handleChange = (event) => {
    const { name, value } = event.target;
    let errors = this.state.errors;

    switch (name) {
      case "ingredientsList":
        errors.ingredientsList = value < 1 ? "Lista zakupów jest pusta!" : "";
        break;
      default:
        break;
    }
    this.setState({ errors, [name]: value });
  };

  handleAddIngredient = (newIngredient) => {
    const ingredients = this.state.actualIngredients;
    ingredients.push(newIngredient);
    this.setState({ actualIngredients: ingredients });
    window.sessionStorage.setItem(
      "actualIngredients",
      JSON.stringify(ingredients)
    );
    this.handleChange({
      target: {
        name: "ingredientsList",
        value: this.state.actualIngredients.length,
      },
    });
    const allIngredients = this.state.allIngredients.filter(function (el) {
      return el !== newIngredient;
    });
    this.setState({ allIngredients: allIngredients }, this.sortAllIngredients);
  };

  handleGetIngredients = async () => {
    fetch("http://localhost:8080/ingredients", {
      credentials: "include",
      kcal_per100g: this.state.kcal_per100g,
      protein_per100g: this.state.protein_per100g,
      carbohydrates_per100g: this.state.carbohydrates_per100g,
      fats_per100g: this.state.fats_per100g,
    })
      .then((res) => res.json())
      .then((res) => {
        var actualIngredients = JSON.parse(
          window.sessionStorage.getItem("actualIngredients")
        );
        if (actualIngredients === null) {
          actualIngredients = [];
          this.setState({
            errors: {
              ingredientsList: "Lista zakupów jest pusta!",
            },
          });
        } else {
          if (actualIngredients.length == 0) {
            this.setState({
              errors: {
                ingredientsList: "Lista zakupów jest pusta!",
              },
            });
          } else {
            this.setState({
              errors: {
                ingredientsList: "",
              },
            });

            actualIngredients.forEach((item, index) => {
              for (var i = 0; i < res.length; i++) {
                console.log(res[i], item)
                if (res[i] !==null && res[i].ingredientId == item.ingredientId) {
                  res[i] = null;
                }
              }
            });
          }
        }
        res = res.filter(function (el) {
          return el !== null;
        });
        console.log(res);
        console.log(actualIngredients);
        this.setState(
          { actualIngredients: actualIngredients, allIngredients: res },
          this.sortAllIngredients
        );
      })
      .catch((error) => {
        console.log("error", error);
        //window.location.href = "http://localhost:3000/";
      });
  };

  ingredientsEraseModalWindow = () => {
    return (
      <Modal
        show={this.state.showModalErase}
        onHide={() => this.setState({ showModalErase: false })}
      >
        <Modal.Header closeButton>
          <Modal.Title style={{ paddingLeft: "18%" }}>
            Czy usunąć ten produkt z listy?
          </Modal.Title>
        </Modal.Header>
        <Modal.Footer>
          <Button
            variant="secondary"
            onClick={() => {
              this.setState({ showModalErase: false });
            }}
          >
            Anuluj
          </Button>
          <Button
            variant="danger"
            onClick={() => {
              const allIngredients = this.state.allIngredients;
              allIngredients.push(
                this.state.actualIngredients[this.state.indexOfIngredient]
              );
              this.setState(
                { allIngredients: allIngredients },
                this.sortAllIngredients
              );
              const ingredients = this.state.actualIngredients;
              ingredients.splice(this.state.indexOfIngredient, 1);
              this.setState({
                actualIngredients: ingredients,
                showModalErase: false,
              });
              this.handleChange({
                target: {
                  name: "ingredientsList",
                  value: this.state.actualIngredients.length,
                },
              });
              window.sessionStorage.setItem(
                "actualIngredients",
                JSON.stringify(ingredients)
              );
            }}
          >
            Usuń
          </Button>
        </Modal.Footer>
      </Modal>
    );
  };

  listOfIngredients = () => {
    const { errors } = this.state;
    return (
      <div className="card card-sl card-title-window-sl">
        <div className="card-body">
          <div className="form-group text-left">
            <label className="label-sl" htmlFor="inputRecipeName">
              Wybrane produkty:
            </label>
            <div>
              {this.state.actualIngredients.map((item, index) => {
                return (
                  <div key={index} className="m-1 p-1 pl-2">
                    <svg
                      onClick={() =>
                        this.setState({
                          showModalErase: true,
                          indexOfIngredient: index,
                        })
                      }
                      xmlns="http://www.w3.org/2000/svg"
                      width="16"
                      height="16"
                      fill="currentColor"
                      viewBox="0 0 16 16"
                      className="bi bi-trash mr-2 hover-grow"
                    >
                      <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                      <path
                        fillRule="evenodd"
                        d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
                      />
                    </svg>
                    {item.name}
                  </div>
                );
              })}
            </div>
            {errors.ingredientsList.length > 0 && (
              <span className="error-sl">{errors.ingredientsList}</span>
            )}
          </div>
        </div>
      </div>
    );
  };

  showProductsList = () => {
    const { errors } = this.state;
    return (
      <div className="card card-sl card-title-window-sl">
        <div className="card-header card-header-sl">Wszystkie produkty:</div>
        <div className="card-body">
          <div className=" card-subtitle mb-2 text-muted">
            produkt - ilość (kcal, białko, węglowodany, tłuszcze)/100g{" "}
          </div>
          <div className="form-group text-left">
            {this.state.allIngredients.map((item, index) => {
              return (
                <div key={index} style={{ margin: "2%" }}>
                  <Button
                    variant="outline-primary"
                    size="lg"
                    block
                    style={{
                      width: "80%",
                      marginLeft: "10%",
                      whiteSpace: "normal",
                      wordWrap: "break-word",
                    }}
                    onClick={() => {
                      this.handleAddIngredient(item);
                      this.setState({ showModal: false });
                    }}
                  >
                    {item.name} &nbsp; ({item.kcalPer100g}kcal,
                    {item.proteinPer100g}g,
                    {item.carbohydratesPer100g}g,
                    {item.fatsPer100g}g)
                  </Button>
                </div>
              );
            })}
          </div>
        </div>
      </div>
    );
  };

  conclusionFooter = () => {
    return (
      <div>
        <label className="label-sl label-center-sl">
          <span className="conclusion-sl">
            {this.fireSvg(24)} {this.state.kcal.toFixed(2)} kcal
          </span>
          <span className="conclusion-sl">
            {this.bugSvg(24)} {this.state.protein.toFixed(2)} g
          </span>
          <span className="conclusion-sl">
            {this.waterSvg(24)} {this.state.fats.toFixed(2)} g
          </span>
          <span className="conclusion-sl">
            {this.bbqSvg(24)} {this.state.carbohydrates.toFixed(2)} g{" "}
          </span>
        </label>
      </div>
    );
  };

  showListWindow = () => {
    return (
      <div className="flex">
        <div className="column-center-sl">{this.showProductsList()}</div>
        <div className="column-list">
          <div>
            <div className="card card-sl" style={{ marginLeft: "25%" }}>
              <div className="card-header card-header-sl">
                Twoja lista zakupów
              </div>
              {this.listOfIngredients()}
              <div className="card-footer card-footer-sl">
                {this.conclusionFooter()}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  };

  render() {
    return (
      <div style={{ minHeight: "100%" }}>
        {this.navbar()}
        <div className="center-sl">
          <div>{this.showListWindow()}</div>
        </div>
        {this.ingredientsEraseModalWindow()}
      </div>
    );
  }
}
