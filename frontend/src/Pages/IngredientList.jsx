import {useState, useEffect} from "react";

import Ingredient from "../Components/Ingredient.jsx";

async function fetchIngredients(){
    const res = await fetch("/api/ingredients");
    return await res.json();

}

function IngredientList(){
    const [ingredients, setIngredients] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(()=>{
        async function task(){
            const fetchedIngredients = await fetchIngredients();
            setIngredients(fetchedIngredients);
            setLoading(false);
        }
        task();
    }, []);

    if(loading){
        return (
            <div>Loading</div>
        )
    }
    return (
        <>
            <div>All ingredients:</div>
            {ingredients.map((ingredient)=>{
                return (<Ingredient key= {ingredient.id} ingredient={ingredient}/>)
                }
            )}
        </>
    )

}
export default IngredientList;