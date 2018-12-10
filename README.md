# RabbitMQDBTransaction
A project to demonstrate transactions containing both RabbitMQ Messaging and a DB Operation

This project is made as a prototype for west company.
there are lots of scenarios that putting a RabbitMQ Message Transaction and a DB Transaction inside one transaction was cuasing an issue that is why this project is created.

in order to test this you need a RabbitMQ installed on your local or change the setting to point to a remote server.

there are different branches.
first one is a simple code using H2 Embedded db.

the second branch is using Oracle, you can download and install and oracle express on local to test it or test it against a QA, Dev, ... Oracle DB.

