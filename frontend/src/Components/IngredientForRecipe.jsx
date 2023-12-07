function IngredientForRecipe(props) {
	const { id, ingredient, amount } = props.ingredient;

	return (
		<>
			<li>
				{amount} {ingredient.unitOfMeasure} of {ingredient.name.toLowerCase()}
			</li>
			<hr></hr>
		</>
	)
}

export default IngredientForRecipe;