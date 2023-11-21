import {Link, Outlet} from "react-router-dom";

function Layout(){
    return(
        <div className="Layout">
            <nav>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                </ul>
            </nav>
            <Outlet/>
        </div>
    )
}

export default Layout;