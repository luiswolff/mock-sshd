# Test specification

This document describes the procedure for testing the mock-sshd application. 
For this purpose, different test cases are described, which should ensure the correct functionality. 
Beforehand, however, the procedure for using the test cases is described. 

## 1. procedure

Since mock-sshd is a desktop application can be tested with simple Unittests no complete functionality. 
Therefore a manual approach was chosen for testing, which in some cases is only supported by predefined scripts.

Each test case is divided into sections:
 1. __Pre Conditions__: What settings must be made on the computer for the test to work.
 2. __Test Steps__: The execution of individual steps, which can be partially executed with the support of scripts. 
 Each steps discripts its expected state after steps are done.
 3. __Post Activities__: The state in which the application or its environment should be when all test steps are completed.

## 2. test cases

### 2.1. test case - start application

#### 2.1.1.: Pre Conditions

Java and Maven should be installed on the local system.
When typing `mvn -version` from the console, the version number should be printed.

#### 2.1.2.: Test Steps

 1. :white_circle: __Building the project__: This is done by running the Maven command `mvn clean package` in the root directory of the project.
 This deletes the previous build directory and performs a clean build.
 When the command is connected, there should be a file named `mock-sshd.jar` in the build directory `target`.
 2. :white_circle: __Starting the application__: From the build directory it should be possible to start the applications with the following command: `java -jar mock-sshd.jar`.
 Now the application screen should be seen.

#### 2.1.3: Post Activities

Close the application with a click on the X button in the top right corner.

