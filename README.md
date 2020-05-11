This is a solution given for Family tree problem. 

**Tech Stack**<br>
    - Scala<br>
    - Scalatest (for unit test)
    
####Use src/resources/input.txt file for inputs.<br>
####Use `sbt run` to start application
####Use `sbt test` to run test

####Supported Commands are
-START_FAMILY maleName femaleName (initialise family i.e create root family)<br>
-MARY maleName femaleName (Add mariage relation between male and female)<br>
-ADD_CHILD motherName childName gender (Add children to mother with given name and gender)<br>
-GET_RELATIONSHIP personName relation (Get relatives of person by name and relation)

####Main.scala is a driver class
