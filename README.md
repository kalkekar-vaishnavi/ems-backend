# Employee Management System API

A small Spring Boot employee management API built with Java 17, Spring Data JPA, and H2.
The database is created automatically in the project's `data` folder when the app starts.

## Run the application

From this folder, run:

```powershell
.\mvnw.cmd spring-boot:run
```

The API is available at `http://localhost:8082/api/employees`.

## API endpoints

| Method | Endpoint | Purpose |
| --- | --- | --- |
| GET | `/api/employees` | List all employees |
| GET | `/api/employees/{id}` | Get one employee |
| POST | `/api/employees` | Add an employee |
| PUT | `/api/employees/{id}` | Update an employee |
| DELETE | `/api/employees/{id}` | Delete an employee |

Send this JSON body for create and update:

```json
{
  "firstName": "Asha",
  "lastName": "Patel",
  "email": "asha.patel@example.com",
  "department": "IT",
  "jobTitle": "Developer",
  "salary": 45000
}
```

First name, last name, email, department, job title, and a positive salary are required.
Email addresses must be unique.
