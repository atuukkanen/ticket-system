import React, { Component } from 'react';
import InfoTable from "../components/InfoTable";
import '../styles/User.css';

class UserList extends Component {
    render() {
        var users = [{
            id: 1,
            username: "aikain",
            name: "Ville Nupponen"
        },{
            id: 2,
            username: "teppo",
            name: "Seppo Taalasmaa"
        },{
            id: 3,
            username: "seppo",
            name: "Teppo Taalasmaa"
        },{
            id: 4,
            username: "pilvi",
            name: "Pilvi Yksisarvinen"
        },{
            id: 5,
            username: "artzi",
            name: "Aaro Tuukkanen"
        }];
        return (
            <div className="userList">
                <InfoTable
                    topic="Käyttäjät"
                    rowClickPath="/users/"
                    history={this.props.history}
                    data={users}
                    fields={[
                        { name: "id", showName: "ID" },
                        { name: "name", showName: "Nimi" },
                        { name: "username", showName: "Käyttäjänimi" }
                    ]}
                />
            </div>
        );
    }
}

export default UserList;
