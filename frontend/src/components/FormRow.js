import React, { Component } from 'react';

class FormRow extends Component {
    render() {
        return (
            <div className="form-group">
                <label className="col-md-2 control-label">{ this.props.text }:</label>
                <div className="col-md-9">
                    {
                        this.props.template !== undefined ? this.props.template : (
                            <input
                                className="form-control"
                                type={ this.props.type !== undefined ? this.props.type : "text" }
                                name={ this.props.name }
                                value={ this.props.value }
                                onChange={ this.props.onChange }
                                required={ this.props.required !== undefined ? this.props.required : false } />
                        )
                    }
                </div>
            </div>
        );
    }
}

export default FormRow;
