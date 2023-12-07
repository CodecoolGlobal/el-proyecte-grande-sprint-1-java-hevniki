import {Link} from "react-router-dom";

function IngredientForRecipe(props) {
    const {id, ingredient, amount} = props.ingredient;


    return (
        <>
            <ul>
                <li>
                    {amount} {ingredient.unitOfMeasure} of {ingredient.name.toLowerCase()}
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