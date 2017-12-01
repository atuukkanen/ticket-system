import React, { Component } from 'react';
import FontAwesome from 'react-fontawesome';

class SidebarLink extends Component {
    render() {
        return (
            <li>
                <a href={this.props.link}>
                    <FontAwesome name={this.props.name} className="fa-lg" /> {this.props.text}
                </a>
            </li>
        );
    }
}

export default SidebarLink;
