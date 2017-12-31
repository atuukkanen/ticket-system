import React, { Component } from 'react';

class InfoTable extends Component {
    handleClick(id) {
        this.props.history.push(this.props.rowClickPath + id);
    }
    render() {
        return (
            <div className="panel panel-default">
                <div className="panel-heading">
                    <h4>{ this.props.topic }</h4>
                </div>
                <table className="table table-striped">
                    <thead>
                    <tr>
                        {this.props.fields.map(function (field) {
                            return <th key={field.name}>{field.showName}</th>
                        })}
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.data.map(function (row) {
                        return <tr key={row.id} onClick={() => this.handleClick(row.id)}>
                            {this.props.fields.map(function (field) {
                                return <td key={field.name}>{row[field.name]}</td>
                            })}
                        </tr>
                    }, this)}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default InfoTable;