import React, { Component } from 'react';
import FormRow from '../components/FormRow'
import FormSubmitRow from '../components/FormSubmitRow'
import '../styles/User.css';

class UserView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: {
                id: 4,
                username: "pilvi",
                name: "Pilvi Yksisarvinen"
            }
        };
        this.handleChange = this.handleChange.bind(this);
    }
    handleChange(event) {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;
        const user = this.state.user;
        user[name] = value;
        this.setState({
            user: user
        });
    }
    render() {
        return (
            <div className="userView">
                <div className="col-md-12 personal-info">
                    <h3>{ this.state.user.name }</h3>
                    <form className="form-horizontal">
                        <FormRow text="Nimi" name={"name"} value={ this.state.user.name } onChange={this.handleChange} />
                        <FormRow text="Käyttäjänimi" name="username" value={ this.state.user.username } onChange={this.handleChange} />
                        <FormSubmitRow />
                    </form>
                </div>
            </div>
        );
    }
}

export default UserView;
