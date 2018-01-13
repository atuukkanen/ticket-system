import React, { Component } from 'react';
import Marked from 'marked';
import Markmirror from 'react-markmirror';
import '../styles/Ticket.css';

class TicketView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ticket: {
                labels: [],
                comments: [],
                creator: {}
            },
            comment: ''
        };
        this.handleEditorChange = this.handleEditorChange.bind(this);
    }
    componentWillMount() {
        fetch("http://localhost:8080/ticket/" + this.props.match.params.id + ".json").then(result => {
            return result.json();
        }).then(data => {
            this.setState({ticket: data});
        });
    }
    handleEditorChange(text) {
        this.setState({ comment: text});
    }
    parseMD(text) {
        if (text === undefined) return { __html: "" };
        return { __html: Marked(text) };
    }
    render() {
        return (
            <div className="ticketView">
                <div className="infoRow">
                    <div className={"status status-" + this.state.ticket.status}>{this.state.ticket.status}</div>
                    <span><b>Tiketti #{this.state.ticket.id}</b> avattu {this.state.ticket.createTime } käyttäjän <b>{this.state.ticket.creator.name}</b> toimesta</span>
                </div>
                <h3>{ this.state.ticket.header }</h3>
                <hr />
                <div dangerouslySetInnerHTML={ this.parseMD(this.state.ticket.description) }></div>
                <hr />
                {this.state.ticket.comments.map(function (comment) {
                    return (
                        <div key={comment.id} className="commentBlock">
                            <div className="commentInfo">
                                {comment.creator.name} <span>@{comment.creator.username} kommentoi {comment.createTime}</span>
                            </div>
                            <div dangerouslySetInnerHTML={ this.parseMD(comment.content) }></div>
                        </div>
                    )
                }, this)}
                <div className="commentBlock commentEditor">
                    <Markmirror
                        value={this.state.comment}
                        onChange={this.handleEditorChange}
                        showSearch={false}
                        onPreview={value => (Marked(value))}
                    />
                    <button type="button" className="btn btn-success">Kommentoi</button>
                </div>
            </div>
        );
    }
}

export default TicketView;
