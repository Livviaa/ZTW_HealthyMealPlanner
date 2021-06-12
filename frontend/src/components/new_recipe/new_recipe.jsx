import React from "react";
import AbstractBase from "../abstract_base/abstractBase";
import "./new_recipe.css";
import { Modal, Button } from "react-bootstrap";
import MDEditor from "@uiw/react-md-editor";

export default class NewRecipe extends AbstractBase {
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
      errors: {
        recipeName: "Nazwa przepisu nie może być pusta!",
        ingredientsList: "Musi być dodany przynajmniej jeden składnik!",
        recipeText: "Treść przepisu nie może być pusta!",
      },
    };
  }

  componentDidMount() {
    this.setState({ isUserLogged: true });
    document.body.style.backgroundColor = "#a7f5f5";
    this.handleGetIngredients();
    this.sortAllIngredients();
  }

  sortAllIngredients() {
    const ingredients = this.state.allIngredients;
    ingredients.sort((a, b) =>
      a.name > b.name ? 1 : b.name > a.name ? -1 : 0
    );
    this.setState({ allIngredients: ingredients }, this.updateConclusionData);
  }

  updateConclusionData() {
    console.log("ASDASDSA");

    var sumKcal = 0;
    var sumProtein = 0;
    var sumFats = 0;
    var sumCarbohydrates = 0;

    this.state.actualIngredients.map((item, index) => {
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
      case "recipeName":
        errors.recipeName =
          value.length < 1 ? "Nazwa przepisu nie może być pusta!" : "";
        break;
      case "ingredientsList":
        errors.ingredientsList =
          value < 1 ? "Musi być dodany przynajmniej jeden składnik!" : "";
        break;
      case "recipeText":
        errors.recipeText =
          value.length < 1 ? "Treść przepisu nie może być pusta!" : "";
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
    })
      .then((res) => res.json())
      .then((res) => {
        const ingredients = [];
        res.map((item, index) => {
          ingredients.push(item);
        });
        this.setState({ allIngredients: ingredients });
      })
      .catch((error) => console.log("error", error));
  };

  validateRecipe = (errors) => {
    let valid = true;
    Object.values(errors).forEach((val) => val.length > 0 && (valid = false));
    return valid;
  };

  handleCreateRecipe() {
    if (this.validateRecipe(this.state.errors)) {
      fetch("http://localhost:8080/specific/recipes", {
        credentials: "include",
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify({
          author: null,
          image: null,
          instruction: this.state.recipeText,
          name: this.state.recipeName,
        }),
      })
        .then((res) => res.json())
        .then((res) => {
          this.state.actualIngredients.map((item, index) => {
            fetch(
              "http://localhost:8080/specific/recipes/" +
                res.message +
                "/" +
                item.ingredientId,
              {
                credentials: "include",
                method: "POST",
                headers: {
                  "Content-Type": "application/json",
                  Accept: "application/json",
                },
              }
            )
              .then((res) => res.json())
              .then((res) => {
                console.log(res);
              })
              .catch((err) => console.error(err));
          });
        })
        .catch((err) => console.error(err));
      alert("Pomyślnie dodano przepis!");
      window.location.reload();
    } else {
      alert("Uzupełnij wszystkie wymagane pola!");
    }
  }

  titleWindow = () => {
    const { errors } = this.state;
    return (
      <div className="card card-nr card-title-window-nr">
        <div className="card-body">
          <div className="form-group text-left">
            <label className="label-nr" htmlFor="inputRecipeName">
              Podaj nazwę przepisu:
            </label>
            {errors.recipeName.length > 0 && (
              <span className="error-nr">{errors.recipeName}</span>
            )}
            <input
              type="text"
              className="form-control mt-3"
              id="recipeName"
              name="recipeName"
              placeholder="Przepis na..."
              required
              onChange={this.handleChange}
              defaultValue={this.state.recipeName}
            />
          </div>
        </div>
      </div>
    );
  };

  ingredientsModalWindow = () => {
    return (
      <Modal
        show={this.state.showModal}
        onHide={() => this.setState({ showModal: false })}
      >
        <Modal.Header closeButton>
          <Modal.Title style={{ paddingLeft: "18%" }}>
            Lista wszystkich składników
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {this.state.allIngredients.map((item, index) => {
            return (
              <div key={index} style={{ margin: "2%" }}>
                <Button
                  variant="primary"
                  style={{ width: "50%", marginLeft: "25%" }}
                  onClick={() => {
                    this.handleAddIngredient(item);
                    this.setState({ showModal: false });
                  }}
                >
                  {item.name}
                </Button>
              </div>
            );
          })}
        </Modal.Body>
      </Modal>
    );
  };

  ingredientsEraseModalWindow = () => {
    return (
      <Modal
        show={this.state.showModalErase}
        onHide={() => this.setState({ showModalErase: false })}
      >
        <Modal.Header closeButton>
          <Modal.Title style={{ paddingLeft: "18%" }}>
            Czy usunąć ten składnik?
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
      <div className="card card-nr card-title-window-nr">
        <div className="card-body">
          <div className="form-group text-left">
            <label className="label-nr" htmlFor="inputRecipeName">
              Dodaj składniki:
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
              <span className="error-nr">{errors.ingredientsList}</span>
            )}
            <div>
              <button
                onClick={() => this.setState({ showModal: true })}
                className="btn btn-primary btn-nr mt-3"
              >
                Dodaj nowy składnik
              </button>
            </div>
          </div>
        </div>
      </div>
    );
  };

  handleEditorChange(value) {
    console.log(value);
    this.setState({ recipeText: value });
    this.handleChange({
      target: {
        name: "recipeText",
        value: value,
      },
    });
  }

  recipeEditor = () => {
    const { errors } = this.state;
    return (
      <div className="card card-nr card-title-window-nr">
        <div className="card-body">
          <div className="container-nr">
            <label className="label-nr" htmlFor="inputRecipeName">
              Podaj treść przepisu:
            </label>
            {errors.recipeText.length > 0 && (
              <span className="error-nr">{errors.recipeText}</span>
            )}
            <MDEditor
              className="mt-3"
              height="500"
              onChange={(value) => this.handleEditorChange(value)}
            />
            <MDEditor.Markdown />
          </div>
        </div>
      </div>
    );
  };

  addRecipeButton = () => {
    return (
      <button
        type="button"
        className="btn btn-primary btn-nr mb-3"
        onClick={() => this.handleCreateRecipe()}
      >
        Zapisz przepis
      </button>
    );
  };

  conclusionFooter = () => {
    return (
      <div>
        <label className="label-nr label-center-nr">
          <span className="conclusion-nr">
            Łącznie: {this.fireSvg()} {this.state.kcal} kcal
          </span>
          <span className="conclusion-nr">
            {this.bugSvg()} {this.state.protein} g
          </span>
          <span className="conclusion-nr">
            {this.waterSvg()} {this.state.fats} g
          </span>
          <span className="conclusion-nr">
            {this.bbqSvg()} {this.state.carbohydrates} g{" "}
          </span>
        </label>
      </div>
    );
  };

  addRecipeWindow = () => {
    return (
      <div>
        <div className="column-left-nr">.</div>
        <div className="column-center-nr">
          <div className="card card-nr">
            <div className="card-header card-header-nr">Nowy Przepis</div>
            {this.titleWindow()}
            {this.listOfIngredients()}
            {this.recipeEditor()}
            {this.addRecipeButton()}
            <div className="card-footer card-footer-nr">
              {this.conclusionFooter()}
            </div>
          </div>
        </div>
        <div className="column-right-nr"></div>
      </div>
    );
  };

  render() {
    return (
      <div style={{ minHeight: "100%" }}>
        {this.navbar()}
        <div className="center-nr">
          <div>{this.addRecipeWindow()}</div>
        </div>
        {this.ingredientsModalWindow()}
        {this.ingredientsEraseModalWindow()}
      </div>
    );
  }
}
