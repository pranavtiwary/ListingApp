# ListingApp

Software Required :
1. JDK 1.8
2. Maven 3.6.3

Internal Module

1. CommandInterface : Spring boot applicaiton, provides command interface to communicate to user and listing service.

Dependencies : JDK 8, Maven 3.6.3, Spring boot 2.4.5

2. UserService : Spring boot web and security applicaiton. Hold user authentication information
Dependencies : JDK 8, Maven 3.6.3, Spring boot 2.4.5, Spring security, Spring data and In memmory H2 DB.


3. CommandInterface : Spring boot web applicaiton, holds listing service.

Dependencies : JDK 8, Maven 3.6.3, Spring boot 2.4.5, Spring Web, Spring data and In memmory H2 DB.


Compilation :
1. Open a new terminal and go to project root directory
2. Run command "mvn clean install"

How to start application?

1. Run User Server : Open a new terminal and run command (Must have 8081 port free, its tomcat server)
2. Run Listing Server : open a new terminal and run command (Must have 8082 port free, its tomcat server)
3. Run Command Inteface : Open a new terminal and run command (Its Normal jar application)

How to interact?
1. Go to terminal where you have opened command interface
2. You can interact with admin of h2 db, see section how to run h2 db admin.

How to Shutdown?
1. Ctrl+C on each terminal

# Running H2 Database Admin : 

http://localhost:8081/h2-console for user : Verify user CRUD here
http://localhost:8082/h2-console for Listing : Verify listing CRUD here
