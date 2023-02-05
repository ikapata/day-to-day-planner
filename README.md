
# Day to Day Planner

A day-to-day planner, where the user can add a do-list, a diary entry and track their mood for each day. 




## Dependencies

**Frontend:** Node

**Backend:** Maven3, Java 17

**Database:**  Postgres

## Run Locally

Clone the project

```bash
  git clone https://github.com/ikapata/day-to-day-planner 
```

Go to the project directory

```bash
  cd day-to-day-planner
```

### Frontend

Install dependencies

```bash
  cd frontend/day-to-day-planner
  npm install
```

Start react on port 3000.

```bash
  npm start
```

### Backend

Add your database name, user and password to the properties.yml file. Then run with Maven:

```bash
  cd backend/day-to-day-planner
  mvn spring-boot:run
```

It will start the server on port 8080.

In the future, this app will be dockerized. 


## Roadmap

- [x]  Setup project
- [x]  Add Spring Security and Users
- [x]  Test backend
- [ ]  Add frontend
- [ ]  Profile Page
- [ ]  Dockerize
- [ ]  Google Calendar Synchronisation

