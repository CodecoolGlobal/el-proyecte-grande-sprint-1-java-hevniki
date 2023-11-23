import React from 'react';
import { useNavigate } from 'react-router-dom';

import RecipeForm from './RecipeForm';

async function createRecipe(recipe) {
	const sendableRecipe = {
		name: recipe.recipeName,
		description: recipe.description,
		ingredients: recipe.newSelectedIngredients
	}
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
	const navigate = useNavigate();

	async function handleCreateRecipe(recipe) {
		await createRecipe(recipe);
		navigate("/");
	}

  return (
    <RecipeForm onSave={handleCreateRecipe}></RecipeForm>
  );
}

export default RecipeCreator