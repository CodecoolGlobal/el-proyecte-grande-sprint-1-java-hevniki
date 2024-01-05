import {useEffect, useState} from 'react'
import RecipeOverview from '../Components/RecipeOverview/RecipeOverview.jsx';
import SearchBar from "./SearchBar.jsx";

function RecipeList() {

    const recipesPath = "/api/recipes"
    const [recipes, setRecipes] = useState(null);

    async function getRecipes(recipesPath, setRecipes) {
        const response = await fetch(recipesPath);
        const recipesData = await response.json();

        setRecipes(recipesData);
    }

    async function handleSearch(query) {
       await getFilteredRecipes(query);
    }



    async function getFilteredRecipes(query) {
        let url = `/api/recipes/search?name=${query.name}`;
        if (query.vegan) {
            url += `&vegan=${query.vegan}`;
        }
        if (query.vegetarian) {
            url += `&vegetarian=${query.vegetarian}`;
        }
        if (query.glutenFree) {
            url += `&glutenFree=${query.glutenFree}`;
        }
        if (query.dairyFree) {
            url += `&dairyFree=${query.dairyFree}`;
        }
        if(query.ingredients.length>0){
            url+="&ingredients=";
            query.ingredients.forEach((ing)=>{url+=`${ing},`});
            url=url.slice(0,-1);
        }
        console.log(url);
        const res = await fetch(url);
        const filteredRecipes = await res.json();
        setRecipes(filteredRecipes);
    }

    useEffect(() => {
        getRecipes(recipesPath, setRecipes);
    }, []);

    if (recipes != null) {

        return (
            <>
                <SearchBar
                    onSearch={handleSearch}></SearchBar>
                <ul>
                    {recipes.map(recipe => <RecipeOverview key={recipe.id} details={recipe}></RecipeOverview>)}
                </ul>
            </>
        )
    }
    return <>Loading...</>
}

export default RecipeList
