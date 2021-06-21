import React from "react";
import AbstractBase from "../abstract_base/abstractBase";
import "./menus.css";
import { Card, Image, Modal, Button } from "react-bootstrap";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";

export default class Menus extends AbstractBase {
  constructor(props) {
    super(props);
    this.state = {
      user: null,
      actualDailyMenu: null,
      showModal: false,
      allRecipes: [],
      visibleNames: null,
      actualIndex: null,
      idxs_to_remove: null,
    };
  }

  componentDidMount() {
    this.setState({ isUserLogged: true });
    document.body.style.backgroundColor = "#a7f5f5";
    this.handleGetUser();
    this.handleGetRecipes();

    // Init actual day
    const value = new Date();
    value.setHours(value.getHours() + 2);
    this.getActualDailyMenu(value.toISOString().substring(0, 10));
  }

  handleGetRecipes = async () => {
    fetch("http://localhost:8080/recipes", {
      credentials: "include",
    })
      .then((res) => res.json())
      .then((res) => {
        var visibleNames = res.map((item, idx) => item.name.toLowerCase());
        this.setState({ allRecipes: res, visibleNames: visibleNames });
      })
      .catch((error) => {
        console.log("error", error);
        window.location.href = "http://localhost:3000/";
      });
  };

  handleGetUser = async () => {
    fetch("http://localhost:8080/specific/users", {
      credentials: "include",
    })
      .then((res) => res.json())
      .then((res) => {
        this.setState({ user: res }, () => console.log("menus loaded"));
      })
      .catch((error) => {
        window.location.href = "http://localhost:3000/";
      });
  };

  getActualDailyMenu = (actualDate) => {
    console.log("XDDD", actualDate);
    fetch("http://localhost:8080/specific/menus/" + actualDate, {
      credentials: "include",
    })
      .then((res) => res.json())
      .then((res) => {
        this.setState({ actualDailyMenu: res });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  calendarChanged = (value, event) => {
    value.setHours(value.getHours() + 2);
    const clickedDate = value.toISOString().substring(0, 10);
    console.log(clickedDate);

    fetch("http://localhost:8080/specific/menus/" + clickedDate, {
      credentials: "include",
      method: "put",
    })
      .then((res) => res.json())
      .then((res) => {
        this.getActualDailyMenu(clickedDate);
      })
      .catch((error) => {
        console.log("error", error);
        this.getActualDailyMenu(clickedDate);
      });
  };

  dailyRecommendedMacroelements = () => {
    return (
      <Card className="card-me" style={{ width: "60%", marginLeft: "30%" }}>
        <Card.Header className="card-header-me">
          Twoje dzienne zapotrzebowanie
        </Card.Header>
        <Card.Body>
          <Card.Text>
            <div>
              <div className="conclusion-me-2">
                {this.fireSvg(30)}{" "}
                {this.state.user &&
                  this.state.user.recommendedDailyKcal.toFixed(2)}{" "}
                kcal
              </div>
              <div className="conclusion-me-2">
                {this.bugSvg(30)}{" "}
                {this.state.user &&
                  this.state.user.recommendedDailyProtein.toFixed(2)}{" "}
                g
              </div>
              <div className="conclusion-me-2">
                {this.waterSvg(30)}{" "}
                {this.state.user &&
                  this.state.user.recommendedDailyFats.toFixed(2)}{" "}
                g
              </div>
              <div className="conclusion-me-2">
                {this.bbqSvg(30)}{" "}
                {this.state.user &&
                  this.state.user.recommendedDailyCarbohydrates.toFixed(2)}{" "}
                g
              </div>
            </div>
          </Card.Text>
        </Card.Body>
      </Card>
    );
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

  singleMeal = (item_2, index, index_2) => {
    this.parseToFileAsync(item_2.imageBytes).then((res) => {
      if (item_2.image === null) {
        console.log("RELOAD");
        item_2.image = URL.createObjectURL(res);
        this.forceUpdate();
      }
    });
    return (
      <Card className="card-me" style={{ width: "100%", marginLeft: "0%" }}>
        <Card.Header>
          <div key={index_2}>
            <svg
              onClick={() =>
                this.setState({
                  idxs_to_remove: [item_2.recipeId, index],
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
            {item_2.name}
          </div>
        </Card.Header>
        <Card.Body>
          <Card.Text>
            <div class="row">
              <div class="column-1-me">
                <Image className="image-me" src={item_2.image} thumbnail />
              </div>
              <div class="column-2-me">
                <label className="label-me">Składniki:</label>
                <ul>
                  {item_2.ingredients.map((it, idx) => {
                    return (
                      <li key={idx} className="m-1 p-1 pl-2">
                        {it.name}
                      </li>
                    );
                  })}
                </ul>
              </div>
              <div class="column-3-me">
                <div>
                  <div className="conclusion-me">
                    {this.fireSvg(30)} {item_2.sumKcal.toFixed(2)} kcal
                  </div>
                  <div className="conclusion-me">
                    {this.bugSvg(30)} {item_2.sumProtein.toFixed(2)} g
                  </div>
                  <div className="conclusion-me">
                    {this.waterSvg(30)} {item_2.sumFats.toFixed(2)} g
                  </div>
                  <div className="conclusion-me">
                    {this.bbqSvg(30)} {item_2.sumCarbohydrates.toFixed(2)} g
                  </div>
                </div>
              </div>
            </div>
          </Card.Text>
        </Card.Body>
      </Card>
    );
  };

  singleMealContainer = (item, index) => {
    return (
      <Card className="card-me" style={{ width: "100%" }}>
        <Card.Header className="card-header-me">
          {index === 0 ? "Śniadanie" : index === 1 ? "Obiad" : "Kolacja"}
        </Card.Header>
        <Card.Body>
          <Card.Text>
            <label className="label-me">
              {this.state.actualDailyMenu.meals[index].recipes.map(
                (item_2, index_2) => {
                  return this.singleMeal(item_2, index, index_2);
                }
              )}
            </label>
          </Card.Text>
          <button
            className="btn btn-outline-primary"
            variant="secondary"
            onClick={() => {
              this.setState({ showModal: true, actualIndex: index });
            }}
          >
            {this.plusSvg(24)} Dodaj nowy przepis
          </button>
        </Card.Body>
        <Card.Footer className="card-footer-me">
          {this.recipeFooter(item, index)}
        </Card.Footer>
      </Card>
    );
  };

  recipeFooter = (item, index) => {
    return (
      <div>
        <label className="label-me label-center-me">
          <span className="conclusion-me">
            Łącznie: {this.fireSvg(20)} {item.sumKcal.toFixed(2)} kcal
          </span>
          <span className="conclusion-me">
            {this.bugSvg(20)} {item.sumProtein.toFixed(2)} g
          </span>
          <span className="conclusion-me">
            {this.waterSvg(20)} {item.sumFats.toFixed(2)} g
          </span>
          <span className="conclusion-me">
            {this.bbqSvg(20)} {item.sumCarbohydrates.toFixed(2)} g{" "}
          </span>
        </label>
      </div>
    );
  };

  dailyMenuWindow = () => {
    return (
      <div>
        <div className="column-left-me">
          <Calendar
            onChange={(value, event) => this.calendarChanged(value, event)}
          />
        </div>
        <div className="column-center-me">
          {this.state.actualDailyMenu &&
            this.state.actualDailyMenu.meals.map((item, index) => {
              return (
                <div key={index}>{this.singleMealContainer(item, index)}</div>
              );
            })}
        </div>
        <div className="column-right-me">
          {this.dailyRecommendedMacroelements()}
        </div>
      </div>
    );
  };

  handleAddRecipe = (newRecipe) => {
    const recipeId = newRecipe.recipeId;
    const mealId =
      this.state.actualDailyMenu.meals[this.state.actualIndex].mealId;

    fetch("http://localhost:8080/specific/meals/" + mealId + "/" + recipeId, {
      credentials: "include",
      method: "post",
    })
      .then((res) => res.json())
      .then((res) => {
        const value = new Date(this.state.actualDailyMenu.date);
        value.setHours(value.getHours() + 2);
        this.getActualDailyMenu(value.toISOString().substring(0, 10));
      })
      .catch((error) => {
        console.log("error", error);
      });
  };

  getRecipesIds = () => {
    var arr = [];
    if (this.state.actualDailyMenu !== null && this.state.actualIndex != null) {
      var buf = this.state.actualDailyMenu.meals[this.state.actualIndex];
      arr = buf.recipes.map((item, index) => item.recipeId);
    }
    return arr;
  };

  resetVisibleNames = () => {
    var visibleNames = [];
    for (var i = 0; i < this.state.allRecipes.length; i++) {
      visibleNames[i] = this.state.allRecipes[i].name.toLowerCase();
    }
    this.setState({ visibleNames: visibleNames });
  };

  handleRecipesSearchChange = (event) => {
    var visibleNames = this.state.visibleNames;
    const { name, value } = event.target;
    this.resetVisibleNames();
    console.log(value)
    if (value === "") {
      this.resetVisibleNames();
    } else {
      this.state.allRecipes.forEach((item, index) => {
        if (!item.name.toLowerCase().includes(value.toLowerCase())) {
          var idx = visibleNames.indexOf(item.name.toLowerCase());
          if (idx > -1) {
            visibleNames[idx] = "";
          }
        }
      });
      this.setState({ visibleNames: visibleNames });
      console.log(visibleNames)
    }
  };

  recipesModalWindow = () => {
    return (
      <Modal
        show={this.state.showModal}
        onHide={() => this.setState({ showModal: false }, this.resetVisibleNames)}
      >
        <Modal.Header closeButton>
          <Modal.Title style={{ paddingLeft: "18%" }}>
            Lista wszystkich przepisów
          </Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div>
            <input
              style={{
                width: "50%",
                marginLeft: "25%",
              }}
              autocomplete="off"
              onChange={(value) => this.handleRecipesSearchChange(value)}
              type="text"
              id="recipesSearch"
              placeholder="Wyszukaj przepisy"
              name="recipesSearch"
            />
          </div>
          {this.state.allRecipes
            .filter((x) => !this.getRecipesIds().includes(x.recipeId))
            .map((item, index) => {
              if (this.state.visibleNames.includes(item.name.toLowerCase())) {
                return (
                  <div key={index} style={{ margin: "2%" }}>
                    <Button
                      variant="primary"
                      style={{
                        width: "50%",
                        marginLeft: "25%",
                        whiteSpace: "normal",
                        wordWrap: "break-word",
                      }}
                      onClick={() => {
                        this.handleAddRecipe(item);
                        this.setState({ showModal: false });
                        this.resetVisibleNames();
                      }}
                    >
                      {item.name}
                    </Button>
                  </div>
                );
              }
            })}
        </Modal.Body>
      </Modal>
    );
  };

  recipesEraseModalWindow = () => {
    return (
      <Modal
        show={this.state.idxs_to_remove !== null}
        onHide={() => this.setState({ idxs_to_remove: null })}
      >
        <Modal.Header closeButton>
          <Modal.Title style={{ paddingLeft: "18%" }}>
            Czy usunąć ten przepis?
          </Modal.Title>
        </Modal.Header>
        <Modal.Footer>
          <Button
            variant="secondary"
            onClick={() => {
              this.setState({ idxs_to_remove: null });
            }}
          >
            Anuluj
          </Button>
          <Button
            variant="danger"
            onClick={() => {
              console.log(
                "Usuwanie: " +
                  this.state.idxs_to_remove[0] +
                  " from " +
                  this.state.idxs_to_remove[1]
              );

              const recipeId = this.state.idxs_to_remove[0];
              const mealId =
                this.state.actualDailyMenu.meals[this.state.idxs_to_remove[1]]
                  .mealId;

              fetch(
                "http://localhost:8080/specific/meals/" +
                  mealId +
                  "/" +
                  recipeId,
                {
                  credentials: "include",
                  method: "delete",
                }
              )
                .then((res) => res.json())
                .then((res) => {
                  const value = new Date(this.state.actualDailyMenu.date);
                  value.setHours(value.getHours() + 2);
                  this.getActualDailyMenu(value.toISOString().substring(0, 10));
                })
                .catch((error) => {
                  console.log("error", error);
                });
              this.setState({ idxs_to_remove: null });
            }}
          >
            Usuń
          </Button>
        </Modal.Footer>
      </Modal>
    );
  };

  render() {
    return (
      <div style={{ minHeight: "100%" }}>
        {this.navbar()}
        <div className="center-me">
          <div>{this.dailyMenuWindow()}</div>
          {this.recipesModalWindow()}
          {this.recipesEraseModalWindow()}
        </div>
      </div>
    );
  }
}
