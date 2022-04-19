# ENSF409 Food Bank Management System

## ENSF 409 Final Project with Carter Boucher, Ayo Olabode, Bryant Zhang, Morgan Chen - Group 25

This program will take a users name, amount of hampers, the number of male adults, number of female adults, number of
children over 8, and the number of children under 8, and calculate the food required per hamper, outputted to a txt file found in `OrderReports` folder.

### TO RUN PROGRAM

````
Clone repo into the desired folder (Preferably in an IDE and run Controller.java as an entry point)

If running in a terminal, cd into the src folder and compile package
-- > src/edu.ucalgary.ensf409 and use the command ```Javac``` on all java files.
ex.  javac src/edu/ucalgary/ensf409/*.java 

After compiling, cd into src and
run command ```java edu.ucalgary.ensf409.Controller```

//Select libraries must be included when compiling the program.

-Junit4
-Hamcreast
-AWT
-Swing
-MySQL-connector-java-8.0.23
````

#### TEST INFORMATION 
- To run tests the package is one level higher instead of edu.ucalgary.ensf409 --> edu.ucalgary.ensf409.tests
- we have left the testing/database libraries in the lib so run the commands at that location 

#### DATABASE INFORMATION

```
database username: student
database password: ensf
```

##### Code Style

- Used VS code extension prettier for formatting
- Used eclipse auto-comment documentation function which includes  
  @ params
