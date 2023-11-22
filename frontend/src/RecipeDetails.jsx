import {useState, useEffect} from "react";
import {useParams} from "react-router-dom";
import IngredientForRecipe from "./IngredientForRecipe.jsx";
function RecipeDetails(){
    const {id} = useParams();
    const [loading, setLoading] = useState(true);
    const [recipe, setRecipe] = useState(null);

    async function fetchRecipe(id){
        const res = await fetch(`/api/recipes/${id}`);
        return res.json();
    }

    useEffect(() => {
        async function task(){
            const fetchedRecipe = await fetchRecipe(id)
            setRecipe(fetchedRecipe);
            setLoading(false);
        }
        task()
    }, []);

    if(loading){
        return (
        <div>
            Loading
        </div>
        )
    }
   // console.log(recipe.ingredients);
    return(
        <div>
            <ul>
                <li>Recipe name: {recipe.name}</li>
                <li>Recipe details: {recipe.description}</li>
                <li>is Vegan : {(recipe.isVegan) ? "yes" : "no"}</li>
                <li>is Vegetarian : {(recipe.isVegetarian) ? "yes" : "no"}</li>
                <li>is GlutenFree : {(recipe.isGlutenFree) ? "yes" : "no"}</li>
                <li>is DairyFree : {(recipe.isDairyFree) ? "yes" : "no"}</li>
                <div>Ingredients: </div>
                <>{recipe.ingredients.map((ingredient)=>{
                    return <IngredientForRecipe
                        key = {ingredient[0]}
                        props={ingredient}
                />})}</>


            </ul>
        </div>
    )
}

export default RecipeDetails;