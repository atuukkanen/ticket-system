import React, { Component } from 'react';
import '../styles/Ticket.css';
import InfoTable from "../components/InfoTable";

class TicketView extends Component {
    render() {
        return (
            <div className="ticketView">
                <InfoTable
                    topic="Tiketit"
                    rowClickPath="/tickets/"
                    history={this.props.history}
                    data={[]}
                    fields={[
                        { name: "id", showName: "ID" }
                    ]} />
            </div>
        );
    }
}

export default TicketView;
