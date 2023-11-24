import { Link } from "react-router-dom";


function IngredientForRecipe (props) {
   const { id, name, amount, unitOfMeasure} = props.ingredient;

    return (
        <>
            <ul>
                <li>
                    {amount} {unitOfMeasure} of {name}
                </li>

                <Link to={`/ingredients/${id}`}>
                    <button>
                        show more details of this ingredient
                    </button>
                </Link>
            </ul>
        </>
    )
}
export default IngredientForRecipe;