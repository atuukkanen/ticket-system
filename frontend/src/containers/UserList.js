import React, { Component } from 'react';
import '../styles/UserList.css';

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
                <div className="panel panel-default">
                    <div className="panel-heading">
                        <h4>Käyttäjät</h4>
                    </div>
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Käyttäjätunnus</th>
                                <th>Nimi</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map(function (user) {
                                return <tr key={user.id}>
                                    <td>{user.id}</td>
                                    <td>{user.username}</td>
                                    <td>{user.name}</td>
                                </tr>
                            })}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default UserList;
