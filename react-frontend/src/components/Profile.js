import React, { Component } from 'react'
import "../static/css/profile.css"
import LoginService from "../services/LoginService"
import ProfileService from "../services/ProfileService"
import UserService from "../services/UserService"

export default class Profile extends Component {
    constructor(){
        super()
        this.state = {
            user: [],
            bets: [],
            formData: '',
            newDateOfBirth: new Date('12-09-2020'),
            dateOfBirthValid: true,
            canGetGift: true
        }

        // functions that change state
        this.changeSurnameHandler = this.changeSurnameHandler.bind(this);
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeDateOfBirthHandler = this.changeDateOfBirthHandler.bind(this);
    }

    componentDidMount(){
        LoginService.getUser().then((res) =>{
            let resultUser = res.data;
            this.setState({
                user: resultUser
            });
        });
        ProfileService.getBets().then((response) => {
            this.setState({
                bets: response.data
            });
        });
    }

    // picture change function
    handleChangeFile = (event) => {
        const file = event.target.files[0];
        ProfileService.updateUserPhoto(file)
        setTimeout(window.location.reload(), 1000);
    }

    // surname change function
    changeSurnameHandler = (event) => {
        const newUser = this.state.user
        newUser.surname = event.target.value
        this.setState({user: newUser})
    }

    // name change function
    changeNameHandler = (event) => {
        const newUser = this.state.user
        newUser.name = event.target.value
        this.setState({user: newUser})
    }

    // function of changing the date of birth
    changeDateOfBirthHandler = (event) => {
        this.setState({newDateOfBirth: event.target.value});
    }

    // function of saving changes in the database
    saveChanges = () => {
        // function to check the date of birth for validity
        const checkDateOfBirth = () => {
            const dateNow = new Date();
            const deteBirth = new Date(this.state.newDateOfBirth)
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

        if (checkDateOfBirth() && String(this.state.user.name).length > 0 && String(this.state.user.surname).length > 0) {
            const newUser = this.state.user
            newUser.dateOfBirth = this.state.newDateOfBirth
            UserService.updateUser(this.state.user.id, newUser)
            setTimeout(window.location.reload(), 1000);
        }
    }

    // function of receiving a bonus to the account
    getGift = () => {
        if (this.props.user.moneyScore < 20){
            ProfileService.getGift()
            setTimeout(window.location.reload(), 1000);
        }
        else{
            this.setState({canGetGift: false})
        }
    }

    // getting the correct display of the match result
    getResultOfMatch({result}){
        if (result === 1){
            return <li>Результат: <p>Победа</p></li>
        } else if (result === 2) {
            return <li>Результат: <p>Проигрыш</p></li>
        } else {
            return <li>Результат: <p>Ожидание конца</p></li>
        }
    }

    deleteUser = () => {
        UserService.deleteUser(this.props.user.id)
        this.props.changeAuth()
        this.props.history.push('/');
    }

    render() {
        // style variables for error text
        let surnameColor = String(this.state.user.surname).length > 0 ? '' : 'red';
        let nameColor = String(this.state.user.name).length > 0 ? '' : 'red';
        let dateOfBirthColor = this.state.dateOfBirthValid ? '' : 'red';

        return (
            <div className="container">
                <div className="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                    <h1 className="display-4">Профиль</h1>
                </div>
                <div className="row g-3 mb-3">
                    <div className="col-sm-6">
                        <div className="profile-info">
                            <div className="col photo-col">
                                <div className="card mb-4 shadow-sm">
                                    <div className="card-header">
                                        <h4 className="my-0 fw-normal">Ваша фотография</h4>
                                    </div>
                                    <div className="card-body">
                                        <img src={"images/" + this.props.user.image} alt="User face" className="photo" />
                                        <div className="form-group">
                                            <label htmlFor="inputFile"className="mb-1">Вы можете изменить свою фотографию
                                                (лучше все равно уже не будет)</label>
                                            <input type="file" className="form-control-file" id="inputFile" name="photo"
                                                   onChange={this.handleChangeFile}/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="col-sm-6">
                        <h3 className="display-4">Вы можете изменить свои данные</h3>
                        <div className="form-group">
                            <label>Текущая Фамилия: {this.props.user.surname}</label>
                            <input type="text" className="form-control" id="inputSurname" name="surname"
                                   placeholder="Новая Фамилия" required onChange={this.changeSurnameHandler} style={{borderColor:surnameColor}}/>
                            {   !(String(this.props.user.surname).length > 0) &&
                            <div className="hidden-text">
                                Введите действительную фамилию.
                            </div>
                            }
                        </div>
                        <div className="form-group">
                            <label>Текущее Имя: {this.props.user.name}</label>
                            <input type="text" className="form-control" id="inputName" name="name"
                                   placeholder="Новое Имя" required onChange={this.changeNameHandler} style={{borderColor:nameColor}}/>
                            {   !(String(this.props.user.name).length > 0) &&
                            <div className="hidden-text">
                                Введите действительное имя.
                            </div>
                            }
                        </div>
                        <div className="form-group">
                            <label>Текущая Дата рождения {this.props.user.dateOfBirth}</label>
                            <input type="date" className="form-control" id="dateOfBirth" name="date" placeholder="Дата" required
                                   onChange={this.changeDateOfBirthHandler} style={{borderColor:dateOfBirthColor}}/>
                            {   (!this.state.dateOfBirthValid) &&
                            <div className="hidden-text">
                                Введите верную дату рождения (вам должно быть больше 18 лет)
                            </div>
                            }
                        </div>
                        <button className="btn btn-primary in-btn m-1" onClick={this.saveChanges}>Сохранить изменения</button>
                        <button className="btn btn-primary in-btn m-1" onClick={this.getGift}>Получить бонус (300)</button>
                        <button className="btn btn-primary in-btn m-1" onClick={this.deleteUser}>Удалить аккаунт</button>
                        {   (!this.state.canGetGift) &&
                        <div className="hidden-text">
                            На вашем счету и так достаточно денег, не наглейте!
                        </div>
                        }
                    </div>
                </div>

                <div className="bets">
                    <h2 className="display-4">Ваши ставки</h2>
                    <div className="row row-cols-1 row-cols-md-2 mb-3 text-center">
                        {
                            this.state.bets.map(
                                bet =>
                                    <div key={bet.id} className="col">
                                        <div className="card mb-4 shadow-sm">
                                            <div className="card-header">
                                                <h4 className="my-0 fw-normal">{bet.matchName}</h4>
                                            </div>
                                            <div className="card-body">
                                                <h1 className="card-title pricing-card-title">Дата {bet.date}</h1>
                                                <ul className="list-unstyled mt-3 mb-4">
                                                    <li>Команда: <p>{bet.team}</p></li>
                                                    <li>Коэффициент на победу: <p>{bet.coef}</p></li>
                                                    <li>Сумма ставки: <p>{bet.money}</p></li>
                                                    <this.getResultOfMatch result={bet.result}/>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                            )
                        }
                    </div>
                </div>
            </div>
        )
    }
}
