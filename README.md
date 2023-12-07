# Information

This is a simple service for working with quotes.
Implemented adding and getting users, CRUD operations with quotes, as well as obtaining the best and worst quotes
and obtaining graph of the evolution of the votes over time. 

# Technologies used

* Spring Boot
* Hibernate
* H2

# Note

* For ease of implementation, the use user's password is implemented through the type String.
* Since the database H2 doesn't support the creation of a unique index since Spring Boot 2.1, 
it must be manually created in H2 with script: "CREATE UNIQUE INDEX user_quote_index ON votes(user_id, quote_id);"
for this service to work correct.
* to run local via docker container, use the command: docker pull kxnvg/life-quotes
