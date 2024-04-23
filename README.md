[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<h3 align="center">El proyecte grande</h3>

  <p align="center">
    Recipe sharing application
    <br />
    <br />
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


This is a learning project where we worked with Spring Boot and React.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

* [![React][React.js]][React-url]
* [![Spring Boot][Spring.js]][Spring-url]
* [![Hibernate ORM][Hibernate.js]][Hibernate-url]
* [![Axios][Axios.js]][Axios-url]
* [![PostgreSQL][Postgres.js]][Postgres-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

* npm
  ```sh
  npm install npm@latest -g
  ```
* JDK
    - <a href="https://www.oracle.com/java/technologies/downloads/">Download JDK version 17</a>
    - Install it
* maven
    - <a href="https://maven.apache.org/download.cgi">Download Maven</a>
    - Install it
* PSQL
    - <a href="https://www.postgresql.org/download/">Download PSQL</a>
    - Install it
### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki.git
   ```
2. Install NPM packages
   ```sh
   npm install
   ```
3. Create a psql database.
4. Navigate to backend/src/main/resources, and create a file named application.properties.
5. Fill out the application properties file with the following: 
`
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=your-db-url
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=true
codecool.app.jwtSecret=jwt-secret
codecool.app.jwtExpirationMs=8640000
`


<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
To use this application, you need to do the following.

Backend: 
* Navigate to the backend folder.
* Run `mvn spring-boot:run`

Frontend: 
* From the root folder run `npm run dev`.
* In your browser go to http://localhost:5173/.
<p align="right">(<a href="#readme-top">back to top</a>)</p>

Project Link: [https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki](https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki.svg?style=for-the-badge
[contributors-url]: https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki.svg?style=for-the-badge
[forks-url]: https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki/network/members
[stars-shield]: https://img.shields.io/github/stars/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki.svg?style=for-the-badge
[stars-url]: https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki/stargazers
[issues-shield]: https://img.shields.io/github/issues/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki.svg?style=for-the-badge
[issues-url]: https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki/issues
[license-shield]: https://img.shields.io/github/license/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki.svg?style=for-the-badge
[license-url]: https://github.com/CodecoolGlobal/el-proyecte-grande-sprint-1-java-hevniki/blob/master/LICENSE.txt
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Spring.js]: https://img.shields.io/badge/Spring_Boot-grey?style=for-the-badge&logo=Spring-boot
[Spring-url]: https://spring.io/projects/spring-boot
[Hibernate-url]: https://hibernate.org/orm/
[Hibernate.js]: https://img.shields.io/badge/Hibernate_Orm-grey?style=for-the-badge&logo=Hibernate
[Axios-url]: https://axios-http.com/docs/intro
[Axios.js]: https://img.shields.io/badge/Axios-blue?style=for-the-badge&logo=Axios
[Postgres-url]: https://www.postgresql.org/
[Postgres.js]: https://img.shields.io/badge/PostgreSQL-blue?style=for-the-badge
