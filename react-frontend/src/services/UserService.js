import axios from 'axios'

const USER_API_BASE_URL = 'http://localhost:8080/api/users'

class UserService {
    // new user registration
    registerUser(registeringUser){
        return axios.post(USER_API_BASE_URL, registeringUser)
    }

    // updating user data
    updateUser(userId, user){
        return axios.put(USER_API_BASE_URL + '/' + userId, user)
    }

    // deleting a user
    deleteUser(userId){
        return axios.delete(USER_API_BASE_URL + '/' + userId)
    }
}

export default new UserService()