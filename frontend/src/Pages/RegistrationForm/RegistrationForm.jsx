import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link as RouterLink } from 'react-router-dom';
import axios from 'axios';
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


function RegistrationForm() {
    const defaultTheme = createTheme();

    const navigate = useNavigate();

    const [input, setInput] = useState({});
    const [errors, setErrors] = useState({});

    const handleChange = (event) => {
        const { name, value } = event.target;
        setInput((prevInput) => ({ ...prevInput, [name]: value }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (validate()) {
            const userData = {
                username: input.username,
                password: input.password,
            };

            try {
                const response = await axios.post('/api/auth/register', userData);

                if (response.data.token) {
                    console.log(response.data);

                    setInput({
                        username: '',
                        password: '',
                        confirm_password: '',
                    });

                    alert('Form is submitted');
                    navigate('/login');
                } else {
                    setErrors({ ...errors, username: 'Username is already taken' });
                }
            } catch (error) {
                console.error('Error submitting form:', error);
            }
        }
    };

    const validate = () => {
        let isValid = true;
        const newErrors = {};

        if (!input.username) {
            isValid = false;
            newErrors.username = 'Please enter your username';
        } else if (input.username.length < 6) {
            isValid = false;
            newErrors.username = 'Username should be at least 6 characters long';
        }

        if (!input.password) {
            isValid = false;
            newErrors.password = 'Please enter your password';
        } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/.test(input.password)) {
            isValid = false;
            newErrors.password = 'The password should contain at least one lowercase, one uppercase, one digit, and one special character. The password should be at least 8 characters long.';
        }

        if (input.password !== input.confirm_password) {
            isValid = false;
            newErrors.confirm_password = "Passwords don't match";
        }

        setErrors(newErrors);
        return isValid;
    };

    return (
        <ThemeProvider theme={defaultTheme}>
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
                    <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }} />
                    <Typography component="h1" variant="h5">
                        Sign up
                    </Typography>
                    <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    id="username"
                                    label="Username"
                                    name="username"
                                    autoComplete="username"
                                    value={input.username || ''}
                                    onChange={handleChange}
                                    error={!!errors.username}
                                    helperText={errors.username}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="password"
                                    label="Password"
                                    type="password"
                                    id="password"
                                    autoComplete="new-password"
                                    value={input.password || ''}
                                    onChange={handleChange}
                                    error={!!errors.password}
                                    helperText={errors.password}
                                />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField
                                    required
                                    fullWidth
                                    name="confirm_password"
                                    label="Confirm password"
                                    type="password"
                                    id="confirm_password"
                                    autoComplete="new-password"
                                    value={input.confirm_password || ''}
                                    onChange={handleChange}
                                    error={!!errors.confirm_password}
                                    helperText={errors.confirm_password}
                                />
                            </Grid>
                        </Grid>
                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Sign Up
                        </Button>
                        <Grid item xs={12} >
                            <Grid item>
                                <Link component={RouterLink} to="/login" variant="body2">
                                    Already have an account? Sign in
                                </Link>
                            </Grid>
                        </Grid>
                    </Box>
                </Box>
            </Container>
        </ThemeProvider>
    );
}
export default RegistrationForm;

