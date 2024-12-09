For the program to compile and run you will need a working installation of the JDK (Java Development Kit) and JavaFX SDK.

Although other versions of these may allow the program to work as intended, it is recommended to use JDK 21.0.5 and JavaFX SDK 21.0.5 as the program was developed using these versions.

Although it is possible to compile the program via the CLI (Command Line Interface) using commands, it is recommended that you use an IDE, such as Intellij IDEA Community Edition, which will greatly simplify the process of compiling the program.

To compile the program via Intellij IDEA, create a new project and copy the source files into the src folder.

Then add JavaFX to the project by going to File -> Project Structure -> Libraries.

Click the + button, then select Java in the New Project Library section.
Navigate to the lib folder in your JavaFX SDK installation, then add the folder.

Intellij should automatically select all items in the folder for you. Navigate to the Modules section and check that JavaFX has been added to the project there as well.

Click apply, then click OK, then open the Main class and attempt to run Main.main().

This will create a runtime configuration for Main.

Click on the dropdown menu at the top right of the screen and select edit configurations, then select Main -> Modify options -> Add VM options to add a field for VM options in the configuration.

In the VM options field, paste the following "--module-path "PATH\TO\javafx-sdk-21.0.5\lib" --add-modules javafx.controls,javafx.fxml", with "PATH\TO" being replaced with the path to your JavaFX SDK installation.

Click apply, then click OK.

Ensure that in the dropdown menu either Main is selected as the file to run, or Current File is selected and the Main class is currently open in the editor.

Press the green play button to compile the program and wait for it to run.