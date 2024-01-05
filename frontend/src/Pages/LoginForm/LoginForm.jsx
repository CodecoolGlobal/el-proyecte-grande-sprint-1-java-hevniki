import React, { useContext, useState } from 'react';
import axios from "axios";
import "./LoginForm.css";
import { CurrentUserContext } from '../../CurrentUserContext';
import { useNavigate } from 'react-router-dom';
import { Link as RouterLink } from 'react-router-dom';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';

function LoginForm() {
	const navigate = useNavigate();

	const [errorMessages, setErrorMessages] = useState({});
	const {currentUser, setCurrentUser} = useContext(CurrentUserContext);
	const [username, setUsername] = useState('');
	const [password, setPassword] = useState('');

	const handleNameChange = (event) => {
		setUsername(event.target.value);
	};

	const handlePasswordChange = (event) => {
		setPassword(event.target.value);
	};

	const renderErrorMessage = (name) => {
		return (
			name === errorMessages.name && (
				<div className="error">{errorMessages.message}</div>
			)
		);
	};

	const handleSubmit = async (event) => {
		event.preventDefault();
		const user = { username, password };
		const response = await axios.post(
		  "/api/auth/authenticate",
		  user
		);
		setCurrentUser(response.data)
		localStorage.setItem('user', response.data)
    navigate('/recipes');
	  };

	if (currentUser != null) {
		return <div>User is here: {currentUser.username}</div>
	}
	return (
		<ThemeProvider theme={createTheme()}>
			<Container component="main" maxWidth="xs">
				<CssBaseline />
				<Box
					sx={{
						marginTop: 8,
						display: 'flex',
						flexDirection: 'column',
						alignItems: 'center',
					}}
				>
					<Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
					</Avatar>
					<Typography component="h1" variant="h5">
						Sign in
					</Typography>
					<Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
						<TextField
							margin="normal"
							required
							fullWidth
							id="username"
							label="Username"
							name="username"
							autoComplete="username"
							autoFocus
							value={username}
							onChange={handleNameChange}
						/>
						{renderErrorMessage('username')}
						<TextField
							margin="normal"
							required
							fullWidth
							name="password"
							label="Password"
							type="password"
							id="password"
							autoComplete="current-password"
							value={password}
							onChange={handlePasswordChange}
						/>
						{renderErrorMessage('password')}
						<Button
							type="submit"
							fullWidth
							variant="contained"
							sx={{ mt: 3, mb: 2 }}
						>
							Sign In
						</Button>
						<Grid container>
							<Grid item xs>
								<Link component={RouterLink} to="/forgot-password" variant="body2">
									Forgot password?
								</Link>
							</Grid>
							<Grid item>
								<Link component={RouterLink} to="/register" variant="body2">
									{"Don't have an account? Sign Up"}
								</Link>
							</Grid>
						</Grid>
					</Box>
				</Box>
			</Container>
		</ThemeProvider>
	);
}

export default LoginForm;
