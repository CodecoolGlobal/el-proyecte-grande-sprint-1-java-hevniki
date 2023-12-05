import { useNavigate } from 'react-router-dom';

import RecipeForm from './RecipeForm';

async function createRecipe(recipe) {
	const sendableRecipe = {
		name: recipe.recipeName,
		description: recipe.description,
		ingredients: recipe.newSelectedIngredients
	}
	console.log(sendableRecipe);
	const response = await fetch("/api/recipes", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(sendableRecipe),
	});

	return response.json();
}

function RecipeCreator() {
	async function handleCreateRecipe(recipe) {
		const res = await createRecipe(recipe);
	}

  return (
    <RecipeForm onSave={handleCreateRecipe}></RecipeForm>
  );
}

export default RecipeCreator