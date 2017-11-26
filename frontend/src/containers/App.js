import React, { Component } from 'react';
import Sidebar from '../components/Sidebar'
import FrontPage from '../containers/FrontPage'
import '../styles/App.css';

class App extends Component {
    render() {
        return (
            <div className="app">
                <Sidebar />
                <FrontPage />
            </div>
        );
    }
}

export default App;
