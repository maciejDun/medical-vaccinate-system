# Welcome to Medical Vaccinate System
An application for saving vaccinate terms.
Particular term has its creation and vaccination date and facility id.
Facility has information about place of vaccination.

Users can have role of regular user or admin. Role are assigned depending on information stored in database.
Every user of application login using Google account. Some of email addresses have role admin. 

Regular users can:
    - search terms of vaccination,
    - register to particular term,
    - unregister term

Admin users can:
    - search user list,
    - delete users,
    - create users with roles,
    - delete terms,
    - create terms,
    - search vaccinated users,
    - register user for vaccination,
    - unregister user,
    - search facilities,
    - delete facilities,
    - create facilities

For clearer application understanding see CaseUses.png and EntityDiagram.png

# Running the application

This project expects a PostgreSQL database listening on port 5432.
There is a docker-compose.yml that You should use for that purpose. Before that you should be able to run Docker
containers on Your computer.
To run it, simply execute: docker-compose up
Using your favorite IDE, execute the main class: com.dunin.medicalvaccinatesystem.MediclaVaccinateSystemApplication
After successfully deploying the application, open your browser and go to http://localhost:8080/
