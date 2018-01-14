import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

import '../styles/NavItem.css'

class NavItem extends Component {

    render() {
        return (
            <li className="nav-item">
                <NavLink className="nav-link" exact to={this.props.to}>{this.props.text}</NavLink>
            </li>
        )
    }
}

export default NavItem;