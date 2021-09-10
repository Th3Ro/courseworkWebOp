import React, { Component } from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Header from './components/Header'
import Footer from './components/Footer'
import Registration from './components/Registration'
import Profile from './components/Profile'
import MainPage from './components/MainPage'
import AdminPanel from './components/AdminPanel'
import DoBetPage from './components/DoBetPage'
import LoginService from './services/LoginService'

class App extends Component {
  constructor () {
    super()
    this.state = {
      isAuth: false,
      user: []
    }
  }

  componentDidMount(){
    LoginService.getUser().then((res) => {
      let resultUser = res.data;
      this.setState({
        user: resultUser
      });
      if(resultUser !== ''){
        this.setState({isAuth: true})
      }
    });
  }

  // изменение состояний авторизации пользователя
  onChangeAuth = () => {
    this.setState({
      isAuth: !this.state.isAuth,
      user: []
    });
  }

  render() {
    return (
        <div className="App text-center">
          <Router>
            <Header
                userIsAuth={this.state.isAuth}
                onClick={this.onChangeAuth}
                userScore={this.state.user.moneyScore}
                userId={this.state.user.id}
            />
            <div className="container">
              <Switch>
                <Route
                    render = {props => <MainPage {...props} isLogin={this.state.isAuth} userScore={this.state.user.moneyScore} userId={this.state.user.id}/>}
                    path = '/'
                    exact
                />
                <Route
                    path = '/registration'
                    component = {Registration}
                />
                <Route
                    render = {props => <Profile {...props} user={this.state.user} changeAuth={this.onChangeAuth}/>}
                    path = '/view-user/:id'
                    exact
                />
                <Route
                    render = {props => <DoBetPage {...props} userId={this.state.user.id}/>}
                    path = '/doBet/:id'
                    exact
                />
                <Route path = '/admin' component = {AdminPanel}></Route>
              </Switch>
            </div>
            <Footer />
          </Router>
        </div>
    )
  }
}

export default App;