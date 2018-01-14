import React, { Component } from 'react';

import FormRow from '../components/FormRow'
import FormSubmitRow from "../components/FormSubmitRow";

class MailSettings extends Component {
    constructor(props) {
        super(props);
        this.state = {
            mailSenderStuff : {
                MAIL_HOSTNAME : "",
                MAIL_PORT : "587",
                MAIL_PROTOCOL : "smtp",
                MAIL_USERNAME : "",
                MAIL_PASSWORD : ""
            },
            mailProperties : {
                "mail.smtp.auth" : "true",
                "mail.smtp.starttls.enable" : "true",
                "mail.smtp.ssl.trust" : "",
                "mail.smtp.quitwait" : "false"
            }
        };
    }
    handleInputChange = (event) => {
        let obj;
        const name = event.target.name;
        const value = event.target.value;
        switch (name.substr(0, name.indexOf("."))) {
            case "mailSenderstuff":
                obj = this.state.mailSenderStuff;
                obj[name.substr(name.indexOf(".")+1)] = value;
                this.setState({ mailSenderStuff: obj });
                break;
            case "mailProperties":
                obj = this.state.mailProperties;
                obj[name.substr(name.indexOf(".")+1)] = value;
                this.setState({ mailProperties: obj });
                break;
            default:
        }
    };
    handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/settings/mail', {
            method: 'POST',
            headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(this.state),
        }).then(function () {
            window.location.reload();
        });
    };
    render() {
        return (
            <form className="form-horizontal" onSubmit={this.handleSubmit}>
                <h4>Sähköpostipalvelimen asetukset</h4>
                <FormRow text="Osoite" name="mailSenderstuff.MAIL_HOSTNAME"
                         value={this.state.mailSenderStuff.MAIL_HOSTNAME}
                         onChange={this.handleInputChange} required={true} />
                <FormRow text="Portti" name="mailSenderstuff.MAIL_PORT"
                         type="number"
                         value={this.state.mailSenderStuff.MAIL_PORT}
                         onChange={this.handleInputChange} required={true} />
                <FormRow text="Protokolla" name="mailSenderstuff.MAIL_PROTOCOL"
                         type="select" options={[{ text: "SMTP", value: "SMTP" }, { text: "SMTPS", value: "SMTPS" }]}
                         value={this.state.mailSenderStuff.MAIL_PROTOCOL}
                         onChange={this.handleInputChange} required={true} />
                <FormRow text="Käyttäjänimi" name="mailSenderstuff.MAIL_USERNAME"
                         value={this.state.mailSenderStuff.MAIL_USERNAME}
                         onChange={this.handleInputChange} required={true} />
                <FormRow text="Salasana" name="mailSenderstuff.MAIL_PASSWORD"
                         type="password"
                         value={this.state.mailSenderStuff.MAIL_PASSWORD}
                         onChange={this.handleInputChange} required={true} />
                <hr />
                <h4>Lähetys asetukset</h4>
                <FormRow text="Tunnistautminen" name="mailProperties.mail.smtp.auth"
                         type="select" options={[{ text: "Kyllä", value: "true" }, { text: "Ei", value: "false" }]}
                         value={this.state.mailProperties["mail.smtp.auth"]}
                         onChange={this.handleInputChange} required={true} />
                <FormRow text="STARTTLS" name="mailProperties.mail.smtp.starttls.enable"
                         type="select" options={[{ text: "Kyllä", value: "true" }, { text: "Ei", value: "false" }]}
                         value={this.state.mailProperties["mail.smtp.starttls.enable"]}
                         onChange={this.handleInputChange} required={true} />
                <FormRow text="Osoite" name="mailProperties.mail.smtp.ssl.trust"
                         value={this.state.mailProperties["mail.smtp.ssl.trust"]}
                         onChange={this.handleInputChange}/>
                <FormRow text="QUIT odotus" name="mailProperties.mail.smtp.quitwait"
                         type="select" options={[{ text: "Kyllä", value: "true" }, { text: "Ei", value: "false" }]}
                         value={this.state.mailProperties["mail.smtp.quitwait"]}
                         onChange={this.handleInputChange} required={true} />
                <FormSubmitRow />
            </form>
        );
    }
}

export default MailSettings;