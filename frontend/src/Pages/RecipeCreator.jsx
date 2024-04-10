import {useContext} from 'react';
import {useNavigate} from 'react-router-dom';
import RecipeForm from '../Components/RecipeForm/RecipeForm.jsx';
import {CurrentUserContext} from '../CurrentUserContext.jsx';




function RecipeCreator() {
    const navigate = useNavigate();

    const {currentUser, setCurrentUser} = useContext(CurrentUserContext);

    async function handleCreateRecipe(recipe) {
        await createRecipe(recipe);
    }
	async function createRecipe(recipe) {

		const recipeModel = {
			name: recipe.name,
			description: recipe.description,
			ingredients: recipe.ingredients
		}
		const token = JSON.parse(localStorage.getItem("user")).token;
		const response = await fetch("/api/recipes", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			'Authorization': `Bearer ${token}`,
			},
			body: JSON.stringify(recipeModel),
		});

		return await response.json();
	}

    function navigateToLogin() {
        navigate('/login')
    }

    if (currentUser == null) {
        return (
            <div>
                <button onClick={navigateToLogin}>Sign in first</button>
            </div>
        )
    }
    return (
        <RecipeForm onSave={handleCreateRecipe}></RecipeForm>
    );
}

export default RecipeCreator