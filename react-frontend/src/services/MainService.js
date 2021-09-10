import axios from 'axios'

const MAIN_REST_API_URL = 'http://localhost:8080/api'

class MainService {
    // getting a list of matches
    getMatches(){
        return axios.get(MAIN_REST_API_URL + '/main')
    }

    // getting a match
    getMatch(id){
        return axios.get(MAIN_REST_API_URL + `/sportMatch/${id}`)
    }
}

export default new MainService()