import React from 'react'
import { Link } from 'react-router-dom';

import "./RecipeOverview.css";

function RecipeOverview(props) {
    const {
        id,
        name,
        isVegan,
        isVegetarian,
        isGlutenFree,
        isDairyFree
    } = props.details
    return (
        <li key={id} className='overview'>
            <p><b>{name}</b></p>
            <p>Vegan: {isVegan ? "yes" : "no"}</p>
            <p>Vegetarian: {isVegetarian ? "yes" : "no"}</p>
            <p>Gluten-free: {isGlutenFree ? "yes" : "no"}</p>
            <p>Dairy-free: {isDairyFree ? "yes" : "no"}</p>
            <Link to={`/recipes/${id}`}>
                <button> Details </button>
            </Link>
        </li>
    )
}

export default RecipeOverview;