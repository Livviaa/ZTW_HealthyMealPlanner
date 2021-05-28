import React from 'react';
import "./AddRecipe.css"
import chicken from './chicken.jpg'
console.log(chicken);

function AddRecipe() {
    return(
        <div>
            <div className="addbox">
                <div className="title">
                    <h2>Obiad</h2>
                </div>
                <div className="clear">
                <div className="img">
                    <img src={chicken}></img>
                </div>
                <div className="ingridients">
                    <b>Składniki:</b>
                    <ul>
                        <li>Ziemniaki: 550g</li>
                        <li>Polędwiczki z indyka: 400g</li>
                        <li> Ogórek: 130g</li>
                        <li> Zielony groszek: 300g</li>
                        <li>Czerwona cebula: 20g</li>
                        <li>Śmietana 12%: 1 łyżka</li>
                    </ul>
                    <button
                    type="registration" 
                    className="btn btn-primary"
                    style={{marginLeft: 165, marginBottom: 5 }}>
                        Dodaj składnik
                    </button>
                </div></div>
                <div >
                    <ol>
                        <li>Mięso z indyka pokroić poprzecznie w cienkie plasterki.Posypać szczyptą soli, pieprzem oraz niewielką ilością słodkiej papryki i pikantnej mieszanki przypraw.</li>
                        <li>Na patelni rozgrzać łyżkę oleju, dodać pokrojoną w piórka cebulę i podsmażyć.</li>
                        <li>Dodać mięso i smażyć do momentu aż zmieni kolor, co jakiś czas mieszając.</li>
                        <li>W miseczce wymieszać sos sojowy, olej sezamowy, ocet balsamiczny i cukier trzcinowy. Wlać na patelnię.Dodać otartą papryczkę chili. Podsmażać do momentu, aż sos się zredukuje i mięso zrobi miękkie.</li>
                        <li>Na koniec dodać sezam i 2 łyżki posiekanych, zielonych pędów cebuli.</li>
                        <li>Ziemniaki ugotować w osolonej wodzie. Odcedzić i wyłożyć na talerze.
                            Groszek cukrowy umyć. Zagotować wodę z dodatkiem cukru i soli (po 1/2 łyżeczki), wrzucić groszek. Gotować przez 3 minuty. Odcedzić.
                            Rozgrzać patelnię, roztopić łyżeczkę masła, wrzucić groszek i podsmażać przez około minutę na dużym ogniu.</li>
                        <li>Ogórka obrać i pokroić w cienkie plasterki. Wyłożyć na talerze, polać śmietaną, doprawić niewielką ilością soli i pieprzu.</li>

                    </ol>
                    <button
                    type="registration" 
                    className="btn btn-primary"
                    style={{float: 'right', marginRight: 10, marginBottom: 5 }}>
                        Zapisz przepis
                    </button>
                </div>

            </div>
            <div className="makrobox">
                <div className="title">
                    <h5>Makroelementy</h5>
                    <table>
                        <tr>
                            <td> </td>
                            <td> </td>
                        </tr>  
                        <tr>
                            <td> </td>
                            <td> </td>
                        </tr> 
                    </table>
                    
                </div>

            </div>
        </div>
    )
}

export default AddRecipe;   