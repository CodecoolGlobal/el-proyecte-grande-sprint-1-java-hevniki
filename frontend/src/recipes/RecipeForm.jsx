import React, { useEffect, useState } from 'react';
import './RecipeForm.css'

async function fetchIngredients() {
    const response = await fetch("/api/ingredients");
    const data = response.json();
    return data;
}

function RecipeForm() {
    const [ingredients, setIngredients] = useState(null);
    const [filteredIngredients, setFilteredIngredients] = useState([]);
    const [filterValue, setFilterValue] = useState('');
    const [selectedIngredients, setSelectedIngredients] = useState([]);

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

    function handleChange(event) {
        setFilterValue(event.target.value);
        if (event.target.value.length > 2) {
            filterIngredients();
        } 
    }

    function filterIngredients() {
        setFilteredIngredients(ingredients.filter((ingredient) => {
            return ingredient.name.substring(0, filterValue.length).toLowerCase() === filterValue.toLowerCase();
        }));
    }

    function handleIngredientSelect(event) {
        event.preventDefault();
        const id = event.target.id;
        console.log(event.target.id);
        console.log(id);
        setSelectedIngredients([...selectedIngredients, id]);
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
                    <input type="text" placeholder="Search.." id="myInput" onChange={handleChange}/>
                        {filteredIngredients.map(ingredient => {
                            return <a id={ingredient.id} onClick={handleIngredientSelect}>{ingredient.name}</a>
                        })}
                </div>
            </div>
        </form>
    );
}

export default RecipeForm;