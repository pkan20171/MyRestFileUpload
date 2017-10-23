# MyRestFileUpload
### Overview
This Maven project contains the Java code for uploading file to 
the file system using Spring Boot and REST Api. REST Api uses multipart
file request to upload files.  It also creates 
a HSQL in memory DB and allows some meta data regarding files
to be stored in the database. The data can again be queried using
REST Api.

Furthermore, spring & mockito based junit test cases are written for
example to retrieve files metadata stored in the database.

### Working with the Code
The project was developed and tested using Java 8 in the Luna Eclipse
and therefore should run fine in any
recent version of Eclipse or another IDE of your choice
that supports Java 8.

### Building the Project
You can also build the project using Maven outside of any IDE:
```
mvn clean install
```

### Package Organization
Java classes for file upload project are organized using mvc framework pattern as follows:

com.myrestfileupload.controller - for all controller classes
com.myrestfileupload.model - for all models that will hold data
com.myrestfileupload.service - for all service classes
com.myrestfileupload.utils - for all utilities such as a FileModelRowMapper

### Pre-requisite steps
Create folders as follows:
C://temp//orig-dir - location to put the original files for testing
C://temp//upload-dir - location where the API will put the file

### Project Execution
Project can be run using the following command:
```
mvn spring-boot:run
```

### Invoking the REST Api
curl -F projectNumber=1010 -F projectName="Aetna Gold" -F fileName="data1.txt" -F files=@"C://temp//orig-dir//data1.txt"  http://localhost:8080/api/upload/multi/

### Running the tests
The test classes for the Spring service tutorial are:
- com.myrestfileupload.controller.SpringBootRestUploadControllerTest


The test classes may be run as JUnit tests from your IDE
or using the Maven command line:
```
mvn test
```

### Suggested Improvements
1. I started putting a web page to upload the files. This is not complete yet and can be expanded to handle uploads, errors etc.
2. Currently there is only one unit test as a demonstration of understanding of controller, unit tests must be written for service and utils also
3. Exception handling can be added to address situations such as very large files and other errors
