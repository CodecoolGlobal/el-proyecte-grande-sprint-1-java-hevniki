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
	const [selectedIngredientIds, setSelectedIngredientIds] = useState([]);
	const [recipeName, setRecipeName] = useState(null);
	const [description, setDescription] = useState(null);

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

	function handleFilterChange(event) {
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

		if (selectedIngredient != null && !selectedIngredientIds.includes(id)) {
			setSelectedIngredients([...selectedIngredients, { ingredient: selectedIngredient, amount: 1 }]);
			setSelectedIngredientIds([selectedIngredientIds, id]);
		}
	}

	function handleNumberOfIngredientsChange(event) {
		event.preventDefault();
		const id = event.target.id;

		const newArray = selectedIngredients.map((ingr) => {
			if (ingr.ingredient.id == id) {
				return { ...ingr, amount: event.target.value };
			} else {
				return ingr;
			}
		});

		setSelectedIngredients(newArray);

	}

	function handleNameChange(event) {
		setRecipeName(event.target.value);
	}

	function handleDescriptionChange(event) {
		setDescription(event.target.value);
	}

	function handleSubmit() {

	}

	if (ingredients == null) {
		return <>Loading...</>;
	}

	return (
		<form>
			<label htmlFor='recipeName'>Name: </label>
			<input id='recipeName' onChange={handleNameChange}></input>
			<br></br>

			<div className="dropdown">
				<button className="dropbtn" onClick={showOptions}>Dropdown</button>
				<div id="myDropdown" className="dropdown-content">
					<input type="text" placeholder="Search.." id="myInput" onChange={handleFilterChange} />
					{filteredIngredients.map(ingredient => {
						return <a key={ingredient.id} id={ingredient.id} onClick={handleIngredientSelect}>{ingredient.name}</a>
					})}
				</div>
			</div>
			<br></br>
			Ingredients:
			<ul>
				{selectedIngredients.map((ingredient) => {
					return <li>{ingredient.ingredient.name} <input id={ingredient.ingredient.id} type='number' onChange={handleNumberOfIngredientsChange}></input> {ingredient.ingredient.unitOfMeasure}</li>
				})}
			</ul>
			<br></br>
			<label htmlFor='description'></label>
			<textarea id='description' onChange={handleDescriptionChange}></textarea>
			<button type='submit' onClick={handleSubmit}>Submit</button>
		</form>
	);
}

export default RecipeForm;