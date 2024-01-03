import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import Layout from "./Pages/Layout/Layout.jsx";
import {createBrowserRouter, RouterProvider} from 'react-router-dom';

import IngredientList from "./Pages/IngredientList.jsx";
import IngredientDetails from "./Pages/IngredientDetails.jsx";

import RecipeDetails from "./Pages/RecipeDetails/RecipeDetails.jsx";

import IngredientCreator from "./Pages/IngredientCreator.jsx";

import RecipeList from './Pages/RecipeList.jsx';
import RecipeCreator from './Pages/RecipeCreator.jsx';
import RegistrationForm from "./Pages/RegistrationForm.jsx";

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
                path: "/register",
                element: <RegistrationForm/>
            },
            {
                path: "/ingredients",
                element: <IngredientList/>,
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


