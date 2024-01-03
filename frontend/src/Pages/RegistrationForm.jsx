import React, { useState } from 'react';
import axios from 'axios';

function RegistrationForm() {
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
        }

        if (typeof input.username !== 'undefined' && input.username != null) {
            const re = /^\S*$/;
            if (input.username.length < 6 || !re.test(input.username)) {
                isValid = false;
                newErrors.username = 'Please enter a valid username';
            }
        }

        if (!input.password) {
            isValid = false;
            newErrors.password = 'Please enter your password';
        }

        if (typeof input.password !== 'undefined') {
            const patternPassword = new RegExp(
                '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})'
            );
            if (!patternPassword.test(input.password)) {
                isValid = false;
                newErrors.password =
                    'The password should contain at least one lowercase, one uppercase, one digit, and one special character. The password should be at least 8 characters long.';
            }
        }

        if (
            typeof input.password !== 'undefined' &&
            typeof input.confirm_password !== 'undefined'
        ) {
            if (input.password !== input.confirm_password) {
                isValid = false;
                newErrors.confirm_password = "Passwords don't match";
            }
        }

        setErrors(newErrors);
        return isValid;
    };

    return (
        <div className='container'>
            <h1 className='text-center mb-4'>REGISTRATION FORM</h1>
            <form onSubmit={handleSubmit}>
                <div className='form-group'>
                    <label htmlFor='username' className='form-label fs-5 fw-bold'>
                        USERNAME:
                    </label>
                    <input
                        type='text'
                        name='username'
                        value={input.username}
                        onChange={handleChange}
                        className='form-control fs-5'
                        placeholder='Enter username'
                        id='username'
                    />
                    <div className='text-danger form-text fw-bold fs-5'>{errors.username}</div>
                </div>

                <div className='form-group'>
                    <label htmlFor='password' className='form-label fs-5 fw-bold'>
                        PASSWORD:
                    </label>
                    <input
                        type='password'
                        name='password'
                        value={input.password}
                        onChange={handleChange}
                        className='form-control fs-5'
                        placeholder='Enter password'
                        id='password'
                    />
                    <div className='text-danger form-text fw-bold fs-5'>{errors.password}</div>
                </div>

                <div className='form-group'>
                    <label htmlFor='confirm-password' className='form-label fs-5 fw-bold'>
                        CONFIRM PASSWORD:
                    </label>
                    <input
                        type='password'
                        name='confirm_password'
                        id='confirm-password'
                        value={input.confirm_password}
                        onChange={handleChange}
                        className='form-control fs-5'
                        placeholder='Enter confirm password'
                    />
                    <div className='text-danger form-text mb-5 fw-bold fs-5'>
                        {errors.confirm_password}
                    </div>
                </div>

                <input
                    type='submit'
                    value='SUBMIT'
                    className='btn btn-success d-flex justify-content-center mx-auto mb-3 px-3'
                />
            </form>
        </div>
    );
}

export default RegistrationForm;

