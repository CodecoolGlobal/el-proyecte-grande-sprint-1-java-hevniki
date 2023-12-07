import { useEffect } from "react";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import RecipeForm from "../Components/RecipeForm/RecipeForm.jsx";


const updateRecipe = (recipe) => {
    console.log(recipe)
    return fetch(`/api/recipes/${recipe.id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(recipe),
    }).then((res) => res.json());
};

const fetchRecipe = (id) => {
    return fetch(`/api/recipes/${id}`).then((res) => res.json());
};

const RecipeUpdater = () => {
    const { id } = useParams();
    const navigate = useNavigate();

    const [recipe, setRecipe] = useState(null);
    const [updateLoading, setUpdateLoading] = useState(false);
    const [recipeLoading, setRecipeLoading] = useState(true);

    useEffect(() => {
        fetchRecipe(id)
            .then((recipe) => {
                setRecipe(recipe);
                setRecipeLoading(false);
            });
    }, [id]);

    const handleUpdateRecipe = (recipe) => {
        setUpdateLoading(true);
        updateRecipe(recipe)
            .then(() => {
                setUpdateLoading(false);
                navigate("/");
            });
    };
    if (recipeLoading) {
        return <>Loading...</>
    }
    return (
        <RecipeForm
            recipe={recipe}
            onSave={handleUpdateRecipe}
            onCancel={() => navigate("/recipes")}
        />
    );
};

export default RecipeUpdater;
