import React from 'react'

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
        <li key={id}>
            <p><b>{name}</b></p>
            <p>Vegan: {isVegan ? "yes" : "no"}</p>
            <p>Vegatarian: {isVegetarian ? "yes" : "no"}</p>
            <p>Gluten-free: {isGlutenFree ? "yes" : "no"}</p>
            <p>Dairy-free: {isDairyFree ? "yes" : "no"}</p>
        </li>
    )
}

export default RecipeOverview;