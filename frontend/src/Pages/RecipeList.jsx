import React, { useEffect, useState } from 'react'
import RecipeOverview from '../Components/RecipeOverview/RecipeOverview.jsx';

function RecipeList() {
	const recipesPath = "/api/recipes"
	const [recipes, setRecipes] = useState(null);

	async function getRecipes(recipesPath, setRecipes) {
		const response = await fetch(recipesPath);
		const recipesData = await response.json();
	
		setRecipes(recipesData);
	}

	useEffect(() => {
		getRecipes(recipesPath, setRecipes);
	}, []);

	if (recipes != null) {

		return (
			<ul>
				{recipes.map(recipe => <RecipeOverview key = {recipe.id} details={recipe}></RecipeOverview>)}
			</ul>
		)
	}
	return <>Loading...</>
}

export default RecipeList
