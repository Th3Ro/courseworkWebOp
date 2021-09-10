import React from 'react'
import '../static/css/modalWindow.css'
import LoginService from '../services/LoginService';
import "../static/css/registration.css"

class AuthorizeModalWindow extends React.Component {
    constructor(props) {
        super(props)
        // the state in which all the necessary data is stored
        this.state = {
            login: '',
            password: '',
            authResult: false,
            message: ''
        }
        // Functions that change state
        this.changeLoginHandler = this.changeLoginHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.authorizeUser = this.authorizeUser.bind(this);
    }

    // login change function
    changeLoginHandler = (event) => {
        this.setState({login: event.target.value});
    }

    // password change function
    changePasswordHandler = (event) => {
        this.setState({password: event.target.value});
    }

    // user authorization function
    authorizeUser = (e) => {
        e.preventDefault();
        if (this.state.login !== '' && this.state.password !== ''){
            LoginService.loginUser(this.state.login, this.state.password).then((response) => {
                const jsonObj =  JSON.stringify(response.data);
                const data = JSON.parse(jsonObj);
                if(data === 'Успешная авторизация'){
                    this.setState({authResult: true})
                    this.props.closePopup()
                    setTimeout(window.location.reload(), 1000);
                }
                else{
                    this.setState({message: data})
                }
                console.log(data)
            })
                .catch((error) => {
                    console.log('Произошла ошибка: ' + error);
                });
        } else {
            this.setState({message:'Заполните все поля!'})
        }
    }

    render() {
        return (
            <div className="popup-box">
                <div className="popup-window">
                    <div className="popup-header">
                        <h1 className="popup-h">Авторизация</h1>
                        <span className="popup-close" onClick={this.props.closePopup}>&times;</span>
                    </div>
                    <div className="form-group">
                        <label htmlFor="inputLogin">Логин</label>
                        <input type="text" className="form-control" id="inputLogin" name="login" placeholder="Логин" required
                               onChange={this.changeLoginHandler}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="inputPassword">Пароль</label>
                        <input type="password" className="form-control" id="inputPassword" name="password" placeholder="Пароль" required
                               onChange={this.changePasswordHandler}/>
                    </div>
                    {!this.state.authResult &&
                    (<div>
                        <p className="hidden-text">{this.state.message}</p>
                    </div>)
                    }
                    <button type="submit" className="btn btn-primary in-btn" onClick={this.authorizeUser}>Войти</button>
                </div>
            </div>
        );
    }
}

export default AuthorizeModalWindow;