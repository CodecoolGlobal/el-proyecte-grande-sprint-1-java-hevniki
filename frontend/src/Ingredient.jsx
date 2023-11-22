function Ingredient(props) {
    const {name, unitOfMeasure, isGlutenFree, isMeatFree, isDairyFree, isEggFree} = props.ingredient;
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
                    show details
                </button>
            </ul>
        </>
    )
}
export default Ingredient;