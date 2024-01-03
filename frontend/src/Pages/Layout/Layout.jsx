import {Link, Outlet} from "react-router-dom";

import "./Navbar.css";

function Layout(){
    return(
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
                </ul>
            </nav>
            <Outlet/>
        </div>
    )
}

export default Layout;