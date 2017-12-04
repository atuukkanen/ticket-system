import React, { Component } from 'react';
import { Route } from 'react-router-dom'

import Dashboard from '../containers/Dashboard';
import TicketView from '../containers/TicketView';
import UserList from '../containers/UserList';
import Settings from '../containers/Settings';

import '../styles/FrontPage.css';

class FrontPage extends Component {
    render() {
        return (
            <div className="frontPage">
                <Route path="/" exact component={Dashboard} />
                <Route path="/tickets" component={TicketView} />
                <Route path="/users" component={UserList} />
                <Route path="/settings" component={Settings} />
            </div>
        );
    }
}

export default FrontPage;
