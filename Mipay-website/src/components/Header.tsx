import { Link } from "react-router-dom";
import "./Header.css";

function Header() {
  return (
    <nav className="navbar navbar-expand-lg bg-body-tertiary">
      <div className="container-fluid">
        <div className="collapse navbar-collapse" id="navbarNavDropdown">
          <ul className="navbar-nav">
            <li>
              <Link className="nav-link" to="/Home">
                <strong>MIPAY</strong>
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/Home">
                Home
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/Events">
                Events
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/About">
                About
              </Link>
            </li>
            {/* This is the search, sign up and login button */}
            <form className="nav-link-search-bar" role="search">
              <input
                className="search-bar"
                type="search"
                placeholder="Search"
                aria-label="Search"
              />
              <button className="btn btn-outline-success" type="submit">
                Search
              </button>
              <button className="btn-signup">
                <Link to="/Signup" className="btn btn-primary" type="button">
                  Sign Up
                </Link>
              </button>
              <button className="btn-login">
                <Link to="/Login" className="btn btn-secondary" type="button">
                  Log in
                </Link>
              </button>
            </form>
          </ul>
        </div>
      </div>
    </nav>
  );
}

export default Header;
