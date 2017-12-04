import React, { Component } from 'react';
import { BrowserRouter } from 'react-router-dom'
import Sidebar from '../components/Sidebar'
import FrontPage from '../containers/FrontPage'
import '../styles/App.css';

class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <div className="app">
                    <Sidebar />
                    <FrontPage />
                </div>
            </BrowserRouter>
        );
    }
}

export default App;
