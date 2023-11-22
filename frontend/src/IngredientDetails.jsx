
import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";


function IngredientDetails() {
    const {id} = useParams();
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(false);
    const [ingredient, setIngredient] = useState(null);

    async function fetchIngredient(id) {
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
    return (
        <>
            <ul>
                <li>

                    ingredient name: {ingredient.name}
                </li>
                <li>
                    unit of measure: {ingredient.unitOfMeasure}
                </li>
                <li>
                    gluten free? {ingredient.isGlutenFree ? "yes" : "no"}
                </li>
                <li>
                    meat free? {ingredient.isMeatFree ? "yes" : "no"}
                </li>
                <li>
                    dairy free? {ingredient.isDairyFree ? "yes" : "no"}
                </li>
                <button>
                    show recipes which includes this ingredient
                </button>
            </ul>
        </>
    )
}

export default IngredientDetails;