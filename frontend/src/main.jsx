import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';
import Layout from "./Pages/Layout/Layout.jsx";
import {createBrowserRouter, RouterProvider} from 'react-router-dom';
import { useState } from 'react';

import IngredientList from "./Pages/IngredientList.jsx";
import IngredientDetails from "./Pages/IngredientDetails.jsx";
import RecipeDetails from "./Pages/RecipeDetails/RecipeDetails.jsx";
import IngredientCreator from "./Pages/IngredientCreator.jsx";
import RecipeList from './Pages/RecipeList.jsx';
import RecipeCreator from './Pages/RecipeCreator.jsx';
import RegistrationForm from "./Pages/RegistrationForm/RegistrationForm.jsx";
import LoginForm from './Pages/LoginForm/LoginForm.jsx';
import RecipeUpdater from "./Pages/RecipeUpdater.jsx";
import YourProblem from "./Components/YourProblem.jsx";

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
        path: "/recipes/update/:id",
        element: <RecipeUpdater />,
      },
      {
        path: "/recipes/create",
        element: <RecipeCreator/>
      },
      {
        path: "/ingredients/create",
        element: <IngredientCreator/>,
      },
      {
        path: "/login",
        element: <LoginForm/>,
      },
      {
        path: "/forgot-password",
        element: <YourProblem/>
      }
    ]
  }
])

function initCurrentUser() {
  const user = localStorage.getItem('user');
  return user ? user : null; // TODO: make condition checks check for `undefined`
}

function Root() {
  const [currentUser, setCurrentUser] = useState(initCurrentUser);
  return    <React.StrictMode>
              <CurrentUserContext.Provider value={{ currentUser, setCurrentUser }}>
                <RouterProvider router={router}/>
              </CurrentUserContext.Provider>
            </React.StrictMode>

}
ReactDOM.createRoot(document.getElementById('root')).render(<Root />);
