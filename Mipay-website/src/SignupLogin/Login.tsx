import { Link } from "react-router-dom";

function Login() {
  return (
    <>
      {" "}
      <form action="" method="post" noValidate>
        <fieldset>
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
            Don't have an Account? <Link to="/Signup">Sign Up</Link>
          </p>
        </fieldset>
      </form>
    </>
  );
}
export default Login;
