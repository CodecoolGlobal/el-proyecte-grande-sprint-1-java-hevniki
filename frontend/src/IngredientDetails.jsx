
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";

async function fetchIngredient(id){

}

function IngredientDetails() {
    const { id } = useParams();

    return (
        <>
            <ul>
                <li>
                    ingredient name: {name}
                </li>
                <li>
                    unit of measure: {unitOfMeasure}
                </li>
                <li>
                    gluten free? {isGlutenFree ? "yes" : "no"}
                </li>
                <li>
                    meat free? {isMeatFree ? "yes" : "no"}
                </li>
                <li>
                    dairy free? {isDairyFree ? "yes" : "no"}
                </li>
                <button>
                    show recipes which includes this ingredient
                </button>
            </ul>
        </>
    )
}
export default IngredientDetails;