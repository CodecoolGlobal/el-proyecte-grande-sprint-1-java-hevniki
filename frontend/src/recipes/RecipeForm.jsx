import React, { useEffect, useState } from 'react';
import './RecipeForm.css'

async function fetchIngredients() {
    const response = await fetch("/api/ingredients");
    const data = response.json();
    return data;
}

function RecipeForm() {
    const [ingredients, setIngredients] = useState(null);

    useEffect(() => {
        async function task() {
            const fetchedIngredients = await fetchIngredients();
            setIngredients(fetchedIngredients);
        }
        
        task();
    }, []);

    function showOptions(event) {
        event.preventDefault();
        document.getElementById("myDropdown").classList.toggle("show");
    }

    if (ingredients == null) {
        return <>Loading...</>;
    }
    return (
        <form>
            <label htmlFor='recipeName'>Name: </label>
            <input id='recipeName'></input>

            <div className="dropdown">
                <button className="dropbtn" onClick={showOptions}>Dropdown</button>
                <div id="myDropdown" className="dropdown-content">
                    <input type="text" placeholder="Search.." id="myInput"/>
                        {ingredients.map(ingredient => {
                            return <a>{ingredient.name}</a>
                        })}
                </div>
            </div>
        </form>
    );
}

export default RecipeForm;