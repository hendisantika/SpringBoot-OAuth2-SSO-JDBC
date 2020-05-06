# SpringBoot-OAuth2-SSO-JDBC
### Technologies used:
1. Spring Boot 2.2.6.RELEASE
2. Maven 3.6.3
3. Java 8
4. OAuth2
5. IntelliJ IDEA Ultimate 2020.1
7. MySQL

### Things todo list:
1. Clone this repository: `git clone https://github.com/hendisantika/SpringBoot-OAuth2-SSO-JDBC.git`
2. Go inside the folder: `cd SpringBoot-OAuth2-SSO-JDBC`
3. Go inside the `spring-security-sso-auth-server` folder for auth-server: `cd spring-security-sso-auth-server`
4. Run the application by this command: `mvn clean spring-boot:run`
5. Go inside the `spring-security-sso-ui` folder for ui-server: `cd spring-security-sso-ui`
6. Run the application by this command: `mvn clean spring-boot:run`
7. Open your favorite MySQL Client and run this command:
    ```sql
    CREATE TABLE users 
      ( 
         username VARCHAR(50) NOT NULL PRIMARY KEY, 
         password VARCHAR(100) NOT NULL, 
         enabled  BOOLEAN NOT NULL 
      ); 
    
    CREATE TABLE authorities 
      ( 
         username  VARCHAR(50) NOT NULL, 
         authority VARCHAR(50) NOT NULL, 
         CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users( 
         username) 
      ); 
    
    CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority); 
    
    ```
8. Insert your user, example:
   
   Go to [this link](https://www.devglan.com/online-tools/bcrypt-hash-generator) to encode your password using BCrypt:
   
   ```sql
    INSERT INTO users 
    VALUES      ('naruto', 
                 '$2a$04$OqfaHsz1.I9bzlPeZwuRFOyvyFeDKyOBm5XKhKXGup8Yf9qdP69/2' 
                 /*naruto*/, 
                 true); 
    
    INSERT INTO authorities 
                (username, 
                 authority) 
    VALUES     ('naruto', 
                'ROLE_ADMIN'); 
   ```
   
   
9. Navigate to http://localhost:8082/ui/