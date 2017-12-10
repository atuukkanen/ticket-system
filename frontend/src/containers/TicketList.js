import React, { Component } from 'react';
import '../styles/Ticket.css';
import InfoTable from "../components/InfoTable";

class TicketView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tickets: []
        }
    }
    componentWillMount() {
        fetch("/data/tickets.json").then(result => {
            return result.json();
        }).then(data => {
            this.setState({tickets: data});
        });
    }
    render() {
        return (
            <div className="ticketView">
                <InfoTable
                    topic="Tiketit"
                    rowClickPath="/tickets/"
                    history={this.props.history}
                    data={this.state.tickets}
                    fields={[
                        { name: "id", showName: "ID" }
                    ]} />
            </div>
        );
    }
}

export default TicketView;
