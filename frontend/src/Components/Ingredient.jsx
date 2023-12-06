import { Link } from "react-router-dom";
function Ingredient(props) {
    const {id, name, unitOfMeasure, isGlutenFree, isMeatFree, isDairyFree, isEggFree} = props.ingredient;
    return (
        <>
            <ul>
                <li>
                    ingredient name: {name}
                </li>
                <li>
                    unit of measure: {unitOfMeasure}
                </li>

                <Link to={`/ingredients/${id}`}>
                    <button>
                        show details
                    </button>
                </Link>
            </ul>
        </>
    )
}
export default Ingredient;