import React from 'react'

export default function Footer() {
    // this year
    const yearNow = new Date().getFullYear()

    return (
        <footer className="pt-4 my-md-3 pt-md-4 border-top">
            <div className="col-12 col-md p-0">
                <small className="d-block mb-3 text-muted">Все права защищены, не сомневайтесь!</small>
                <small className="d-block mb-3 text-muted">© 2020 - {yearNow}</small>
            </div>
            <div className="col-12 col-md p-0">
                <a href="https://vk.com/unknownwhoit" target="_blank" rel="noreferrer">
                    <small className="d-block mb-3 text-muted">Тех. поддержка</small>
                </a>
            </div>
        </footer>
    )
}