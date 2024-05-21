# TransposeNotes

TransposeNotes is a Java application that transposes a collection of musical notes by a specified number of semitones. The application reads an input JSON file containing the notes, transposes them, and writes the transposed notes to an output JSON file. If any note falls out of the valid range after transposition, the application will print an error message and exit without creating an output file.

## Requirements

- Java 8 or higher
- Gradle

## Building the Project

To build the project, run the following command in the project directory:

```sh
gradle build

