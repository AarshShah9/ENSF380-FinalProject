# 380-Final Project

This is the repo that houses the project for the ENSF-380 final project that creates a application for the
Wildlife Rescue that scedhules tasks based on a SQLDatabase and inferred tasks based on the amimals kept
and provides a GUI that allows the user to create .txt file of the schedule for the day.

## Table of Contents

- [Installation](#installation)
- [Usage](#Usage)
- [Documentation](#Documentation)
- [Contributing](#Contributing)
- [License](#License)

## Installation

[Include instructions for installing any required dependencies or libraries for your project. Also, provide instructions on how to clone and set up the project locally.]

#### Dependencies

- mysql-connector for connecting to a hosted MySQL database [Download Link](http://www.java2s.com/Code/Jar/m/Downloadmysqlconnectorjar.htm)
- JUnit for testing [Download Link](https://sourceforge.net/projects/junit/files/junit/4.10/)
- Hamcrest for running testing [Download Link](https://mvnrepository.com/artifact/org.hamcrest/hamcrest-core/1.3)

## Usage

The following sections explains how to use the code as a developer and a user

### Using the Program as a User

- SAVINO ADD HERE

### Runnning Tests in Command Line

- 1. Clone & cd into the repo in terminal (e.g. cmd for windows)
- 2. Add the dependencies listed in [dependencies](#Dependencies) to the lib folder
- 3. Run the compliation: javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23/mysql-connector-java-8.0.23.jar edu/ucalgary/oop/SQLDatabaseTest.java
- 4. Run the test file: java -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.oop.ExampleTest

Note ensure you replace 'ExampleTest' with whatever test file you want to run.

### Runnning files in Command Line

The same steps should be followed as in running tests, but the the junit and hamcrest libraries are optional, as they are not needed.

## Documentation

WILL EXPLAIN HERE HOW YOUR DAILYSCHEDULE WORKS

## Contributing

The Current Devs for this project are:

- Aarsh Shah
- William Fraser
- Nick Savino
- Sarim Sheikh

Feel free to fork this repo and submit a pull request with relevant details of any important additions to the code base.

## License

This project is included under the [MIT License](LICENSE)
