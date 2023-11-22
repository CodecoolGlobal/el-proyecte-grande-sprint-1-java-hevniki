import React, { useEffect, useState } from 'react'

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
		console.log(recipes);
		return (
			<ul>
				{recipes.map(recipe => <li>{recipe.name}</li>)}
			</ul>
		)
	}
	return <>Loading...</>
}

export default RecipeList
