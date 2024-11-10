# Technology1 - Technical Test

This project is a Spring Boot application that uses Gradle as the build tool. Follow the instructions below to set up the project on a Windows machine and build it using `gradlew.bat`.

## Prerequisites

Make sure the following prerequisites are installed on your machine before starting:

1. **Java Development Kit (JDK) 21+**
    - Download and install the JDK from  [AdoptOpenJDK](https://adoptopenjdk.net/), This project has been confirmed to work with the 'Latest LTS Release' version jdk-21.0.5+11.
    - After installation, ensure the `JAVA_HOME` environment variable is set:
        1. Open *Environment Variables* in your System Properties.
        2. Under *System Variables*, click *New* and set:
            - **Variable Name**: `JAVA_HOME`
            - **Variable Value**: The path to your JDK installation (e.g., `C:\Program Files\Java\jdk-11.0.10`)
        3. Add `JAVA_HOME\bin` to your system's `Path` variable.
    - To confirm the installation, open a new command prompt and type:
      ```bash
      java -version
      ```
      This should display the Java version installed.

2. **Git (optional, if cloning from a repository)**
    - Download and install Git from [Git for Windows](https://gitforwindows.org/).

## Steps to Set Up and Run the Project

NOTE: these commands are geared towards running on a windows machine, for linux/mac commands remove the '.bat' part of the command as Gradle provides linux based scripts for convenience.

1. **Clone the Repository**
    - If you're working with a repository, clone it using Git:
      ```bash
      git clone <repository-url>
      ```
    - Navigate into the project directory:
      ```bash
      cd <project-directory>
      ```

2. **Verify `gradlew.bat` Permissions**
    - Ensure the `gradlew.bat` file is executable. You can verify permissions by right-clicking the file, selecting *Properties*, and making sure no permissions are blocked under the *Security* tab.

3. **Build the Project**
    - In the root of the project directory, run the following command to build the project:
      ```bash
      gradlew.bat build
      ```
    - This will download all necessary dependencies and build the project. You should see `BUILD SUCCESSFUL` in the command prompt if everything is configured correctly.

4. **Run the Application**
    - To start the Spring Boot application, use the following command:
      ```bash
      gradlew.bat bootRun
      ```
    - By default, the application will be accessible at `http://localhost:8080`. You can confirm it’s running by visiting this URL in your web browser.

5. **Create Docker image**
    - (Untested) There is a `dockerfile` added to the root of the project that once you have run `gradlew.bat build` to create the artefact, can be used to build a docker image for you to run.

## Troubleshooting

- **Command not recognized**: If `java` or `gradlew.bat` commands are not recognized, ensure that Java is installed and that the `JAVA_HOME` variable is set correctly.
- **Port already in use**: If port `8080` is already occupied, modify the port configuration in the `application.properties` file located in `src/main/resources`, and also update the `dockerfile` to use the same port:
  ```properties
  server.port=8081