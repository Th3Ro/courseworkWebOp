import React from 'react'
import '../static/css/modalWindow.css'
import BetService from '../services/BetService'
import MainService from '../services/MainService'

class DoBetPage extends React.Component {
    constructor(props) {
        super(props)
        // the state in which all the necessary data is stored
        this.state = {
            id: this.props.match.params.id,
            money: 0,
            match: [],
            teamNumber: 0,
            canDoBet: true,
            message: ''
        }
        // functions that change state
        this.changeMoneyHandler = this.changeMoneyHandler.bind(this);
        this.changeTeamNumberHandler = this.changeTeamNumberHandler.bind(this);
        this.doBet = this.doBet.bind(this);
    }

    componentDidMount(){
        MainService.getMatch(this.state.id).then( res => {
            this.setState({match: res.data});
        })
    }

    // function for changing the amount of the bet
    changeMoneyHandler = (event) => {
        this.setState({money: event.target.value});
    }

    // command number change function
    changeTeamNumberHandler = (event) => {
        this.setState({teamNumber: event.target.value});
    }

    // bet creation function
    doBet = (e) => {
        e.preventDefault();
        if (this.state.money < 10){
            this.setState({
                canDoBet: false,
                message: 'Минимальная сумма ставки - 10!'
            })
        } else if (this.state.money > this.props.userScore){
            this.setState({
                canDoBet: false,
                message: 'Вы не можете ставить больше, чем у Вас на счету!'
            })
        } else if (this.state.teamNumber === 0){
            this.setState({
                canDoBet: false,
                message: 'Вы не выбрали команду!'
            })
        } else {
            const bet = {
                matchId: this.state.match.id,
                userId: this.props.userId,
                teamNumber: this.state.teamNumber,
                money: this.state.money,
                winResult: 0
            }
            this.setState({canDoBet: true})
            BetService.doBet(bet)
            this.props.history.push('/')
        }
    }

    render() {
        return (
            <div className="container">
                <h1 className="mb-3">Может проще сразу нам деньги отдать?</h1>
                <div className="row row-cols-1 row-cols-md-1 mb-3 text-center">
                    <div className="col">
                        <div className="card mb-4 shadow-sm">
                            <div className="card-header">
                                <h4 className="my-0 fw-normal">{this.state.match.name}</h4>
                            </div>
                            <div className="card-body">
                                <h1 className="card-title pricing-card-title">Дата {this.state.match.dateOfMatch}</h1>
                                <ul className="list-unstyled mt-3 mb-4">
                                    <li>Коэффициент на победу первой: <p>{this.state.match.firstCoefficient}</p></li>
                                    <li>Коэффициент на победу второй: <p>{this.state.match.secondCoefficient}</p></li>
                                </ul>
                                <div className="d-flex flex-row align-items-end">
                                    <div className="d-flex flex-column m-1">
                                        <label htmlFor="money">Сумма</label>
                                        <input type="number" min="10" max={this.props.userScore} placeholder="0" required
                                               name="money" onChange={this.changeMoneyHandler}
                                        />
                                    </div>
                                    <div className="d-flex flex-column m-1">
                                        <label htmlFor="team">Команда</label>
                                        <input type="number" min="1" max="2" placeholder="1" required name="team" onChange={this.changeTeamNumberHandler}/>
                                    </div>
                                    <button className="btn btn-sm btn-outline-secondary m-1" onClick={this.doBet}>Поставить</button>
                                </div>
                                {!this.state.canDoBet &&
                                (<div>
                                    <p className="hidden-text">{this.state.message}</p>
                                </div>)
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default DoBetPage;