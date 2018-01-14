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
                creation: {
                    creator: {}
                }
            },
            comment: ''
        };
    }
    componentDidMount() {
        document.querySelectorAll("button:not([type=button]):not([type=submit])").forEach(function(element) {
            element.setAttribute("type", "button");
        });
    }
    componentWillMount() {
        fetch("/api/ticket/" + this.props.match.params.id).then(result => {
            return result.json();
        }).then(data => {
            this.setState({ticket: data});
        });
    }
    handleClose = (event) => {
        if (this.state.comment.length > 0) this.sendComment();
        fetch('/api/ticket/close/' + this.state.ticket.id, {
            method: 'PUT'
        }).then(function () {
            window.location.reload();
        });
        event.preventDefault();
    };
    handleEditorChange = (text) => {
        this.setState({ comment: text});
    };
    handleOpen = (event) => {
        if (this.state.comment.length > 0) this.sendComment();
        fetch('/api/ticket/open/' + this.state.ticket.id, {
            method: 'PUT'
        }).then(function () {
            window.location.reload();
        });
        event.preventDefault();
    };
    handleSubmit = (event) => {
        this.sendComment(function () {
            window.location.reload();
        });
        event.preventDefault();
    };
    parseMD(text) {
        if (text === undefined || text === null) return { __html: "" };
        return { __html: Marked(text) };
    }
    sendComment(fn) {
        fetch('/api/comment/' + this.state.ticket.id, {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({commentText: this.state.comment}),
        }).then(function () {
            if (fn !== undefined) fn();
        });
    }
    render() {
        return (
            <div className="ticketView">
                <div className="infoRow">
                    <div className={"status status-" + this.state.ticket.status}>{this.state.ticket.status}</div>
                    <span><b>Tiketti #{this.state.ticket.id}</b> avattu {this.state.ticket.createTime } käyttäjän <b>{this.state.ticket.creation.creator.name}</b> toimesta</span>
                </div>
                <h3>{ this.state.ticket.header }</h3>
                <hr />
                <div dangerouslySetInnerHTML={ this.parseMD(this.state.ticket.description) }></div>
                <hr />
                {this.state.ticket.comments.map(function (comment) {
                    return (
                        <div key={comment.id} className="commentBlock">
                            <div className="commentInfo">
                                {comment.creation.creator.name} <span>@{comment.creation.creator.username} kommentoi {comment.createTime}</span>
                            </div>
                            <div dangerouslySetInnerHTML={ this.parseMD(comment.commentText) }></div>
                        </div>
                    )
                }, this)}
                <div className="commentBlock commentEditor">
                    <form className="form-horizontal" onSubmit={this.handleSubmit}>
                        <Markmirror
                            value={this.state.comment}
                            onChange={this.handleEditorChange}
                            showSearch={false}
                            onPreview={value => (Marked(value))}
                        />
                        <div className="submitRow">
                            <button type="submit" className="btn btn-success">Kommentoi</button>
                            { this.state.ticket.status === "OPEN" ? (
                                <button type="submit" className="btn btn-danger" onClick={this.handleClose}>{ this.state.comment.length > 0 ? "Kommentoi ja sulje" : "Sulje" }</button>
                            ) : (
                                <button type="submit" className="btn btn-secondary" onClick={this.handleOpen}>{ this.state.comment.length > 0 ? "Kommentoi ja avaa" : "Avaa"  }</button>
                            )}
                        </div>
                    </form>
                </div>
            </div>
        );
    }
}

export default TicketView;
