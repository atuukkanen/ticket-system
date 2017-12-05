import React, { Component } from 'react';
import { Route } from 'react-router-dom'

import NavItem from '../components/NavItem'

import DefaultSettings from '../components/DefaultSettings'
import LdapSettings from '../components/LdapSettings'
import MailSettings from '../components/MailSettings'

import '../styles/Settings.css';

class Settings extends Component {
    render() {
        return (
            <div className="settings">
                <ul className="nav nav-tabs">
                    <NavItem to="/settings" text="Yleiset"/>
                    <NavItem to="/settings/ldap" text="Ldap"/>
                    <NavItem to="/settings/mail" text="Sähköposti"/>
                </ul>

                <Route path="/settings" exact component={DefaultSettings} />
                <Route path="/settings/ldap" component={LdapSettings} />
                <Route path="/settings/mail" component={MailSettings} />
            </div>
        );
    }
}

export default Settings;
