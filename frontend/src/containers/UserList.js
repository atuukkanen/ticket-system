import React, { Component } from 'react';
import InfoTable from "../components/InfoTable";
import '../styles/User.css';

class UserList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: []
        }
    }
    componentWillMount() {
        fetch("/data/users.json").then(result => {
            return result.json();
        }).then(data => {
            this.setState({users: data});
        });
    }
    render() {
        return (
            <div className="userList">
                <InfoTable
                    topic="Käyttäjät"
                    rowClickPath="/users/"
                    history={this.props.history}
                    data={this.state.users}
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
