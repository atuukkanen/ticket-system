import React, { Component } from 'react';
import { Link } from 'react-router-dom'
import FontAwesome from 'react-fontawesome';

class SidebarLink extends Component {
    render() {
        return (
            <li>
                <Link to={this.props.link}>
                    <FontAwesome name={this.props.name} className="fa-lg" /> {this.props.text}
                </Link>
            </li>
        );
    }
}

export default SidebarLink;
