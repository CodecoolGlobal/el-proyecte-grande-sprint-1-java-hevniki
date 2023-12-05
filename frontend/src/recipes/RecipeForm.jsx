import {useEffect, useState} from 'react';
import './RecipeForm.css'
import {useNavigate} from "react-router-dom";

async function fetchIngredients() {
	const response = await fetch("/api/ingredients");
	return response.json();
}

function RecipeForm({onSave}) {
	const [ingredients, setIngredients] = useState(null);
	
	const [recipeName, setRecipeName] = useState(null);
	const [description, setDescription] = useState(null);
	const [selectedIngredients, setSelectedIngredients] = useState([]);

	const [filteredIngredients, setFilteredIngredients] = useState([]);
	const [filterValue, setFilterValue] = useState('');
	const [selectedIngredientIds, setSelectedIngredientIds] = useState([]);
	const navigate = useNavigate();

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
		const id = Number(event.target.id);
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
			if (ingr.ingredient.id === id) {
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

	async function handleSubmit(event) {
		event.preventDefault();

		const newSelectedIngredients = selectedIngredients.map(ingr => {
			console.log(ingr)
			return {amount: ingr.amount, ingredient: {id:ingr.ingredient.id}}
		})
		await onSave({recipeName, description, newSelectedIngredients});
		navigate('/');
	}

	if (ingredients == null) {
		return <>Loading...</>;
	}

	return (
		<form className='recipeForm'>
			<label htmlFor='recipeName'>Name: </label>
			<input id='recipeName' onChange={handleNameChange}></input>
			<br></br>

			<div className="dropdown">
				<button className="dropbtn" onClick={showOptions}>Ingredients</button>
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
					return <li key={ingredient.ingredient.id}>{ingredient.ingredient.name} <input id={ingredient.ingredient.id} type='number' onChange={handleNumberOfIngredientsChange}></input> {ingredient.ingredient.unitOfMeasure}</li>
				})}
			</ul>
			<br></br>
			<label htmlFor='description'>Description:</label>
			<br></br>
			<textarea id='description' onChange={handleDescriptionChange}></textarea>
			<button type='submit' onClick={handleSubmit}>Submit</button>
		</form>
	);
}

export default RecipeForm;