import React, { Component } from 'react'
import AdminService from '../services/AdminService'
import MainService from '../services/MainService'

class AdminPanel extends Component {
    constructor(){
        super()
        this.state = {
            matches: [],
            canAdd: true,
            message: ''
        }
    }

    componentDidMount(){
        MainService.getMatches().then((response) => {
            this.setState({
                matches: response.data,
            })
        });
    }

    // function of adding a new match
    addSportMatch = () => {
        const sportMatch = {
            name: document.getElementById('inputName').value,
            dateOfMatch: document.getElementById('dateOfMatch').value,
            firstCoefficient: document.getElementById('coef1').value,
            secondCoefficient: document.getElementById('coef2').value,
        }
        if (sportMatch.name === '' || sportMatch.dateOfMatch === '' ||
            sportMatch.firstCoefficient < 0.01 || sportMatch.secondCoefficient < 0.01) {
            this.setState({canAdd: false})
        }
        else {
            this.setState({canAdd: true})
            AdminService.addSportMatch(sportMatch)
            setTimeout(window.location.reload(), 1000);
        }
    }

    // function of changing the result of the match
    changeMatchResult (id) {
        const team = document.getElementById(`match${id}`).value
        if (team === '') {
            this.setState({message: 'Выбери команду'})
        } else {
            const match = this.state.matches.filter(match => match.id === id)
            match[0].winner = team
            console.log(match[0])
            AdminService.changeMatchResult(id, match[0])
            setTimeout(window.location.reload(), 1000);
        }
    }

    render() {
        return (
            <div className="container">
                <div>
                    <h3 className="display-4 mb-3">Добавление матчей</h3>
                    <div className="row g-3 mb-3">
                        <div className="col-sm-6">
                            <div className="form-group">
                                <label htmlFor="inputName">Название события</label>
                                <input type="text" className="form-control" id="inputName" name="name"
                                       placeholder="Название события" required />
                            </div>
                        </div>
                        <div className="col-sm-6">
                            <div className="form-group">
                                <label htmlFor="dateOfMatch">Дата матча</label>
                                <input type="date" className="form-control" id="dateOfMatch" name="date" placeholder="Дата" required />
                            </div>
                        </div>
                    </div>
                    <div className="row g-3 mb-3">
                        <div className="col-sm-6">
                            <div className="form-group">
                                <label htmlFor="coef1">Коэффициент на первую команду</label>
                                <input type="number" className="form-control" id="coef1" name="coef1"
                                       required step="0.01" min="0" placeholder="0,00" />
                            </div>
                        </div>
                        <div className="col-sm-6">
                            <div className="form-group">
                                <label htmlFor="coef2">Коэффициент на вторую команду</label>
                                <input type="number" className="form-control" id="coef2" name="coef2"
                                       required step="0.01" min="0" placeholder="0,00" />
                            </div>
                        </div>
                    </div>
                    <button className="btn btn-primary in-btn" onClick={this.addSportMatch}>Добавить событие</button>
                    {(!this.state.canAdd) &&
                    <div className="hidden-text mt-2">
                        Заполни все поля!
                    </div>
                    }
                </div>
                <div>
                    <h3 className="display-4 mb-3">Изменение результатов матчей</h3>
                    <p className="hidden-text h4 mb-3">{this.state.message}</p>
                    <div className="row row-cols-1 row-cols-md-3 mb-3 text-center">
                        {
                            this.state.matches.filter(match => match.isEnd !== true).map(
                                match =>
                                    <div key={match.id} className="col">
                                        <div className="card mb-4 shadow-sm">
                                            <div className="card-header">
                                                <h4 className="my-0 fw-normal">{match.name}</h4>
                                            </div>
                                            <div className="card-body">
                                                <h1 className="card-title pricing-card-title">Дата {match.dateOfMatch}</h1>
                                            </div>
                                            <div className="d-flex flex-column m-1 w-25 mx-auto">
                                                <label htmlFor={'match' + match.id}>Команда</label>
                                                <input type="number" min="1" max="2" placeholder="1" required className="mb-2" id={'match' + match.id}/>
                                            </div>
                                            <button className="btn btn-sm btn-outline-secondary mx-auto mb-3 w-75"
                                                    onClick={() => this.changeMatchResult(match.id)}>
                                                Изменить результат
                                            </button>
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

export default AdminPanel