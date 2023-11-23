import React, { useEffect, useState } from 'react'
import RecipeOverview from './RecipeOverview';

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
			<ul style={{listStyleType: "none"}}>
				{recipes.map(recipe => <RecipeOverview details={recipe}></RecipeOverview>)}
			</ul>
		)
	}
	return <>Loading...</>
}

export default RecipeList
