import {useState, useEffect} from "react";

async function fetchIngredients() {
    const res = await fetch("/api/ingredients");
    return await res.json();

}

function SearchBar({onSearch}) {
    const [properties, setProperties] = useState({
        vegan: false,
        vegetarian: false,
        dairyFree: false,
        glutenFree: false,
        name: "",
        ingredients:[]
    });
    const [ingredients, setIngredients] = useState([]);
    const [selectedIngredients, setSelectedIngredients] = useState([]);
    const [shownIngredientId, setShownIngredientId] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        async function task() {
            const fetchedIngredients = await fetchIngredients();
            setIngredients(fetchedIngredients);
            setLoading(false);
        }

        task();
    }, []);

    function onCheck(e) {
        const updatedProperties = {...properties};
        updatedProperties[e.target.id] = e.target.checked;
        setProperties(updatedProperties);
    }

    function onInput(e) {
        const updatedProperties = {...properties};
        updatedProperties[e.target.id] = e.target.value;
        setProperties(updatedProperties);
    }

    function handleSelect() {
        const newSelectedIngredients = [...selectedIngredients];
        const foundIngredient = ingredients.find(element => element.id === Number(shownIngredientId));
        newSelectedIngredients.push(foundIngredient);
        setSelectedIngredients(newSelectedIngredients);
        const newIngredients = [...ingredients];
        const filteredIngredients = newIngredients.filter(element => {
            return element.id !== Number(shownIngredientId);
        });
        setIngredients(filteredIngredients);
        setShownIngredientId(null);
    }

    function handleRemove(id) {
        id = Number(id);
        const newSelectedIngredients = [...selectedIngredients];
        const newIngredients = [...ingredients];
        const foundIngredient = selectedIngredients.find(element => id === element.id);
        newIngredients.push(foundIngredient);
        const filteredIngredients = newSelectedIngredients.filter(element => id !== element.id);
        setIngredients(newIngredients);
        setSelectedIngredients(filteredIngredients);
    }

    if (loading) {
        return <div> loading</div>
    }

    return (
        <div>
            <label htmlFor='name'>name:</label>
            <input id='name'
                   value={properties.name}
                   onChange={onInput}>
            </input>
            <label htmlFor='vegan'>vegan?</label>
            <input id='vegan'
                   type='checkbox'
                   checked={properties.vegan}
                   onChange={onCheck}>
            </input>
            <label htmlFor='vegetarian'>vegetarian?</label>
            <input id='vegetarian'
                   type='checkbox'
                   checked={properties.vegetarian}
                   onChange={onCheck}>
            </input>
            <label htmlFor='dairyFree'> dairy free?</label>
            <input id='dairyFree'
                   type='checkbox'
                   checked={properties.dairyFree}
                   onChange={onCheck}>
            </input>
            <label htmlFor='glutenFree'>gluten free?</label>
            <input id='glutenFree'
                   type='checkbox'
                   checked={properties.glutenFree}
                   onChange={onCheck}>
            </input>
            <select onChange={(e) => {
                setShownIngredientId(e.target.value)
            }}>
                <option value={null}>please select ingredient</option>
                {ingredients.map((ingredient) => {
                        return (<option key={ingredient.id} value={ingredient.id}>{ingredient.name}</option>)
                    }
                )}
            </select>
            <button onClick={handleSelect}>select</button>
            <ul>Selected Ingredients:
                {selectedIngredients.map((ingredient) => {
                    return (<li key={ingredient.id}>{ingredient.name}
                        <button onClick={() => {
                            handleRemove(ingredient.id)
                        }}>x
                        </button>
                    </li>)
                })}
            </ul>
            <button
                onClick={async () => {
                    const newProperties={...properties};
                    newProperties.ingredients=[];
                    selectedIngredients.forEach((element)=>{newProperties.ingredients.push(element.id)});
                    setProperties(newProperties);
                    await onSearch(newProperties)
                }}>search
            </button>
        </div>
    )

}

export default SearchBar;