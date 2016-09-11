Building and running application
--------------------------------
- make sure the following prerequisities are met:
    - JDK 1.8 is installed and JAVA_HOME variable is set properly 
    - Maven 3.x is installed and MAVEN_HOME\bin is added to PATH variable 
- clone repository
- build and package application executing:

        mvn package
- unpack rea-robot-1.0.tar.gz found in target directory
- run simulation (using one of example files distributed with application):

        ./run.sh simulations/rea-a.txt
        
Input file format
-----------------
- text file
- 1 command per line
- supported commands:
    
        PLACE X,Y,F
        MOVE
        LEFT
        RIGHT
        REPORT
   where:
    - X - X coordinate (number)
    - Y - Y coordinate (number)
    - F - facing direction, one of
            
            NORTH
            EAST
            SOUTH
            WEST
            
Tests
----------------------
Application has been tested on:
- OS X 10.11.6
- Oracle JDK 1.8.0_101 x64

Limitations
-----------
- distribution contains only Unix/Linux and OSX compatible script for running application
- commands are case sensitive and do not allow extra whitespaces
- empty lines and invalid/unknown commands are silently ignored
- input file needs to contain at least one valid REPORT command, otherwise simulation will not produce any output 
        

