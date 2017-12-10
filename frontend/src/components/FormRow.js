import React, { Component } from 'react';

class FormRow extends Component {
    render() {
        return (
            <div className="form-group">
                <label className="col-md-3 control-label">{ this.props.text }:</label>
                <div className="col-md-8">
                    <input className="form-control" type="text" name={ this.props.name } value={ this.props.value } onChange={this.props.onChange} />
                </div>
            </div>
        );
    }
}

export default FormRow;
