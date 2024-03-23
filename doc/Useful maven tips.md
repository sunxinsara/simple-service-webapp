# Useful Maven Tips

Maven is a powerful build tool used in software development for Java projects, simplifying the build process like compilation, documentation, packaging, and deployment. This document provides an overview of Maven's lifecycle, key Maven commands, and their integration with Jenkins, along with important keywords in the `pom.xml` file.

<img src="file:///C:/Users/sunxin/AppData/Roaming/marktext/images/2024-03-23-16-36-56-image.png" title="" alt="" data-align="center">

## Maven Commands

Here are some commonly used Maven commands:

- `mvn compile`: Compiles the source code of the project.
- `mvn test`: Runs tests using a suitable unit testing framework.
- `mvn package`: Packages the compiled code in its distributable format, such as a JAR.
- `mvn install`: Installs the package into the local repository, for use as a dependency in other projects locally.
- `mvn clean`: Cleans up after the build process by deleting the target directory.
- `mvn deploy`: Deploys your packaged code to a remote repository.

## Key Elements in pom.xml

The `pom.xml` file is central to a Maven project, containing configuration details, project dependencies, and more. Here are some key elements:

- **`<groupId>`**: Defines the group or organization that the project belongs to. This is often used to structure the package names.
- **`<artifactId>`**: The name of the project itself. Together with the groupId, it uniquely identifies the project.
- **`<version>`**: The project's version. Maven supports versioning and can manage project versions in dependencies.
- **`<dependencies>`**: Specifies project dependencies that Maven will automatically download and include in the build process.
- **`<build>`**: Contains build-related information, including plugins and their configurations.
  
  ![](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-16-50-37-image.png)

## JaCoCo Configuration

![](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-16-52-02-image.png)

## Unit test collection

![](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-16-53-12-image.png)

## SonarQube scanner

![](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-16-53-47-image.png)

## Maven command in Jenkisfile

### Build Stage

`bat "mvn clean package -DskipTests"`: This command cleans up the artifacts from the previous build and then performs a new build while skipping tests. This is to ensure a quick compilation and packaging process to verify that the build succeeds without needing to run tests.

![](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-16-57-53-image.png)

### Test Stage

`bat "mvn test jacoco:prepare-agent jacoco:report"`: This command runs unit tests and collects JaCoCo code coverage reports. Since the build stage skipped the tests, this stage ensures that tests are executed and coverage data is generated, which is an essential part of the continuous integration process.

![](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-16-58-02-image.png)

### SonarCloud Analysis Stage

`bat "mvn sonar:sonar ..."`: This command uploads the code analysis results to SonarCloud for code quality assessment. This stage does not involve building or testing the project but focuses on analyzing the quality of the code.

![](C:\Users\sunxin\AppData\Roaming\marktext\images\2024-03-23-16-58-18-image.png)

## Other Useful Parameters

- `-Dmaven.clean.failOnError=false`: When executing `mvn clean`, the build will not fail even if there are errors.

- `-Dmaven.javadoc.skip=true`: Skips the generation of Javadoc, which is useful for speeding up the build, especially during development.

- `-Dmaven.compiler.source=1.8 -Dmaven.compiler.target=1.8`: Specifies the source and target versions of the Java compiler.

- `-DoutputDirectory=custom/dir`: Modifies the behavior of a plugin, for example, changing the output directory of the JAR file.

- `-Pprofile-name`: Activates one or more Maven build profiles. This is useful for switching build configurations between different environments.

- `-X`: Provides more detailed output, useful for debugging build issues.

- `-e`: Provides a full stack trace of errors in case of build failure, helping in diagnosing problems.
