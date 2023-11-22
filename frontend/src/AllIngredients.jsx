import {useState, useEffect} from "react";
import Ingredient from "./Ingredient.jsx";

async function fetchIngredients(){
    const res = await fetch("/api/ingredients");
    return await res.json();

}

function AllIngredients(){
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
    console.log(ingredients);
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
export default AllIngredients;