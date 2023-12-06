import {useState, useEffect} from "react";
import {useParams, useNavigate} from "react-router-dom";

function RecipeDetails() {
    const {id} = useParams();
    const [loading, setLoading] = useState(true);
    const [recipe, setRecipe] = useState(null);
    const navigate = useNavigate()

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

    useEffect(() => {
        async function task() {
            const fetchedRecipe = await fetchRecipe(id)
            setRecipe(fetchedRecipe);
            setLoading(false);
        }

        task()
    }, []);

    if (loading) {
        return (
            <div>
                Loading
            </div>
        )
    }
    // console.log(recipe.ingredients);
    return (
        <div>
            <ul>
                <li>Recipe name: {recipe.name}</li>
                <li>Recipe details: {recipe.description}</li>
                <li>is Vegan : {(recipe.isVegan) ? "yes" : "no"}</li>
                <li>is Vegetarian : {(recipe.isVegetarian) ? "yes" : "no"}</li>
                <li>is GlutenFree : {(recipe.isGlutenFree) ? "yes" : "no"}</li>
                <li>is DairyFree : {(recipe.isDairyFree) ? "yes" : "no"}</li>

            </ul>
            <button onClick={async () => {
                await deleteRecipe(id);
                navigate('/');
            }}>delete this recipe
            </button>
        </div>
    )
}


export default RecipeDetails;