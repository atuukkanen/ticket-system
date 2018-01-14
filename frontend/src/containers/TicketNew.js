import React, { Component } from 'react';
import Marked from 'marked';
import Markmirror from 'react-markmirror';
import '../styles/Ticket.css';

import FormRow from '../components/FormRow'
import FormSubmitRow from "../components/FormSubmitRow";

class TicketNew extends Component {
    constructor(props) {
        super(props);
        this.state = {
            header: '',
            description: ''
        };
    }
    componentDidMount() {
        document.querySelectorAll("button[title=Preview]")[0].setAttribute("type", "button");
    }
    handleInputChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;
        this.setState({ [name]: value });
    };
    handleEditorChange = (text) => {
        this.setState({ description: text});
    };
    handleSubmit = (event) => {
        fetch('/api/ticket', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(this.state),
        })
        .then((response) => response.json())
        .then(dataJSON => {
            this.props.history.push("/tickets/" + dataJSON.id);
        });
        event.preventDefault();
    };
    render() {
        return (
            <div className="ticketView">
                <form className="form-horizontal" onSubmit={this.handleSubmit}>
                    <FormRow text="Otsikko" name="header" value={this.state.header} onChange={this.handleInputChange} required={true} />
                    <FormRow text="Selitys" template={
                        <div className="commentEditor">
                            <Markmirror
                                value={this.state.description}
                                onChange={this.handleEditorChange}
                                showSearch={false}
                                onPreview={value => (Marked(value))}
                            />
                        </div>
                    }/>
                    <FormSubmitRow />
                </form>
            </div>
        );
    }
}

export default TicketNew;
