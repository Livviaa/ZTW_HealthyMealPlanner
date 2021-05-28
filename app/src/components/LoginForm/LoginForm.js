import React from 'react';
import diet1 from "./diet1.png";
import diet2 from "./diet2.png";

console.log(diet1);
console.log(diet2);

function LoginForm(props) {
  return(
      <div className="loginBox" style={{marginTop:150}}>
        <div id="plogin">
            <h3>Healthy Meal Planner</h3>
            <p >Zadbaj o swoje zdrowie! Zadbaj o swoją dietę razem z nami. </p>
            <img src={diet1} alt="Diet" style={{height: 150}} />
            
        </div>
        <div id="from1">
        <div className="card col-12 col-lg-4 login-card mt-2 hv-center">
            <form>
                <div className="form-group text-left">
                <label htmlFor="exampleInputUsername1">Nazwa użytkownika:</label>
                <input type="username" 
                       className="form-control" 
                       id="username" 
                       placeholder="Podaj nazwę..."
                />
                
                </div>
                <div className="form-group text-left">
                    <label htmlFor="exampleInputPassword1">Hasło logowania:</label>
                    <input type="password" 
                        className="form-control" 
                        id="password" 
                        placeholder="Podaj hasło..."
                    />
                </div>
                
                <button 
                    type="submit" 
                    className="btn btn-primary"
                >
                    Zaloguj się!
                </button>
                <button 
                    
                    type="registration" 
                    className="btn btn-primary"
                    style={{marginLeft: 30 }}
                >
                    Zarejestruj się!
                </button>
               <u style={{color: 'grey'}}><small id="forgetPassword" className="form-text text-muted" style={{marginLeft: 60 }}>Nie pamiętasz hasła?</small></u>
            </form>
        </div>
    </div></div>
    )
}
export default LoginForm;