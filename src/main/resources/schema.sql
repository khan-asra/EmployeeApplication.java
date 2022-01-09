CREATE TABLE employee
(
    id         LONG PRIMARY KEY AUTO_INCREMENT,
    firstName  VARCHAR(25),
    lastName   VARCHAR(25),
    email      VARCHAR(40),
    phone      VARCHAR(12),
    department VARCHAR(40),
    title      VARCHAR(25)
) ;

insert into employee ( firstName, lastName, email, phone, department, title)
VALUES ('SAMPLE','SAMPLE','SA@MPLE','1234567','IT','SAMPLE');