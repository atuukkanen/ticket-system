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
                        <SidebarLink name="dashboard" text="Työpöytä" />
                        <SidebarLink name="ticket" text="Tiketit" />
                        <SidebarLink name="users" text="Käyttäjät" />
                        <SidebarLink name="cog" text="Asetukset" />
                    </ul>
                </div>
            </div>
        );
    }
}

export default Sidebar;
