CREATE TABLE tbl_employee (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    department VARCHAR(255),
    birthday DATE NOT NULL
)