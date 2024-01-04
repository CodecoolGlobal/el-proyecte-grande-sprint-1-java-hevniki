import { useContext } from 'react';
import RecipeForm from '../Components/RecipeForm/RecipeForm.jsx';
import { CurrentUserContext } from '../CurrentUserContext.jsx';

async function createRecipe(recipe) {
	const recipeModel = {
		name: recipe.recipeName,
		description: recipe.description,
		ingredients: recipe.ingredients
	}
	console.log(recipeModel);
	const response = await fetch("/api/recipes", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(recipeModel),
	});

	return response.json();
}

function RecipeCreator() {
	const {currentUser, setCurrentUser} = useContext(CurrentUserContext);

	async function handleCreateRecipe(recipe) {
		const res = await createRecipe(recipe);
	}

	if (currentUser == null) {
		return (<div>Sign in</div>)
	}
  return (
    <RecipeForm onSave={handleCreateRecipe}></RecipeForm>
  );
}

export default RecipeCreator