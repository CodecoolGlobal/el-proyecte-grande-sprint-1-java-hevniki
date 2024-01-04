import {Link, Outlet} from "react-router-dom";

import "./Navbar.css";
import { CurrentUserContext } from "../../CurrentUserContext";
import { useState } from "react";

function Layout(){
    const [currentUser, setCurrentUser] = useState(null);

    return(
        <CurrentUserContext.Provider value={{currentUser, setCurrentUser}}>
        <div className="Layout">
            <nav>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                    <li>
                        <Link to="/recipes">Recipes</Link>
                    </li>
                    <li>
                        <Link to="/ingredients">Ingredients</Link>
                    </li>
                    <li>
                        <Link to="/recipes/create">Add new recipe</Link>
                    </li>
                    <li>
                        <Link to="/ingredients/create">Add new ingredient</Link>
                    </li>
                    <li>
                        <Link to="/register">Register</Link>
                    </li>
                    <li>
                        <Link to="/login">Log in</Link>
                    </li>
                </ul>
            </nav>
            <Outlet/>
        </div>
        </CurrentUserContext.Provider>
    )
}

export default Layout;