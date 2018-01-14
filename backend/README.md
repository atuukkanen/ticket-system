# Ticket System Backend


## Environmental Variables

### Optional, but required for mail notifications to work "out-of-the-box"
* MAIL_HOSTNAME (example: ```smtp.emailserver.com```)
* MAIL_USERNAME (example: ```ticketsystememailuser```)
* MAIL_PASSWORD (example: ```XQ8b0nz!^QwU0}bdW$sXZAEiM1R4^O+>G-NZ~%6/}7.[@uW&AHn~Y$/A[[g`se[.```)

### Required
* DATABASE_URL (example: ```jdbc:mysql://databasehost.com:3306/ticketdatabase```)
* DATABASE_USERNAME (example: ```ticketuser```)
* DATABASE_PASSWORD (example: ```pBiJ%8Wa%]<Hl_#'tCO9I{<6fm[]P_rcIT'F+;&JjJtYJ~&z-o.^mm=!?:Y-l.=/```)

### Optional
* MAIL_PORT (default value: ```587```)
* MAIL_PROTOCOL (default value: ```smtp```)
* JPA_SHOW_SQL (default value: ```false```)


## Swagger Documentation

Swagger UI documentation for endpoints is available in host/application-root/swagger-ui.html

## Mail Templates

Mail templates use Thymeleaf. Mail template options are defined in resources/mailtemplate.properties.

## Configuring Mail On Runtime

Endpoint ```/settings/mail``` provides functionality for configuring mail on runtime. Example request body for setting a new mail sending server:
```
{
    "mailSenderStuff" : {
        "MAIL_HOSTNAME" : "smtp.gmail.com",
        "MAIL_PORT" : "587",
        "MAIL_PROTOCOL" : "smtp",
        "MAIL_USERNAME" : "ticketusername@gmail.com",
        "MAIL_PASSWORD" : "app-password"
    },
    "mailProperties" : {
        "mail.smtp.auth" : "true",
        "mail.smtp.starttls.enable" : "true",
        "mail.smtp.ssl.trust" : "smtp.gmail.com",
        "mail.smtp.quitwait" : "false"
    }
}
```