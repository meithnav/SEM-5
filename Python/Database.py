
import sqlite3

conn = sqlite3.connect("test.db")
crsr = conn.cursor()
print("Connected to database")

#Creating table
cmd = "CREATE TABLE Employee(empId INTEGER PRIMARY KEY AUTOINCREMENT, fname VARCHAR(20) NOT NULL, lname VARCHAR(20) NOT NULL, gender CHAR(1) DEFAULT 'M');"
crsr.execute(cmd)

#Inserting data
cmd = "INSERT INTO Employee(fname,lname,gender) VALUES ('Jack','Smith','M'), ('Jane','Doyle', 'F'), ('Tim','Brookes','M'), ('Lydia','Simson','F'), ('Penny','Hill','M');"
crsr.execute(cmd)
conn.commit()

#Reading data
print("All Employees:")
cmd = "SELECT * FROM Employee;"
crsr.execute(cmd)
results = crsr.fetchall()
for emp in results :
    print(emp)

#Updating data
crsr = conn.cursor()
cmd = "UPDATE Employee SET gender='F' WHERE fname='Penny' AND lname='Hill';"
crsr.execute(cmd)
conn.commit()
print("\nAfter updating employee gender: ")
cmd = "SELECT * FROM Employee;"
crsr.execute(cmd)
results = crsr.fetchall()
for emp in results :
    print(emp)

#Deleting data
crsr = conn.cursor()
cmd = "DELETE FROM Employee WHERE fname='Penny';"
crsr.execute(cmd)
conn.commit()
print("\nAfter deleting employee record:")
cmd = "SELECT * FROM Employee;"
crsr.execute(cmd)
results = crsr.fetchall()
for emp in results :
    print(emp)

conn.close()