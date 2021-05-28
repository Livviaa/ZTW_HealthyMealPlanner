import React from 'react';

function RegistrationForm(props) {
  return(
        <div className="card col-12 col-lg-4 login-card mt-2 hv-center" style={{marginLeft: 400}}>
            <form>
            <div className="form-group text-left">
                <label htmlFor="exampleInputGender1">Płeć</label><br></br>
               
                <input type="radio" name="gender" id="female" value="female"></input> <label for="female">Kobieta</label><br></br>
                <input type="radio" name="gender" id="male" value="male"></input> <label for="male">Mężczyzna</label>
                
                </div>
                
                <div className="form-group text-left">
                <label htmlFor="exampleInputName1">Imię</label>
                <input type="name" 
                       className="form-control" 
                       id="name" 
                       placeholder="Podaj imię..."
                />
                </div>

                <div className="form-group text-left">
                <label htmlFor="exampleInputSurname1">Nazwisko</label>
                <input type="surname" 
                       className="form-control" 
                       id="surname" 
                       placeholder="Podaj nazwisko..."
                />
                </div>

                <div className="form-group text-left">
                <label htmlFor="exampleInputUsername1">Nazwa użytkownika</label>
                <input type="username" 
                       className="form-control" 
                       id="username" 
                       placeholder="Podaj nazwę..."
                       required
                />
                </div>
                
                <div className="form-group text-left">
                <label htmlFor="exampleInputEmail1">Adres email</label>
                <input type="email" 
                       className="form-control" 
                       id="email" 
                       placeholder="adres@email.com"
                       required
                />
                </div>
                <div className="form-group text-left">
                    <label htmlFor="exampleInputPassword1">Hasło</label>
                    <input type="password" 
                        className="form-control" 
                        id="password" 
                        placeholder="********"
                        required
                    />
                </div>
                <div className="form-group text-left">
                    <label htmlFor="exampleInputPassword1">Powtórz hasło</label>
                    <input type="password" 
                        className="form-control" 
                        id="confirmPassword" 
                        placeholder="********"
                        required
                    />
                </div>

                <div className="form-group text-left">
                <label htmlFor="exampleInputBirthday1">Data urodzenia</label>
                <input type="date" 
                       className="form-control" 
                       id="username" 
               />
                </div>

                <div className="form-group text-left">
                <label htmlFor="exampleInputHeight1">Wzrost [cm]</label>
                <input type="height" 
                       className="form-control" 
                       id="height" 
               />
                </div>

                <div className="form-group text-left">
                <label htmlFor="exampleInputWeight1">Waga [kg]</label>
                <input type="weight" 
                       className="form-control" 
                       id="weight" 
                />
                </div>

                <div className="form-group text-left">
                <label htmlFor="exampleInputActivity1">Aktywność </label><br></br>
                <input type="radio" name="activity" id="none" value="none"></input> <label for="none">brak</label><br></br>
                <input type="radio" name="activity" id="little" value="little"></input> <label for="little">1 - 2 razy w tyg.</label><br></br>
                <input type="radio" name="activity" id="medium" value="medium"></input> <label for="medium">3 - 4 razy w tyg.</label><br></br>
                <input type="radio" name="activity" id="large" value="large"></input> <label for="large">4 - 5 razy w tyg.</label><br></br>
                
                </div>

                <button 
                    type="submit" 
                    className="btn btn-primary"
                    style={{marginBottom: 10, float: 'right'}}
                >Zarejestruj się 
                </button>
            </form>
        </div>
    )
}
export default RegistrationForm;