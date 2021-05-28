import React, { Component } from 'react'
import Card from './Card';
import './Recipes.css';
import owsianka from './owsianka.jpg';
import omlet from './omlet.jpg';
import gofry from './gofry.jpg';
import pancakes from './pancakes.jpg';


export default class Recipes extends Component {
    
    constructor(props){
        super(props);
        this.state = {
            displayCategories : true,
            displayRecipies: false,
            recipes: ""
        }
    }

    render() {
        return (
            <div className="container">
                <div>
            <div className="row">
                <div className="col-sm-2"></div>
                <div className="col-sm-8">
                    <h1>Przepisy innych użytkowników</h1>
                </div>
                <div className="col-sm-2"></div>
            </div>
            <div className="row"> 
                <Card itemName={owsianka} searchItem="owsianka" onImageClick={this.onImageClick}/>
                <Card itemName={omlet} searchItem="omlet" onImageClick={this.onImageClick}/>
                <Card itemName={gofry} searchItem="gofry" onImageClick={this.onImageClick}/>
                <Card itemName={pancakes} searchItem="pancakes" onImageClick={this.onImageClick}/>
            </div>
            
            </div> 
        </div>
        )
    }
}