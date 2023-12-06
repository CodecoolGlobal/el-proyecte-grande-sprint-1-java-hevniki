import {useState} from "react";


function SearchBar({onSearch}) {
    const [properties, setProperties] = useState({
        vegan: false,
        vegetarian: false,
        dairyFree: false,
        glutenFree: false,
        name: ""
    });

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
            <button
            onClick={async ()=>{await onSearch(properties)}}>search</button>
        </div>
    )

}

export default SearchBar;