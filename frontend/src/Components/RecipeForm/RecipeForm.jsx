import {useEffect, useState} from "react";
import ingredient from "../Ingredient.jsx";

const fetchIngredients = async () => {
    const res = await fetch("/api/ingredients")
    const ingr = await res.json()
    return ingr
};

const getSelectedIngredients = (ingredients) => {
    return ingredients.map((item) => {
        return {ingredient:{id: item.ingredient.id}, amount: item.amount}
    })
}

const RecipeForm = ({onSave, disabled, recipe, onCancel}) => {
    const [name, setRecipeName] = useState(recipe?.name ?? "");
    const [description, setDescription] = useState(recipe?.description ?? "");
    const [ingredients, setIngredients] = useState([]);
    const [selectedIngredients, setSelectedIngredients] = useState(recipe ? getSelectedIngredients(recipe.ingredients) : []);

    useEffect(() => {
        fetchIngredients()
            .then((data) => {
                setIngredients(data)
            })
    }, [])


    const onSubmit = (e) => {
        e.preventDefault();

        if (recipe) {
            return onSave({
                ...recipe,
                name,
                description,
                ingredients: selectedIngredients,
            });
        }

        return onSave({
            name,
            description,
            ingredients: selectedIngredients,
        });
    };

    const handleIngredientChange = (e) => {
        const newSelectedIngredients = [...selectedIngredients];
        newSelectedIngredients.push({ingredient:{id:e.target.value}, amount: 0});
        setSelectedIngredients(newSelectedIngredients);
    }

    const handleAmountChange = (event, id) => {
        event.preventDefault();
        const newArray = selectedIngredients.map((ingr) => {
            if (Number(ingr.ingredient.id) === Number(id)) {
                return {ingredient:ingr.ingredient, amount: event.target.value};
            } else {
                return ingr;
            }
        });
        setSelectedIngredients(newArray);
    }
    const deleteIngredient = (event, id) => {
        event.preventDefault();
        const updatedIngredients = [...selectedIngredients];
        const filteredIngredients = updatedIngredients.filter((ingredient) => Number(ingredient.ingredient.id) !== Number(id));
        setSelectedIngredients(filteredIngredients);
    };


    return (
        <form className="RecipeForm" onSubmit={onSubmit}>
            <div className="control">
                <label htmlFor="name">Name:</label>
                <input
                    value={name}
                    onChange={(e) => setRecipeName(e.target.value)}
                    name="recipeName"
                    id="recipeName"
                />
            </div>

            <div className="control">
                <label htmlFor="description">Description:</label>
                <input
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    name="description"
                    id="description"
                />
            </div>

            <div className="control">
                <label htmlFor="ingredient">Ingredient</label>
                <select value={selectedIngredients}
                        onChange={handleIngredientChange}>
                    <option value=""> Select ingredients</option>

                    {ingredients.map((ingredient) => (
                        <option key={ingredient.id} value={ingredient.id}>{ingredient.name}</option>
                    ))}
                </select>
            </div>

            <div className="control">
                <label htmlFor="ingredients">Ingredients:</label>
                <ul>
                    {selectedIngredients.length > 0 ? (
                        selectedIngredients.map((ingredient) => {
                            const filteredIngredient = ingredients.find(
                                (item) => {
                                    return Number(item.id) === Number(ingredient.ingredient.id)
                                });
                            return (
                                <li key={ingredient.ingredient.id}>
                                    {filteredIngredient && (
                                        <>
                                            {filteredIngredient.name}{" "}
                                            <input
                                                type="number"
                                                value={ingredient.amount}
                                                onChange={(e) => handleAmountChange(e, filteredIngredient.id)}
                                            />{" "}
                                            {filteredIngredient.unitOfMeasure}
                                            <button onClick={(event) => deleteIngredient(event, filteredIngredient.id)}>remove
                                            </button>

                                        </>
                                    )}
                                </li>
                            );
                        })
                    ) : (
                        <li>No ingredients selected</li>
                    )}
                </ul>
            </div>


            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    {recipe ? "Update Recipe" : "Create Recipe"}
                </button>

                <button type="button" onClick={onCancel}>
                    Cancel
                </button>
            </div>
        </form>
    );
};

export default RecipeForm;