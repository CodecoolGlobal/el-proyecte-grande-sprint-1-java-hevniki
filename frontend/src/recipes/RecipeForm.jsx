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
		const selectedIngredient = ingredients.filter((ingr => id === ingr.id))[0];
		if (selectedIngredient != null && !selectedIngredients.includes(selectedIngredient)) {
			setSelectedIngredients([...selectedIngredients, {ingredient: selectedIngredient, amount: 1}]);
		}
	}

	function handleNumberOfIngredientsChange(event) {
		event.preventDefault();
		const id = event.target.id;

		const ingredient = selectedIngredients.find((ingr => ingr.ingredient.id = id));
		if (ingredient != null) {
			const newArray = selectedIngredients.map((ingr) => {
				if (ingr.ingredient.id == id) {
					return {...ingr, amount: event.target.value};
				} else {
					return ingr;
				}
			});

			setSelectedIngredients(newArray);
		}
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
					<input type="text" placeholder="Search.." id="myInput" onChange={handleChange} />
					{filteredIngredients.map(ingredient => {
						return <a id={ingredient.id} onClick={handleIngredientSelect}>{ingredient.name}</a>
					})}
				</div>
				Ingredients:
				<ul>
					{selectedIngredients.map((ingredient) => {
						return <li>{ingredient.ingredient.name} <input id={ingredient.ingredient.id} type='number' onChange={handleNumberOfIngredientsChange}></input> {ingredient.ingredient.unitOfMeasure}</li>
					})}
				</ul>
				<br></br>
				<label htmlFor='description'></label>
				<textarea id='description' ></textarea>
			</div>
		</form>
	);
}

export default RecipeForm;