import axios from 'axios'

const PROFILE_API_BASE_URL = 'http://localhost:8080/api'

class ProfileService {
    // getting a list of the user's bets
    getBets(){
        return axios.get(PROFILE_API_BASE_URL + '/userBets')
    }

    // getting a bonus on the account
    getGift(){
        return axios.put(PROFILE_API_BASE_URL + '/getGift')
    }

    // custom photo update
    updateUserPhoto(newPhoto){
        const formData = new FormData();
        formData.append('image', newPhoto);
        return axios.put(PROFILE_API_BASE_URL + '/changePhoto', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    }
}

export default new ProfileService()