import { Link, Outlet, useNavigate } from "react-router-dom";

import "./Navbar.css";
import { CurrentUserContext } from "../../CurrentUserContext";
import { useState, useContext } from "react";

function Layout() {
          const {setCurrentUser} = useContext(CurrentUserContext);
          const navigate = useNavigate();

          async function handleLogout() {
            const res = fetch("/api/logout");
            setCurrentUser(null);
            navigate("/");
          }

	return (
			<div className="Layout">
				<nav style={{ width: "100%" }}>
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
						{!currentUser &&
							<li>
								<Link to="/register">Register</Link>
							</li>
							<li>
								<Link to="/login">Log in</Link>
							</li>
						}
						{currentUser ?
							<li>
								<button onClick={handleLogout}>
									Log out
								</button>
							</li>
						}
					</ul>
				</nav>
				<Outlet />
			</div>
	)
}

export default Layout;
