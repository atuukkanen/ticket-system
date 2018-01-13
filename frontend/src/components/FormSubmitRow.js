import React, { Component } from 'react';

class FormSubmitRow extends Component {
    backToPrevPage() {
        window.history.back();
    }
    render() {
        return (
            <div className="form-group">
                <label className="col-md-2 control-label"/>
                <div className="col-md-9">
                    <input type="submit" className="btn btn-primary" value="Tallenna" />
                    <span/>
                    <input type="reset" className="btn btn-default" value="Peruuta" onClick={() => this.backToPrevPage()} />
                </div>
            </div>
        );
    }
}

export default FormSubmitRow;
