import {useEffect, useState} from "react";

const fetchIngredients = async () => {
    const res = await fetch("/api/ingredients")
    const ingr = await res.json()
    return ingr
};

const RecipeForm = ({onSave, disabled, recipe, onCancel}) => {
    const [recipeName, setRecipeName] = useState(recipe?.name ?? "");
    const [description, setDescription] = useState(recipe?.description ?? "");
    const [ingredients, setIngredients] = useState([]);
    const [selectedIngredients, setSelectedIngredients] = useState(recipe?.ingredient ?? "");

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
                recipeName,
                description,
                ingredients: selectedIngredients,
            });
        }

        return onSave({
            recipeName,
            description,
            ingredients: selectedIngredients,
        });
    };

    const handleIngredientChange = (e) => {
        const newSelectedIngredients = [...selectedIngredients];
        newSelectedIngredients.push(e.target.value);
        setSelectedIngredients(newSelectedIngredients);
    }

    const handleAmountChange = (event, id) => {
        event.preventDefault();
        const newArray = selectedIngredients.map((ingr) => {
            if (ingr === id) {
                return {...ingr, amount: event.target.value};
            } else {
                return ingr;
            }
        });
        setSelectedIngredients(newArray);
    }
    const deleteIngredient = (event, id) => {
        event.preventDefault();
        const updatedIngredients = [...selectedIngredients];
        const filteredIngredients = updatedIngredients.filter((ingredient) => Number(ingredient) !== Number(id));
        setSelectedIngredients(filteredIngredients);
    };


    return (
        <form className="RecipeForm" onSubmit={onSubmit}>
            <div className="control">
                <label htmlFor="recipeName">Name:</label>
                <input
                    value={recipeName}
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
                                (item) => Number(item.id) === Number(ingredient)
                            );

                            return (
                                <li key={ingredient.id}>
                                    {filteredIngredient && (
                                        <>
                                            {filteredIngredient.name}{" "}
                                            <input
                                                type="number"
                                                onChange={(e) => handleAmountChange(e, ingredient.id)}
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