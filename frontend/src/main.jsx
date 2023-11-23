import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import Layout from "./Layout.jsx";
import {createBrowserRouter, RouterProvider} from 'react-router-dom';

import AllIngredients from "./AllIngredients.jsx";
import IngredientDetails from "./IngredientDetails.jsx";

import RecipeDetails from "./RecipeDetails.jsx";
import RecipeList from './recipes/RecipeList.jsx';
import RecipeForm from './recipes/RecipeForm.jsx';


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
                path: "/create-recipe",
                element: <RecipeForm/>
            }
        ]
    }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
   <RouterProvider router={router}/>
  </React.StrictMode>,
)


