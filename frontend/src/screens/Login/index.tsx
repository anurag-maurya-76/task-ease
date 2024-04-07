import React, { useState } from "react";
import styles from "./Login.module.scss";
import { useNavigate } from "react-router-dom";
import formStyles from "../../form.module.scss";
import { loginService } from "../../services/loginService";

const Login = () => {
  const navigate = useNavigate();
  const [mode, setMode] = useState("Login");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const handleSubmit = async () => {
    if (mode === "Login") {
      try {
        const response = await loginService.login({
          email,
          password,
        });
        if (response.data.statusCode === 205) {
          navigate("/dashboard");
        }
      } catch (e) {
        console.log("Invalid user");
      }
    } else if (mode === "Register") {
      const response = await loginService.register({
        email,
        password,
      });
      if (response.data.statusCode === 203) {
        setMode("Login");
      }
    }
  };
  return (
    <div className={styles.login}>
      <div className={styles.login__logo}></div>
      <div className={styles.login__div}>
        <form className={formStyles.form}>
          <div className={styles.login__title}>TODO</div>
          <label htmlFor="Email">Email</label>
          <input
            id={"Email"}
            type="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
          />
          <label htmlFor="Password">Password</label>
          <input
            id={"Password"}
            type="password"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
          />
          {mode === "Register" && (
            <>
              <label htmlFor="ConfirmPassword">Confirm Password</label>
              <input id={"ConfirmPassword"} type="password" />
            </>
          )}
          <button type="button" onClick={handleSubmit}>
            {mode}
          </button>
          <div className={styles.login__registerLink}>
            <p
              onClick={() =>
                setMode((state) => (state === "Login" ? "Register" : "Login"))
              }
            >
              {mode === "Register" ? "Login instead?" : "Register"}
            </p>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
