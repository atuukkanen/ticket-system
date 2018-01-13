import React, { Component } from 'react';
import { Route } from 'react-router-dom'

import Dashboard from '../containers/Dashboard';
import TicketList from '../containers/TicketList';
import TicketView from '../containers/TicketView';
import UserList from '../containers/UserList';
import UserView from '../containers/UserView';
import Settings from '../containers/Settings';

import '../styles/FrontPage.css';

class FrontPage extends Component {
    render() {
        return (
            <div className="frontPage">
                <Route path="/" exact component={Dashboard} />
                <Route path="/tickets" exact component={TicketList} />
                <Route path="/tickets/:id" exact component={TicketView} />
                <Route path="/users" exact component={UserList} />
                <Route path="/users/:id" exact component={UserView} />
                <Route path="/settings" component={Settings} />
            </div>
        );
    }
}

export default FrontPage;
