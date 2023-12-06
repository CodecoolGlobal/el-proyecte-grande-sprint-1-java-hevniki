import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import Layout from "./Pages/Layout/Layout.jsx";
import {createBrowserRouter, RouterProvider} from 'react-router-dom';

import AllIngredients from "./Pages/AllIngredients.jsx";
import IngredientDetails from "./Pages/IngredientDetails.jsx";

import RecipeDetails from "./Pages/RecipeDetails.jsx";

import IngredientCreator from "./Pages/IngredientCreator.jsx";

import RecipeList from './Pages/RecipeList.jsx';
import RecipeCreator from './Pages/RecipeCreator.jsx';

const router = createBrowserRouter([
    {
        path: "/",
        element: <Layout/>,
        children:[
            {
                path: "/",
                element: <App/>,
            },
            {
                path: "/ingredients",
                element: <AllIngredients/>,
            },
            {

                path: "/ingredients/:id",
                element: <IngredientDetails/>,
            },
            {
                path: "/recipes",
                element: <RecipeList/>,
            },
            {
                path: "/recipes/:id",

                element: <RecipeDetails/>,
            },
            {
                path: "/recipes/create",
                element: <RecipeCreator/>
            },
            {
                path: "/ingredients/create",
                element: <IngredientCreator/>,
            },
        ]
    }
])

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
        <RouterProvider router={router}/>
    </React.StrictMode>,
)


