import {useParams, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

async function deleteIngredient(id){
    const res = await fetch(`/api/ingredients/${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    });
    return await res.json();
}

function IngredientDetails() {
    const {id} = useParams();
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(false);
    const [ingredient, setIngredient] = useState(null);
    const navigate = useNavigate();

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
                <button onClick={async()=>{
                    await deleteIngredient(id);
                    navigate('/');
                }}>
                    delete this ingredient
                </button>
            </ul>
        </>
    )
}

export default IngredientDetails;