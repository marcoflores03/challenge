# Challenge

## Pre-requisites:
#### 1 - OP.: Windows
    Note = Framework available to add other OPs like Linux or Mac with few updates
#### 2 - Chrome (v.83)
    Note: Framework available to fix other chrome version or browsers.
#### 3 - Maven
#### 4 - Java JDK

## Steps to Execute Tests:

##### 1 - Go to test project folder
##### 2 - Execute mvn clean install
##### 3 - To rerun, execute mvn test

## Steps to Execute Tests with IntelliJ IDE:
##### 1 - Open test project
##### 2 - Find pom.xml file and click on secondary mouse button.
##### 3 - Click on maven -> reimport
##### 4 - Click on maven -> Download Sources
##### 5 - Find testng.xml file, and then click on secondary mouse button
##### 6 - Click on Run item 

## Steps to Execute Tests for Mobile Emulator:
##### 1 - Execute mvn test -DtheDriver="chromeMobile"

##### Note1: Test cases are running for Chrome browser by default.
##### Note2: Chrome emulator is setup as 'iPhone X', but it can be update to emulate more devices.