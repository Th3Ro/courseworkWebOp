import React, { Component } from 'react'
import UserService from '../services/UserService';
import '../static/css/registration.css'


export default class Registration extends Component {
    constructor(props) {
        super(props)

        // the state in which all the necessary data is stored
        this.state = {
            // data for adding user authorization data to the database
            login: '',
            password: '',
            // data to add to the user entity in the database
            surname: '',
            name: '',
            dateOfBirth: new Date('12-09-2020'),
            // indicators of correctness of entered data
            surnameValid: true,
            nameValid: true,
            dateOfBirthValid: true,
            loginValid: true,
            passwordValid: true,

        }
        // functions that change state
        this.changeLoginHandler = this.changeLoginHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.changeSurnameHandler = this.changeSurnameHandler.bind(this);
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeDateOfBirthHandler = this.changeDateOfBirthHandler.bind(this);
        this.registerUser = this.registerUser.bind(this);
    }

    // login change function
    changeLoginHandler = (event) => {
        this.setState({login: event.target.value});
    }

    // password change function
    changePasswordHandler = (event) => {
        this.setState({password: event.target.value});
    }

    // surname change function
    changeSurnameHandler = (event) => {
        this.setState({surname: event.target.value});
    }

    // name change function
    changeNameHandler = (event) => {
        this.setState({name: event.target.value});
    }

    // function of changing the date of birth
    changeDateOfBirthHandler = (event) => {
        this.setState({dateOfBirth: event.target.value});
    }

    // user registration function
    registerUser = (e) => {
        e.preventDefault();

        // functions for checking the correctness of the entered data
        const checkSurname = () => {
            this.setState({surnameValid: this.state.surname.length > 0})
            return this.state.surname.length > 0
        }

        const checkName = () => {
            this.setState({nameValid: this.state.name.length > 0})
            return this.state.name.length > 0
        }

        const checkDateOfBirth = () => {
            const dateNow = new Date();
            const deteBirth = new Date(this.state.dateOfBirth)
            let daysLag = parseInt(Math.abs(dateNow.getTime() - deteBirth.getTime()) / (1000 * 3600 * 24))
            if (daysLag / 365 >= 18){
                this.setState({dateOfBirthValid: true})
                return true
            }
            else {
                this.setState({dateOfBirthValid: false})
                return false
            }

        }

        const checkLogin = () => {
            if(!(/[A-Za-z]/.test(this.state.login) && /[0-9]/.test(this.state.login)) || this.state.login.length < 8){
                this.setState({loginValid: false})
                return false;
            } else {
                this.setState({loginValid: true})
                return true;
            }
        }

        const checkPassword = () => {
            if(!(/[A-Za-z]/.test(this.state.password) && /[0-9]/.test(this.state.password)) || this.state.password.length < 8){
                this.setState({passwordValid: false})
                return false;
            } else {
                this.setState({passwordValid: true})
                return true;
            }
        }

        // calling functions to change state
        checkLogin()
        checkPassword()
        checkSurname()
        checkName()
        checkDateOfBirth()

        // checking all data
        if (checkLogin() && checkPassword() && checkSurname() && checkName() && checkDateOfBirth()) {
            let registeringUser = {
                surname: this.state.surname,
                name: this.state.name,
                dateOfBirth: this.state.dateOfBirth,
                userLogin: this.state.login,
                userPassword: this.state.password
            };

            // user registration (adding data to the table of users and their authorization data)
            UserService.registerUser(registeringUser).then(res => {
                this.props.history.push('/');
            });
        }
    }

    render() {
        // style Variables for Error Text
        let loginColor = this.state.loginValid ? '' : 'red';
        let passwordColor = this.state.passwordValid ? '' : 'red';
        let surnameColor = this.state.surnameValid ? '' : 'red';
        let nameColor = this.state.nameValid ? '' : 'red';
        let dateOfBirthColor = this.state.dateOfBirthValid ? '' : 'red';

        return (
            <div className="container">
                <div className="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                    <h1 className="display-4">Регистрация</h1>
                </div>
                <form className="auth">
                    <div className="row g-3 mb-3">
                        <div className="col-sm-6">
                            <label htmlFor="inputLogin">Логин</label>
                            <input type="text" className="form-control" id="inputLogin" name="login"
                                   placeholder="Логин" required onChange={this.changeLoginHandler} style={{borderColor:loginColor}}/>
                            {   (!this.state.loginValid) &&
                            <div className="hidden-text">
                                Логин должен быть не менее 7 символов и включать как буквы так и цифры
                            </div>
                            }
                        </div>
                        <div className="col-sm-6">
                            <label htmlFor="inputPassword">Пароль</label>
                            <input type="password" className="form-control" id="inputPassword" name="password"
                                   placeholder="Пароль" required onChange={this.changePasswordHandler} style={{borderColor:passwordColor}}/>
                            {   (!this.state.passwordValid) &&
                            <div className="hidden-text">
                                Пароль должен быть не менее 7 символов и включать как буквы так и цифры
                            </div>
                            }
                        </div>
                    </div>
                    <div className="row g-3 mb-3">
                        <div className="col-sm-6">
                            <label htmlFor="inputSurname">Фамилия</label>
                            <input type="text" className="form-control" id="inputSurname" name="surname"
                                   placeholder="Фамилия" required onChange={this.changeSurnameHandler} style={{borderColor:surnameColor}}/>
                            {   (!this.state.surnameValid) &&
                            <div className="hidden-text">
                                Введите действительную фамилию.
                            </div>
                            }
                        </div>
                        <div className="col-sm-6">
                            <label htmlFor="inputName">Имя</label>
                            <input type="text" className="form-control" id="inputName" name="name"
                                   placeholder="Имя" required onChange={this.changeNameHandler} style={{borderColor:nameColor}}/>
                            {   (!this.state.nameValid) &&
                            <div className="hidden-text">
                                Введите действительное имя.
                            </div>
                            }
                        </div>
                    </div>
                    <div className="d-flex flex-column align-items-center w-100">
                        <div className="form-group w-25 align-middle">
                            <label htmlFor="dateOfBirth">Дата рождения</label>
                            <input type="date" className="form-control" id="dateOfBirth" name="date"
                                   placeholder="Дата" required onChange={this.changeDateOfBirthHandler} style={{borderColor:dateOfBirthColor}}/>
                            {   (!this.state.dateOfBirthValid) &&
                            <div className="hidden-text">
                                Введите верную дату рождения (вам должно быть больше 18 лет)
                            </div>
                            }
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary in-btn" onClick={this.registerUser}>Зарегистрироваться</button>
                </form>
            </div>
        )
    }
}
