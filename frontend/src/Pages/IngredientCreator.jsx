import {useState} from "react";
import {useNavigate} from "react-router-dom";

async function postIngredient(newIngredient) {
    const res = await fetch("/api/ingredients", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newIngredient)
    });
    return await res.json();
}

function IngredientCreator() {
    const [ingredient, setIngredient] = useState(
        {
            id: null,
            name: "",
            unitOfMeasure: "",
            isGlutenFree: false,
            isDairyFree: false,
            isMeatFree: false,
            isEggFree: false,
        }
    )
    const navigate = useNavigate();

    function onCheckBoxChange(e){
        const updatedIngredient = {...ingredient};
        if (ingredient[e.target.id]) {
            updatedIngredient[e.target.id] = false
        }
        else if (!ingredient[e.target.id]) {
            updatedIngredient[e.target.id] = true
        }
        setIngredient(updatedIngredient);
    }

    function onTextChange(e){
        const updatedIngredient = {...ingredient};
        updatedIngredient[e.target.id]=e.target.value;
        setIngredient(updatedIngredient);
    }

    return (
        <div>
            <h3>Add new ingredient</h3>
            <div>
                <label htmlFor="name"><b>Name: </b></label>
                <br></br>
                <input
                    value={ingredient.name}
                    name="name"
                    id="name"
                    onChange={onTextChange}
                />
            </div>
            <div>
                <label htmlFor="unitOfMeasure"><b>Unit of measure: </b></label>
                <br></br>
                <input
                    value={ingredient.unitOfMeasure}
                    name="unitOfMeasure"
                    id="unitOfMeasure"
                    onChange={onTextChange}
                />
            </div>
            <br></br>
            <div>
                <h3>Is your ingredient...</h3>
                <label htmlFor="isGlutenFree">Gluten Free?</label>
                <input type="checkbox"
                       onChange={onCheckBoxChange}
                       value={ingredient.isGlutenFree}
                       id="isGlutenFree"
                       name="isGlutenFree"
                />
            </div>
            <div>
                <label htmlFor="isDairyFree">Dairy Free?</label>
                <input type="checkbox"
                       onChange={onCheckBoxChange}
                       value={ingredient.isDairyFree}
                       id="isDairyFree"
                       name="isDairyFree"
                />
            </div>
            <div>
                <label htmlFor="isMeatFree">Meat Free?</label>
                <input type="checkbox"
                       onChange={onCheckBoxChange}
                       value={ingredient.isMeatFree}
                       id="isMeatFree"
                       name="isMeatFree"
                />
            </div>
            <div>
                <label htmlFor="isEggFree">Egg Free?</label>
                <input type="checkbox"
                       onChange={onCheckBoxChange}
                       value={ingredient.isEggFree}
                       id="isEggFree"
                       name="isEggFree"
                />
            </div>
            <br></br>
            <button onClick={async ()=>{
                await postIngredient(ingredient);
                navigate("/");
            }}> Add ingredient</button>
        </div>
    )
}

export default IngredientCreator;
