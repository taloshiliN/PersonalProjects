import { Link } from "react-router-dom";
import "../SignupAndLogin.css";
function Signup() {
  return (
    <>
      <form action="" method="post" noValidate>
        <fieldset>
          <legend>Sign Up</legend>
          <div>
            <label htmlFor="Firstname">FirstName</label>
            <input type="text" name="FirstName" id="FName" />
          </div>
          <div>
            <label htmlFor="Lastname">LastName</label>
            <input type="text" name="LastName" id="LName" />
          </div>
          <div>
            <label htmlFor="Email">Email</label>
            <input type="Email" name="Email" id="Email" />
          </div>
          <div>
            <label htmlFor="Password">Password</label>
            <input type="password" name="Password" id="Password" />
          </div>
          <button>Submit</button>
          <p>
            Already have an account? <Link to="/Login">Log in</Link>
          </p>
        </fieldset>
      </form>
    </>
  );
}

export default Signup;
