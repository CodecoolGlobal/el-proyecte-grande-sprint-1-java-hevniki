import React, { useState } from 'react';
import axios from "axios";
import "./LoginForm.css";

function LoginForm() {
	const [errorMessages, setErrorMessages] = useState({});
	const [isSubmitted, setIsSubmitted] = useState(false);

	const [user, setUser] = useState();
	const [username, setUsername] = useState('');
	const [password, setPassword] = useState('');

	function handleNameChange(event) {
		setUsername(event.target.value);
	}

	function handlePasswordChange(event) {
		setPassword(event.target.value);
	}

	function renderErrorMessage(name) {
		name === errorMessages.name && (
			<div className="error">{errorMessages.message}</div>
		);
	}

	const handleSubmit = async e => {
		e.preventDefault();
		const user = { username, password };
		const response = await axios.post(
		  "/api/auth/authenticate",
		  user
		);
		setUser(response.data)
		localStorage.setItem('user', response.data)
		console.log(response.data)
	  };

	if (user) {
		return <div>User is here: {user.username}</div>
	}

	return (
		<div className="form">
			<form onSubmit={handleSubmit}>
				<div className="input-container">
					<label>Username </label>
					<input type="text" name="uname" onInput={handleNameChange} required />
					{renderErrorMessage("uname")}
				</div>
				<div className="input-container">
					<label>Password </label>
					<input type="password" name="pass" onInput={handlePasswordChange} required />
					{renderErrorMessage("pass")}
				</div>
				<div className="button-container">
					<input type="submit" />
				</div>
			</form>
		</div>)
}

export default LoginForm;