# student-course-management
Student Course Management Application

Use cases supported:

- Adding new students

- Fetching All students details

- Fetching specific student details

- Updating specific student

- Deleting specific student

- Adding new courses

- Fetching all courses

- Fetching specific course

- Updating course

- Deleting course

Unit test coverage: added only sample test cases due to lack of time. Test cases can be added for all above scenarios to increase the code coverage.

H2 database is used for storing the student and course details. No specific actions required on database side. The schema and tables will be created in-memory once the application is started successfully.

Build and Execution Instructions

Pre-requisites : maven command line tool(mvn), java 17 installed in target environment

Build steps

Download the source code from this git repo to a local folder(refered as %source folder%)

Run below maven command

%source folder% mvn clean install

Check if application jar file "student-course-management-1.0.0.jar" is created in %source folder%/target directory

Sample output:


Execute the application using below command

%source folder% java -jar target/student-course-management-1.0.0.jar

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.2.3)

2024-02-28T17:21:47.488+11:00  INFO 27780 --- [           main] com.apromore.Application                 : Starting Application using Java 17.0.9 with PID 27780 (C:\Apromore\student-course-management\target\classes started by satis in C:\Apromore\student-course-management)
2024-02-28T17:21:47.496+11:00 DEBUG 27780 --- [           main] com.apromore.Application                 : Running with Spring Boot v3.2.3, Spring v6.1.4
2024-02-28T17:21:47.497+11:00  INFO 27780 --- [           main] com.apromore.Application                 : No active profile set, falling back to 1 default profile: "default"
2024-02-28T17:21:49.804+11:00  INFO 27780 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2024-02-28T17:21:49.966+11:00  INFO 27780 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 126 ms. Found 2 JPA repository interfaces.
2024-02-28T17:21:50.801+11:00  INFO 27780 --- [           main] o.s.cloud.context.scope.GenericScope     : BeanFactory id=a32e184a-d247-3582-a56b-18c2b1095822
2024-02-28T17:21:52.522+11:00  INFO 27780 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-02-28T17:21:52.545+11:00  INFO 27780 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-02-28T17:21:52.546+11:00  INFO 27780 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.19]
2024-02-28T17:21:52.747+11:00  INFO 27780 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-02-28T17:21:52.752+11:00  INFO 27780 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 5095 ms
2024-02-28T17:21:52.883+11:00  INFO 27780 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2024-02-28T17:21:53.478+11:00  INFO 27780 --- [           main] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection conn0: url=jdbc:h2:mem:f7aa96e4-0b5c-4adb-bcba-f1c564419cca user=BELONG
2024-02-28T17:21:53.483+11:00  INFO 27780 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2024-02-28T17:21:53.512+11:00  INFO 27780 --- [           main] o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:f7aa96e4-0b5c-4adb-bcba-f1c564419cca'
2024-02-28T17:21:54.123+11:00  INFO 27780 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2024-02-28T17:21:54.321+11:00  INFO 27780 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.4.4.Final
2024-02-28T17:21:54.440+11:00  INFO 27780 --- [           main] o.h.c.internal.RegionFactoryInitiator    : HHH000026: Second-level cache disabled
2024-02-28T17:21:55.107+11:00  INFO 27780 --- [           main] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2024-02-28T17:21:55.232+11:00  WARN 27780 --- [           main] org.hibernate.orm.deprecation            : HHH90000025: H2Dialect does not need to be specified explicitly using 'hibernate.dialect' (remove the property setting and it will be selected by default)
2024-02-28T17:21:57.097+11:00  INFO 27780 --- [           main] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000489: No JTA platform available (set 'hibernate.transaction.jta.platform' to enable JTA platform integration)
2024-02-28T17:21:57.101+11:00  INFO 27780 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2024-02-28T17:21:58.805+11:00  WARN 27780 --- [           main] JpaBaseConfiguration$JpaWebConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
2024-02-28T17:22:01.240+11:00  INFO 27780 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path ''
2024-02-28T17:22:01.483+11:00  INFO 27780 --- [           main] com.apromore.Application                 : Started Application in 15.395 seconds (process running for 16.602)
Test Instructions


   - If the application is executed in AWS EC2 server, the public IP address of the server can be used in Postman URL.
   - 
Further improvements that can be implemented given more time.

 - The oauth2.0 implementation for API security
 - Global Exception Handler can be added for enhanced exception handling
 
 - Test cases can be improved by adding blackbox testcases(using RestAssured or TestRestTemplate)
 
 - Swagger documentation can be added for APIs
 
 - Test cases can be added to test rate limitting scenarios which can further improve code coverage percentage
 
 - Containerization can be done using docker and deploy in ECS/Kubernetes clusters for high availability and better performance.
