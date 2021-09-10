import React, { Component } from 'react'
import AuthorizeModalWindow from './AuthorizeModalWindow';
import LoginService from '../services/LoginService'
import { withRouter } from 'react-router-dom';

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showAuthorizeModal: false
        };
    }

    // function of opening, closing the authorization window
    togglePopup() {
        this.setState({
            showAuthorizeModal: !this.state.showAuthorizeModal
        })
    }

    // user logout function
    logoutUser = () => {
        this.props.onClick()
        LoginService.logoutUser().then(res => {
            this.props.history.push('/');
        })
    }

    // header for guest (not logged in user)
    GuestHeader = () => {
        return(
            <div className="d-flex flex-row align-items-center">
                <nav className="my-2 my-md-0 me-md-3">
                    <button className="btn btn-outline-primary" onClick={this.togglePopup.bind(this)}>Войти</button>
                </nav>
                <nav className="my-2 my-md-0 me-md-3">
                    <a className="btn btn-primary" href="/registration">Зарегистрироваться</a>
                </nav>
            </div>
        )
    }

    // admin header (determined by id = 1)
    AdminHeader = () => {
        return(
            <div className="d-flex flex-row align-items-center">
                <nav className="my-2 my-md-0 me-md-3">
                    <p className="p-2 text-dark score m-0">{this.props.userScore.toFixed(2)} руб.</p>
                </nav>
                <nav className="my-2 my-md-0 me-md-3">
                    <a className="p-2 text-dark" href={"/view-user/" + this.props.userId}>Профиль</a>
                </nav>
                <nav className="my-2 my-md-0 me-md-3">
                    <a className="p-2 text-dark" href="/admin">Администрирование</a>
                </nav>
                <nav className="my-2 my-md-0 me-md-3">
                    <button className="btn btn-outline-primary" onClick={this.logoutUser}>Выйти</button>
                </nav>
            </div>
        )
    }

    // regular user header
    UserHeader = () => {
        return(
            <div className="d-flex flex-row align-items-center">
                <nav className="my-2 my-md-0 me-md-3">
                    <p className="p-2 text-dark score m-0">{this.props.userScore.toFixed(2)} руб.</p>
                </nav>
                <nav className="my-2 my-md-0 me-md-3">
                    <a className="p-2 text-dark" href={"/view-user/" + this.props.userId}>Профиль</a>
                </nav>
                <nav className="my-2 my-md-0 me-md-3">
                    <button className="btn btn-outline-primary" onClick={this.logoutUser}>Выйти</button>
                </nav>
            </div>
        )
    }

    // function of getting a header for the current user
    GetCurrentHeader = () => {
        if (this.props.userIsAuth && this.props.userId === 1){
            return <this.AdminHeader />
        } else if (this.props.userIsAuth) {
            return <this.UserHeader />
        } else {
            return <this.GuestHeader/>
        }
    }

    render() {
        return (
            <header className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
                <p className="h5 my-0 me-md-auto fw-normal"><a href="/" className="nav-link">ВЫИГРЫШЕЙ.NET</a></p>
                <this.GetCurrentHeader />
                {this.state.showAuthorizeModal ?
                    <AuthorizeModalWindow
                        closePopup={this.togglePopup.bind(this)}
                    />
                    : null
                }
            </header>
        )
    }
}

export default withRouter(Header);
