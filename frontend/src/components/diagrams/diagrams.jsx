import React from "react";
import AbstractBase from "../abstract_base/abstractBase";
import "./diagrams.css";
import { Card, Image, Modal, Button } from "react-bootstrap";
import Calendar from "react-calendar";
import Chart from "chart.js";
import "react-calendar/dist/Calendar.css";

export default class Diagrams extends AbstractBase {
  constructor(props) {
    super(props);
    this.state = {
      kcalDiagram: null,
      proteinDiagram: null,
      carbohydratesDiagram: null,
      fatsDiagram: null,
      user: null,
      kcalData: null,
      proteinData: null,
      fatsData: null,
      carbohydratesData: null,
      datesData: null,
      actualDate: new Date(),
      currentKcal: null,
      currentFats: null,
      currentProtein: null,
      currentCarbohydrates: null,
      currentDates: null,
    };
  }

  componentDidMount() {
    this.setState({ isUserLogged: true });
    document.body.style.backgroundColor = "#a7f5f5";
    this.handleGetUser();
  }

  handleGetUser = async () => {
    fetch("http://localhost:8080/specific/users", {
      credentials: "include",
    })
      .then((res) => res.json())
      .then((res) => {
        this.setState({ user: res }, () => this.initializeDiagramData());
      })
      .catch((error) => {
        window.location.href = "http://localhost:3000/";
      });
  };

  dailyRecommendedMacroelements = () => {
    return (
      <Card className="card-di" style={{ width: "60%", marginLeft: "30%" }}>
        <Card.Header className="card-header-di">
          Twoje dzienne zapotrzebowanie
        </Card.Header>
        <Card.Body>
          <Card.Text>
            <div>
              <div className="conclusion-di-2">
                {this.fireSvg(30)}{" "}
                {this.state.user &&
                  this.state.user.recommendedDailyKcal.toFixed(2)}{" "}
                kcal
              </div>
              <div className="conclusion-di-2">
                {this.bugSvg(30)}{" "}
                {this.state.user &&
                  this.state.user.recommendedDailyProtein.toFixed(2)}{" "}
                g
              </div>
              <div className="conclusion-di-2">
                {this.waterSvg(30)}{" "}
                {this.state.user &&
                  this.state.user.recommendedDailyFats.toFixed(2)}{" "}
                g
              </div>
              <div className="conclusion-di-2">
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

  initializeDiagramData = () => {
    //console.log(this.state.user.menus)
    var allDates = [];
    var allKcals = [];
    var allFats = [];
    var allCarbohydrates = [];
    var allProtein = [];

    this.state.user.menus.sort((a, b) =>
      a.date > b.date ? 1 : b.date > a.date ? -1 : 0
    );

    this.state.user.menus.forEach((item, index) => {
      const value = new Date(item.date);
      value.setHours(value.getHours() + 2);
      allDates.push(value.toISOString().substring(0, 10));
      //allDates.push(value);
      allKcals.push(item.sumKcal);
      allFats.push(item.sumFats);
      allCarbohydrates.push(item.sumCarbohydrates);
      allProtein.push(item.sumProtein);
    });

    this.setState(
      {
        kcalData: allKcals,
        proteinData: allProtein,
        fatsData: allFats,
        carbohydratesData: allCarbohydrates,
        datesData: allDates,
      },
      () => {
        var date = new Date();
        var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
        this.calendarChanged(firstDay, null);
      }
    );
  };

  singleDiagram = (diagramId, diagramTitle) => {
    return (
      <Card className="card-di" style={{ width: "100%" }}>
        <Card.Header className="card-header-di">{diagramTitle}</Card.Header>
        <Card.Body>
          <Card.Text>{<canvas id={diagramId}></canvas>}</Card.Text>
        </Card.Body>
      </Card>
    );
  };

  calendarChanged = (value, event) => {
    console.log(value);
    value.setHours(value.getHours() + 2);
    var firstDayOfMonth = value;
    var bufDay = new Date(firstDayOfMonth);
    var currMonthDays = [];

    for (var i = 0; i < 31; i++) {
      if (
        bufDay.getMonth() === firstDayOfMonth.getMonth() &&
        bufDay.getYear() === firstDayOfMonth.getYear()
      ) {
        currMonthDays.push(new Date(bufDay));
      }
      var buf = new Date(bufDay);
      bufDay.setDate(buf.getDate() + 1);
    }

    const dates = this.state.datesData;
    const kcals = this.state.kcalData;
    const fats = this.state.fatsData;
    const proteins = this.state.proteinData;
    const carbohydrates = this.state.carbohydratesData;
    var bufKcals = [];
    var bufProteins = [];
    var bufFats = [];
    var bufCarbohydrates = [];
    var bufDates = [];

    if (this.state.datesData) {
      currMonthDays.forEach((item, index) => {
        var bufCurrDay = item.toISOString().substring(0, 10);
        var bufIdx = dates.indexOf(bufCurrDay);
        if (bufIdx !== -1) {
          bufDates.push(bufCurrDay);
          bufKcals.push(kcals[bufIdx]);
          bufProteins.push(proteins[bufIdx]);
          bufFats.push(fats[bufIdx]);
          bufCarbohydrates.push(carbohydrates[bufIdx]);
        } else {
          bufDates.push(bufCurrDay);
          bufKcals.push(0);
          bufProteins.push(0);
          bufFats.push(0);
          bufCarbohydrates.push(0);
        }
      });
    }

    this.setState(
      {
        currentKcal: bufKcals,
        currentFats: bufFats,
        currentProtein: bufProteins,
        currentCarbohydrates: bufCarbohydrates,
        currentDates: bufDates,
      },
      () => this.updateAllDiagrams()
    );
  };

  updateAllDiagrams = () => {
    this.updateSingleDiagram(
      "kcal",
      "kcal",
      this.state.currentDates,
      this.state.currentKcal,
      this.state.user.recommendedDailyKcal
    );
    this.updateSingleDiagram(
      "tłuszcze",
      "fats",
      this.state.currentDates,
      this.state.currentFats,
      this.state.user.recommendedDailyFats
    );
    this.updateSingleDiagram(
      "węglowodany",
      "carbohydrates",
      this.state.currentDates,
      this.state.currentCarbohydrates,
      this.state.user.recommendedDailyCarbohydrates
    );
    this.updateSingleDiagram(
      "białko",
      "protein",
      this.state.currentDates,
      this.state.currentProtein,
      this.state.user.recommendedDailyProtein
    );
  };

  updateSingleDiagram = (label, labelEng, xData, yData, recommendedData) => {
    var yLabel = label;
    var actualValuesLabel = "Spożyte " + label;
    var recommendedLabel = "Rekomendowane spożycie: " + label;

    var dataMacroelements = xData.map((it, idx) => recommendedData);
    console.log(this.state.currentDates);
    var ctx = document.getElementById(labelEng + "Diagram").getContext("2d");
    var chart = new Chart(ctx, {
      type: "bar",
      data: {
        labels: xData,
        datasets: [
          {
            label: actualValuesLabel,
            backgroundColor: "rgba(54, 162, 235, 0.2)",
            data: yData,
            barThickness: 16,
            borderColor: "rgb(54, 162, 235)",
            borderWidth: 1,
          },
          {
            label: recommendedLabel,
            borderColor: "red",
            backgroundColor: "transparent",
            data: dataMacroelements,
            barThickness: 16,
            type: "line",
          },
        ],
      },
      options: {
        scales: {
          yAxes: [
            {
              scaleLabel: {
                display: true,
                labelString: yLabel,
              },
            },
          ],
        },
      },
    });

    switch (label) {
      case "kcal":
        this.setState({ kcalDiagram: chart });
        break;
      case "białko":
        this.setState({ proteinDiagram: chart });
        break;
      case "węglowodany":
        this.setState({ carbohydratesDiagram: chart });
        break;
      case "tłuszcze":
        this.setState({ fatsDiagram: chart });
        break;
      default:
        console.log("error");
    }
  };

  allDiagrams = () => {
    return (
      <div>
        <div className="column-left-di">
          <Calendar
            view="year"
            onClickMonth={(value, event) => this.calendarChanged(value, event)}
          />
        </div>
        <div className="column-center-di">
          {this.singleDiagram(
            "kcalDiagram",
            "Diagram dziennie spożywanych kcal"
          )}
          {this.singleDiagram(
            "proteinDiagram",
            "Diagram dziennie spożywanego białka"
          )}
          {this.singleDiagram(
            "fatsDiagram",
            "Diagram dziennie spożywanych tłuszczy"
          )}
          {this.singleDiagram(
            "carbohydratesDiagram",
            "Diagram dziennie spożywanych węglowodanów"
          )}
        </div>
        <div className="column-right-di">
          {this.dailyRecommendedMacroelements()}
        </div>
      </div>
    );
  };

  render() {
    return (
      <div style={{ minHeight: "100%" }}>
        {this.navbar()}
        <div className="center-di">
          <div>{this.allDiagrams()}</div>
        </div>
      </div>
    );
  }
}
