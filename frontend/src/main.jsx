import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import Layout from "./Layout.jsx";
import {createBrowserRouter, RouterProvider} from 'react-router-dom';

import AllIngredients from "./AllIngredients.jsx";
import IngredientDetails from "./IngredientDetails.jsx";

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
                path: "/ingredient/id",
                element: <IngredientDetails/>,
            }
        ]
    }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
   <RouterProvider router={router}/>
  </React.StrictMode>,
)


