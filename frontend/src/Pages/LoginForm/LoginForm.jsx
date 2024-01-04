import React, { useState } from 'react';
import "./LoginForm.css";

function LoginForm() {
	const [errorMessages, setErrorMessages] = useState({});
	const [isSubmitted, setIsSubmitted] = useState(false);
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

	function handleSubmit(event) {
		event.preventDefault();

		const authenticationRequest = {
			username: username,
			password: password
		}

		const res = fetch("/api/auth/authenticate", {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(authenticationRequest),
		});
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