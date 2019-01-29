# flight-aggregator
API aggregator for Flight Search

# API Documentation

https://documenter.getpostman.com/view/1148681/RztkMUoA

# Run
Run Following Command:

    - ./mvnw spring-boot:run   

# Test
Run Following Command:

    - ./mvnw clean install   

# Notes

1- There is no logging(!)

2- There is no centralized exception handling mechanism to return
well-structured response to the client.

3- Note that some time when application query the cheap flight API
time format comes in 2 different format. So that application takes one that 
come more often than other.

