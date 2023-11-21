function Ingredient(props) {
    const {name, unitOfMeasure, isGlutenFree, isMeatFree, isDairyFree} = props.ingredient;
    console.log(props);
    console.log(name);

    return (
        <>
            <ul>
                <li>
                    ingredient name: {name}
                </li>
                <li>
                    unit of measure: {unitOfMeasure}
                </li>
                <li>
                    gluten free? {isGlutenFree ? "yes" : "no"}
                </li>
                <li>
                    meat free? {isMeatFree ? "yes" : "no"}
                </li>
                <li>
                    dairy free? {isDairyFree ? "yes" : "no"}
                </li>
                <button>
                    show recipes which includes this ingredient
                </button>
            </ul>
        </>
    )
}
export default Ingredient;