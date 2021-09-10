import React, { Component } from 'react'
import MainService from '../services/MainService'

export default class MainPage extends Component {
    state = {
        matches:[],
        isLogged: false,
        showBetWindow: false
    };

    componentDidMount(){
        MainService.getMatches().then((response) => {
            this.setState({
                matches: response.data,
            })
        });
    }

    // function of opening, closing the bet window
    togglePopup() {
        this.setState({
            showBetWindow: !this.state.showBetWindow
        })
    }

    // function to get html code for each bet
    getCurrentHtml = ({id}) => {
        if (!this.props.isLogin){
            return <p>Что бы поставить, Вам надо войти.</p>
        } else {
            return  (
                <a className="btn btn-sm btn-outline-secondary" href={`/doBet/${id}`}>Поставить</a>
            )
        }
    }

    render() {
        return (
            <main className="container">
                <div className="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                    <h1 className="display-4">Выигрышей.нет (и не будет)</h1>
                    <p className="lead">Самые высокие коэффициенты (если брать параллельную вселенную),
                        лучшая контора, только у нас вы можете обогатиться
                        (трехэтажными матами, которые добавятся в Ваш словарный, если решитесь ставить у нас).</p>
                </div>
                <div className="row row-cols-1 row-cols-md-3 mb-3 text-center">
                    {
                        this.state.matches.filter(match => match.isEnd !== true).map(
                            match =>
                                <div className="col" key={match.id}>
                                    <div className="card mb-4 shadow-sm">
                                        <div className="card-header">
                                            <h4 className="my-0 fw-normal">{match.name}</h4>
                                        </div>
                                        <div className="card-body">
                                            <h1 className="card-title pricing-card-title">Дата {match.dateOfMatch}</h1>
                                            <ul className="list-unstyled mt-3 mb-4">
                                                <li>Коэффициент на победу первой: <p>{match.firstCoefficient}</p></li>
                                                <li>Коэффициент на победу второй: <p>{match.secondCoefficient}</p></li>
                                            </ul>
                                            <this.getCurrentHtml id={match.id}/>
                                        </div>
                                    </div>
                                </div>
                        )
                    }
                </div>
            </main>
        )
    }
}