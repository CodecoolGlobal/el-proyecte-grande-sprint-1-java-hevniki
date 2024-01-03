import React, { useState } from 'react';
import "./LoginForm.css";

function LoginForm() {
	const [errorMessages, setErrorMessages] = useState({});
	const [isSubmitted, setIsSubmitted] = useState(false);

	function renderErrorMessage(name) {
		name === errorMessages.name && (
			<div className="error">{errorMessages.message}</div>
		);
	}

	function handleSubmit(event) {
		event.preventDefault();
	}


	return (
		<div className="form">
			<form onSubmit={handleSubmit}>
				<div className="input-container">
					<label>Username </label>
					<input type="text" name="uname" required />
					{renderErrorMessage("uname")}
				</div>
				<div className="input-container">
					<label>Password </label>
					<input type="password" name="pass" required />
					{renderErrorMessage("pass")}
				</div>
				<div className="button-container">
					<input type="submit" />
				</div>
			</form>
		</div>)
}

export default LoginForm