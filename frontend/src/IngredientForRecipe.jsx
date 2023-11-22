import { Link } from "react-router-dom";
import {useEffect, useState} from "react";




function IngredientForRecipe (props) {
   /*
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(false);
    const [ingredient, setIngredient] = useState(null);

    */
    const id = props.props[0];
    const name = props.props[1];
    const amount = props.props[2];
    const unit = props.props[3];


   /* async function fetchIngredient(id) {
        try {
            const res = await fetch(`/api/ingredients/${id}`);

            if (!res.ok) {
                if (res.status === 404) {
                    throw new Error("Ingredient Not Found");
                }
            }
            return res.json();
        } catch (error) {
            setError(error.message);

        }
    }
    useEffect(() => {
        async function task() {
            const ingredient = await fetchIngredient(id);
            setIngredient(ingredient);
            setLoading(false);
        }
        task();

    }, []);

    if (error) {
        return (
            <div>
                404 error, page not found
            </div>
        )
    }

    if (loading) {
        return (
            <div>
                loading
            </div>
        )
    }

    */
    return (
        <>
            <ul>
                <li>
                    {amount} {unit} of {name}
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