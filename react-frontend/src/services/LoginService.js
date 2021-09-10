import axios from 'axios'

const LOGIN_API_BASE_URL = 'http://localhost:8080/api/login'
const LOGOUT_API_BASE_URL = 'http://localhost:8080/api/logout'
const USERISAUTH_API_BASE_URL = 'http://localhost:8080/api/authUser'

class LoginService {
    // user authorization
    loginUser(login, password){
        return axios.post(LOGIN_API_BASE_URL + '/' + login, password)
    }
    // user logout
    logoutUser(){
        return axios.get(LOGOUT_API_BASE_URL)
    }
    // getting user authorization
    getUser(){
        return axios.get(USERISAUTH_API_BASE_URL)
    }
}

export default new LoginService()