import axios from 'axios'

const SPORT_MATCH_API_BASE_URL = 'http://localhost:8080/api/sportMatches'

class AdminService {
    // match creation
    addSportMatch (sportMatch) {
        return axios.post(SPORT_MATCH_API_BASE_URL, sportMatch)
    }

    // change the result of the match
    changeMatchResult (id, match) {
        return axios.put(SPORT_MATCH_API_BASE_URL + `/${id}`, match)
    }
}

export default new AdminService()