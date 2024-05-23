# Student Management System

This project is a Spring Boot application for managing student data. It connects to a local MySQL database and provides REST APIs to perform CRUD operations on student records.

### Dependencies

1. Spring Web
2. Spring Data JPA
3. MySQL Driver

### Architecture Overview

The project follows a layered architecture where requests from the API client (simulated with Postman) are directed to the API Layer. 
The API Layer contains REST APIs responsible for handling incoming requests. 
These APIs then delegate the business logic to the Service Layer. 
The Service Layer contains the business logic for the REST APIs. 
Finally, the Service Layer interacts with the Data Access Layer to perform database operations.

**Components**

1. API Layer: Contains REST APIs for handling client requests.
2. Service Layer: Implements business logic for managing student data.
3. Data Access Layer: Uses the StudentRepository interface to interact with the MySQL database.

### Setup Instructions

1. Clone the repository:
```
git clone https://github.com/srahuliitb/SpringbootRestProject.git
```


2. Configure MySQL:

    - Install MySQL on your local machine if you haven't already.
    - Create a new database named student. 


3. Update application.properties:

    - Open `src/main/resources/application.properties.`
    - Update the MySQL database connection properties according to your MySQL setup.
    - Provide your MySQL database credentials.


4. Build and run the application:
```
./mvnw spring-boot:run
```

### API Endpoints

1. Create Student
   - URL: `http://localhost:8080/api/v1/student`
   - Method: `POST`
   - Request Body:
     ```
        {
          "name": "John Doe",
          "email": "Doe.John@example.com",
          "dob": 1990-01-30
        }
      ```
   - Response:
     - Success: `HTTP 201 Created`
     - Failure: `HTTP 400 Bad Request`

     
2. Get All Students 
   - URL: `http://localhost:8080/api/v1/student`
   - Method: `GET`
   - Response:
        - Success: `HTTP 200 OK with a list of student records`
        - Failure: `HTTP 404 Not Found if no students found`


3. Update Student
   - URL: `http://localhost:8080/api/v1/student/1?name=Foo%20Bar&email=Bar.Foo@example.com`
   - Method: `PUT`
   - Request Body: None 
   - Response:
      - Success: `HTTP 200 OK with the updated student record`
      - Failure: `HTTP 404 Not Found if the student with the specified ID does not exist`


4. Delete Student
   - URL: `http://localhost:8080/api/v1/student/{id}`
   - Method: `DELETE`
   - Response:
      - Success: `HTTP 204 No Content`
      - Failure: `HTTP 404 Not Found if the student with the specified ID does not exist`

### Testing

You can test the API endpoints using tools like Postman or any other REST client. 
The project is yet to be tested with unit tests and integration tests.

### Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please feel free to open an issue or create a pull request.
