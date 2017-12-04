import React, { Component } from 'react';
import SidebarLink from './SidebarLink'
import '../styles/Sidebar.css';

class Sidebar extends Component {
    render() {
        return (
            <div className="nav-side-menu">
                <div className="brand">
                    Ticket System
                </div>

                <div className="menu-list">
                    <ul id="menu-content" className="menu-content">
                        <SidebarLink name="dashboard" text="Työpöytä" link="/" />
                        <SidebarLink name="ticket" text="Tiketit" link="/tickets" />
                        <SidebarLink name="users" text="Käyttäjät" link="/users" />
                        <SidebarLink name="cog" text="Asetukset" link="/settings" />
                    </ul>
                </div>
            </div>
        );
    }
}

export default Sidebar;
