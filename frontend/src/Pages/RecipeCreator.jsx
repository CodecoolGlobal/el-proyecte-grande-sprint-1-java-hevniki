import RecipeForm from '../Components/RecipeForm/RecipeForm.jsx';

async function createRecipe(recipe) {
	const recipeModel = {
		name: recipe.recipeName,
		description: recipe.description,
		ingredients: recipe.newSelectedIngredients
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
	async function handleCreateRecipe(recipe) {
		const res = await createRecipe(recipe);
	}

  return (
    <RecipeForm onSave={handleCreateRecipe}></RecipeForm>
  );
}

export default RecipeCreator