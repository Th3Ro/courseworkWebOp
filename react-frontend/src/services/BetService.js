import axios from 'axios'

const LOGIN_API_BASE_URL = 'http://localhost:8080/api/newBet'

class BetService {
    // bet creation
    doBet(bet){
        return axios.post(LOGIN_API_BASE_URL, bet)
    }
}

export default new BetService()