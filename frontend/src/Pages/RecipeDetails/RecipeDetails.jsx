import {useState, useEffect} from "react";
import {useParams, useNavigate, Link} from "react-router-dom";
import IngredientForRecipe from "../../Components/IngredientForRecipe";

import "./RecipeDetails.css"

async function fetchRecipe(id) {
    const res = await fetch(`/api/recipes/${id}`);
    return res.json();
}

async function deleteRecipe(id) {
    const res = await fetch(`/api/recipes/${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    });
    return await res.json();
}

function RecipeDetails() {
    const {id} = useParams();
    const [loading, setLoading] = useState(true);
    const [recipe, setRecipe] = useState(null);
    const navigate = useNavigate()

    useEffect(() => {
        async function task() {
            const fetchedRecipe = await fetchRecipe(id)
            setRecipe(fetchedRecipe);
            setLoading(false);
        }

        task();
    }, []);

    if (loading) {
        return (
            <div>
                Loading...
            </div>
        )
    }

    return (
        <div className="recipeDetailsContainer">
            <h1>{recipe.name}</h1>
            <hr></hr>
            <br></br>
            <ul>
                {recipe.ingredients.map(current => {
                    return <IngredientForRecipe ingredient={current}></IngredientForRecipe>
                })}
            </ul>
            <br></br>
            <div className="recipeDescription">{recipe.description}</div>
            <br></br>
            <Link to={`/recipes/update/${id}`}>
                <button type="button">Update</button>
            </Link>
            <br></br>
            <button onClick={async () => {
                await deleteRecipe(id);
                navigate('/');
            }}>delete this recipe
            </button>
        </div>
    )
}


export default RecipeDetails;