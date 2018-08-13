--2.1 SELECT
--Task – Select all records from the Employee table.
SELECT * FROM EMPLOYEE;
--Task – Select all records from the Employee table where last name is King.
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';
--Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;
--2.2 ORDER BY
--Task – Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM ALBUM ORDER BY TITLE DESC;
--Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;
--2.3 INSERT INTO
--Task – Insert two new records into Genre table
INSERT INTO GENRE VALUES (26,'G-FUNK');
INSERT INTO GENRE VALUES (27,'GOOD');
--Task – Insert two new records into Employee table
INSERT INTO EMPLOYEE 
VALUES (9,'Sato','Sam','MADMAN',8,TO_DATE('1995-12-25','yyyy-mm-dd'),TO_DATE('2018-7-30','yyyy-mm-dd'),'18 Clarendon Pl','Bloomfield','NJ','US of A','07003','+1 862-596-7526','+1 (403) 262-332','halfcreative@gmail.com');
INSERT INTO EMPLOYEE
VALUES (10,'Sato','Alan','Reasonable guy',9,TO_DATE('1995-12-25','yyyy-mm-dd'),TO_DATE('2018-7-30','yyyy-mm-dd'),'18 Clarendon Pl','Bloomfield','NJ','US of A','07003','+1 862-596-7526','+1 (403) 262-332','halfcreative@gmail.com');
--Task – Insert two new records into Customer table
INSERT ALL
INTO CUSTOMER
VALUES (60,'Sato','Sam','MADMAN INC','18 Clarendon Pl','Bloomfield','NJ','US of A','07003','+1 862-596-7526','+1 (403) 262-332','halfcreative@gmail.com',3)
INTO CUSTOMER
VALUES (61,'Sato','Alan','Reason Co','18 Clarendon Pl','Bloomfield','NJ','US of A','07003','+1 862-596-7526','+1 (403) 262-332','sam.alan.sato@gmail.com',3)
SELECT * FROM DUAL;
--2.4 UPDATE
--Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
--Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';
--2.5 LIKE
--Task – Select all invoices with a billing address like “T%”
SELECT * FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';
--2.6 BETWEEN
--Task – Select all invoices that have a total between 15 and 50
SELECT * FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;
--Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM EMPLOYEE
WHERE HIREDATE BETWEEN TO_DATE('2003-06-01','yyyy-mm-dd') AND TO_DATE('2004-03-01','yyyy-mm-dd');
--2.7 DELETE
--Task – Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'; --32
DELETE FROM INVOICELINE 
WHERE INVOICEID IN (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 32);
DELETE FROM INVOICE
WHERE CUSTOMERID = 32;
DELETE FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
--3.1 System Defined Functions
--Task – Create a function that returns the current time.
SELECT TO_CHAR
    (CURRENT_TIMESTAMP, 'HH24:MI:SS') "NOW"
     FROM DUAL;
--Task – create a function that returns	 the length of name in MEDIATYPE table

--3.2 System Defined Aggregate Functions
--Task – Create a function that returns the average total of all invoices 
SELECT AVG(TOTAL) FROM INVOICE;
--Task – Create a function that returns the most expensive track
SELECT NAME FROM TRACK WHERE UNITPRICE IN (SELECT MAX(UNITPRICE) FROM TRACK);
--3.3 User Defined Scalar Functions
--Task – Create a function that returns the average price of invoiceline items in the invoiceline table

--3.4 User Defined Table Valued Functions
--Task – Create a function that returns all employees who are born after 1968.

--4.1 Basic Stored Procedure
--Task – Create a stored procedure that selects the first and last names of all the employees.

--4.2 Stored Procedure Input Parameters
--Task – Create a stored procedure that updates the personal information of an employee.

--Task – Create a stored procedure that returns the managers of an employee.

--4.3 Stored Procedure Output Parameters
--Task – Create a stored procedure that returns the name and company of a customer.

--Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).

--6.1 AFTER/FOR
--Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.

--Task – Create an after update trigger on the album table that fires after a row is inserted in the table

--Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.

--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID 
FROM INVOICE
INNER JOIN CUSTOMER ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID;
--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT INVOICE.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL FROM INVOICE
LEFT JOIN CUSTOMER ON INVOICE.CUSTOMERID = CUSTOMER.CUSTOMERID;
--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT ARTIST.NAME, ALBUM.TITLE FROM ARTIST RIGHT JOIN ALBUM ON ARTIST.ARTISTID = ALBUM.ARTISTID;
--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.

--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.

