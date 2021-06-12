import AbstractBase from "../abstract_base/abstractBase";

export default class Profile extends AbstractBase {
  constructor(props) {
    super(props);
    this.state = {
      name: null,
      surname: null,
      height: null,
      weight: null,
      birthDate: null,
      sex: null,
      activity: null,
      userId: null,
      errors: {
        name: "",
        surname: "",
        height: "",
        weight: "",
        birthDate: "",
        sex: "",
        activity: "",
      },
    };
  }

  componentDidMount() {
    this.getActualUser();
    this.setState({ isUserLogged: true })
    document.body.style.backgroundColor = "#a7f5f5"
  }

  getActualUser = async () => {
    fetch("http://localhost:8080/specific/users", {
      credentials: "include",
    })
      .then((res) => res.json())
      .then((res) => {
        this.setState({
          name: res.name,
          surname: res.surname,
          height: res.height,
          weight: res.weight,
          birthDate: res.birthDate,
          sex: res.sex,
          activity: res.activity,
          userId: res.userId,
        });
        console.log(this.state);
        if (this.state.sex !== null) {
          if (this.state.sex === "M") {
            document.getElementById("male").checked = true;
          } else {
            document.getElementById("female").checked = true;
          }
        }
        if (this.state.activity !== null) {
          if (this.state.activity === "brak") {
            document.getElementById("none").checked = true;
          } else if (this.state.activity === "malo") {
            document.getElementById("little").checked = true;
          } else if (this.state.activity === "srednio") {
            document.getElementById("medium").checked = true;
          } else if (this.state.activity === "duzo") {
            document.getElementById("large").checked = true;
          }
        }
        if (this.state.birthDate !== null) {
          var birth = this.state.birthDate.substring(0, 10);
          document.getElementById("birthDate").value = birth;
        } else {
          let errors = this.state.errors;
          errors.birthDate = "Wymagane pole!";
          this.setState({ errors: errors });
        }

        if (this.state.height === null) {
          let errors = this.state.errors;
          errors.height = "Wzrost musi być większy od zera!";
          this.setState({ errors: errors });
        }
        if (this.state.weight === null) {
          let errors = this.state.errors;
          errors.weight = "Waga musi być większa od zera!";
          this.setState({ errors: errors });
        }
      })
      .catch((error) => console.log("error", error));
  };

  validateForm = (errors) => {
    let valid = true;
    Object.values(errors).forEach((val) => val.length > 0 && (valid = false));
    return valid;
  };

  handleChange = (event) => {
    event.preventDefault();
    const { name, value } = event.target;
    let errors = this.state.errors;

    switch (name) {
      case "name":
        errors.name = value.length < 1 ? "Nie może być puste!" : "";
        break;
      case "surname":
        errors.surname = value.length < 1 ? "Naziwsko nie może być puste!" : "";
        break;
      case "height":
        errors.height = value < 1 ? "Wzrost musi być większy od zera!" : "";
        break;
      case "weight":
        errors.weight = value < 1 ? "Waga musi być większa od zera!" : "";
        break;
      case "birthDate":
        errors.birthDate = value.length < 1 ? "Wymagane pole!" : "";
        break;
      default:
        break;
    }
    this.setState({ errors, [name]: value });
  };

  handleSubmit = (event) => {
    event.preventDefault();
    if (this.validateForm(this.state.errors)) {
      var activity = "brak";
      if (document.getElementById("none").checked === true) {
        activity = "brak";
      } else if (document.getElementById("little").checked) {
        activity = "malo";
      } else if (document.getElementById("medium").checked) {
        activity = "srednio";
      } else if (document.getElementById("large").checked) {
        activity = "duzo";
      }

      fetch("http://localhost:8080/specific/users", {
        credentials: "include",
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
        },
        body: JSON.stringify({
          activity: activity,
          birthDate: this.state.birthDate,
          height: parseInt(this.state.height),
          name: this.state.name,
          sex: document.getElementById("male").checked === true ? "M" : "K",
          surname: this.state.surname,
          weight: parseInt(this.state.weight),
          userId: this.state.userId,
        }),
      })
        .then((res) => res.json())
        .then((res) => console.log(res))
        .catch((err) => console.error(err));
      alert("Pomyślnie zaktualizowano dane personalne!");
    } else {
      alert("Błąd! Niepoprawne dane");
    }
  };

  profileForm = () => {
    const { errors } = this.state;
    return (
      <div>
        <form onSubmit={this.handleSubmit} noValidate>
          <div className="form-group text-left">
            <label htmlFor="exampleInputGender1">Płeć</label>
            <br></br>
            <input type="radio" name="gender" id="female"></input>{" "}
            <label htmlFor="female">Kobieta</label>
            <br></br>
            <input type="radio" name="gender" id="male"></input>{" "}
            <label htmlFor="male">Mężczyzna</label>
          </div>

          <div className="form-group text-left">
            <label htmlFor="exampleInputName1">Imię</label>
            <input
              type="name"
              className="form-control"
              id="name"
              name="name"
              placeholder="Podaj imię..."
              required
              onChange={this.handleChange}
              defaultValue={this.state.name}
            />
            {errors.name.length > 0 && (
              <span className="error">{errors.name}</span>
            )}
          </div>

          <div className="form-group text-left">
            <label htmlFor="exampleInputSurname1">Nazwisko</label>
            <input
              type="surname"
              className="form-control"
              id="surname"
              name="surname"
              placeholder="Podaj nazwisko..."
              required
              onChange={this.handleChange}
              defaultValue={this.state.surname}
            />
            {errors.surname.length > 0 && (
              <span className="error">{errors.surname}</span>
            )}
          </div>

          <div className="form-group text-left">
            <label htmlFor="exampleInputBirthday1">Data urodzenia</label>
            <input
              type="date"
              className="form-control"
              id="birthDate"
              name="birthDate"
              onChange={this.handleChange}
              defaultValue={this.state.birthDate}
            />
            {errors.birthDate.length > 0 && (
              <span className="error">{errors.birthDate}</span>
            )}
          </div>

          <div className="form-group text-left">
            <label htmlFor="exampleInputHeight1">Wzrost [cm]</label>
            <input
              type="number"
              className="form-control"
              name="height"
              id="height"
              onChange={this.handleChange}
              defaultValue={this.state.height}
            />
            {errors.height.length > 0 && (
              <span className="error">{errors.height}</span>
            )}
          </div>

          <div className="form-group text-left">
            <label htmlFor="exampleInputWeight1">Waga [kg]</label>
            <input
              type="number"
              className="form-control"
              name="weight"
              id="weight"
              onChange={this.handleChange}
              defaultValue={this.state.weight}
              required
            />
            {errors.weight.length > 0 && (
              <span className="error">{errors.weight}</span>
            )}
          </div>

          <div className="form-group text-left">
            <label htmlFor="exampleInputActivity1">Aktywność: </label>
            <br></br>
            <input
              type="radio"
              name="activity"
              id="none"
              value="none"
            ></input>{" "}
            <label htmlFor="none">Brak aktywności fizycznej</label>
            <br></br>
            <input
              type="radio"
              name="activity"
              id="little"
              value="little"
            ></input>{" "}
            <label htmlFor="little">Trening 1 - 2 razy w tyg.</label>
            <br></br>
            <input
              type="radio"
              name="activity"
              id="medium"
              value="medium"
            ></input>{" "}
            <label htmlFor="medium">Trening 3 - 4 razy w tyg.</label>
            <br></br>
            <input
              type="radio"
              name="activity"
              id="large"
              value="large"
            ></input>{" "}
            <label htmlFor="large">Trening 4 - 5 razy w tyg.</label>
            <br></br>
          </div>

          <button
            type="submit"
            onClick={this.handleSubmit}
            className="btn btn-primary"
            style={{ marginBottom: 10, float: "right" }}
          >
            Aktualizuj dane
          </button>
        </form>
      </div>
    );
  };

  render() {
    return (
      <div style={{ minHeight: "100%" }}>
        {this.navbar()}
        <div className="container pb-1">
          <div className="card border-primary mb-3">
            <div className="card-header border-primary">Ustawienia konta</div>
            <div className="card-body">{this.profileForm()}</div>
          </div>
          {this.logoutButton()}
        </div>
      </div>
    );
  }
}
