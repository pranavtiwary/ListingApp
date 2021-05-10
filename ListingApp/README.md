# ListingApp
Listing App listens to command and create user and listing in the application. Its display on microservice architecture.

Internal Module

1. CommandInterface : Spring boot application, provides command interface to communicate to user and listing service.
   ->Dependencies : JDK 8, Maven 3.6.3, Spring boot 2.4.5

2. UserService : Spring boot web and security application. Hold user authentication information
   ->Dependencies : JDK 8, Maven 3.6.3, Spring boot 2.4.5, Spring security, Spring data and In memmory H2 DB.

3. CommandInterface : Spring boot web application, holds listing service.
   ->Dependencies : JDK 8, Maven 3.6.3, Spring boot 2.4.5, Spring Web, Spring data and In memmory H2 DB.


# Software Required :
1. JDK 1.8
2. Maven 3.6.3

#Code Setup 
https://github.com/pranavtiwary/ListingApp.git
git@github.com:pranavtiwary/ListingApp.git

#Run Book :
    1. Open a new terminal and go to project root directory 
        eg : cd /Volumes/HD2/Research/ListingApp
        (you may need to change mod "chmod 777 *.sh")
        Run command "./compile.sh"
        Description: Its runs "mvn clean install" and build/test/create jar file for all our modules
        Output : You should see "BUILD SUCCESS" in the terminal
    2. Open a new terminal and go to project root directory and Run command
        Run command "./runUserService.sh"
        Description: Its runs the User Service and Start a server at 8081 port
        check if this user service is on "http://localhost:8081/actuator" 
        and also check h2 db at "http://localhost:8081/h2-console"
    3. Open a new terminal and go to project root directory and Run command
        Run command "./runListingService.sh"
        Description: Its runs the Listing Service and Start a server at 8085 port
        check if this user service is on "http://localhost:8085/actuator"
        and also check h2 db at "http://localhost:8085/h2-console"   
    4. Open a new terminal and go to project root directory and Run command
        Run command "./runCommandInterface.sh"
        Description: Its runs the Command Interface Service and Start a server at 8085 port
        This is where we enter command line input.


#How to interact?
1. Go to terminal where you have opened command interface
2. You can interact with admin of h2 db, see section how to run h2 db admin.

How to Shutdown?
1. Ctrl+C on each terminal

# Running H2 Database Admin : 

http://localhost:8081/h2-console for user : Verify user CRUD here
http://localhost:8085/h2-console for Listing : Verify listing CRUD here
