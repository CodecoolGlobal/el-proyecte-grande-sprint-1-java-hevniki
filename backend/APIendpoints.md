 
### Authentication endpoints: 

    /api/auth
    POST : ("/register")
            Request body : { username, password }
    POST : ("/authenticate")
            Request body : { username, password }

### Ingredient endpoints
    /api/ingredients
    GET : ("/")
        Response : [{ id : number, name : string, unitOfMeasure : string, isGlutenFree : boolean, isDairyFree : boolean, isMeatFree : boolean, isEggFree : boolean }]
    GET : ("/:id")
        Response : { id : number, name : string, unitOfMeasure : string, isGlutenFree : boolean, isDairyFree : boolean, isMeatFree : boolean, isEggFree : boolean }
    POST : ("/")
        Request body : { id : number, name : string, unitOfMeasure : string, isGlutenFree : boolean, isDairyFree : boolean, isMeatFree : boolean, isEggFree : boolean }
    DELETE : ("/:id")
    PUT : ("/:id")
        Request body : { id : number, name : string, unitOfMeasure : string, isGlutenFree : boolean, isDairyFree : boolean, isMeatFree : boolean, isEggFree : boolean }

### Recipe endpoints
    /api/recipes
    GET : ("/")
        Response : [{ id : number, ingredients :  [{ id : number, name : string, unitOfMeasure : string, isGlutenFree : boolean, isDairyFree : boolean, isMeatFree : boolean, isEggFree : boolean }], name : string, description : string, isVegan : boolean, isVegetarian : boolean, isGlutenFree : boolean, isDairyFree : boolean }]
    GET : ("/:id")
        Response : { id : number, ingredients :  [{ id : number, name : string, unitOfMeasure : string, isGlutenFree : boolean, isDairyFree : boolean, isMeatFree : boolean, isEggFree : boolean }], name : string, description : string, isVegan : boolean, isVegetarian : boolean, isGlutenFree : boolean, isDairyFree : boolean }
    GET : ("/search")
        Optional query parameters : name, vegan, vegetarian, glutenFree, dairyFree, ingredient id ( ->separated by comma)
    DELETE : ("/:id")
    PUT : ("/:id")
        Request body : { id : number, ingredients :  [{ id : number, name : string, unitOfMeasure : string, isGlutenFree : boolean, isDairyFree : boolean, isMeatFree : boolean, isEggFree : boolean }], name : string, description : string, isVegan : boolean, isVegetarian : boolean, isGlutenFree : boolean, isDairyFree : boolean }
    PUT : ("/")
        Request body : { id : number, ingredients :  [{ id : number, name : string, unitOfMeasure : string, isGlutenFree : boolean, isDairyFree : boolean, isMeatFree : boolean, isEggFree : boolean }], name : string, description : string, isVegan : boolean, isVegetarian : boolean, isGlutenFree : boolean, isDairyFree : boolean }