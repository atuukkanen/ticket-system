import React, { Component } from 'react';

class FormRow extends Component {
    render() {
        return (
            <div className="form-group row">
                <label className="col-sm-2 col-form-label">{ this.props.text }:</label>
                <div className="col-sm-10">
                    {
                        this.props.template !== undefined ? this.props.template :
                        this.props.type === "select" ? (
                            <select
                                className="form-control"
                                name={ this.props.name }
                                value={ this.props.value }
                                onChange={ this.props.onChange }
                                required={ this.props.required !== undefined ? this.props.required : false }>
                                {this.props.options.map(function(option) {
                                    return (<option key={option.value + "-" + option.text} value={option.value}>{option.text}</option>);
                                })}
                            </select>
                        ) : (
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
