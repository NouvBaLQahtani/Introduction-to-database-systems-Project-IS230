# UniScheduler
This project is part of the introduction to database systems course under my bachelor's degree in software engineering

## Description
UniScheduler is a Java program that allows interaction with the `Room` table of a Maria database. The program provides the following capabilities:

1. **Insert Operation**: The program prompts the user to enter values for each attribute of the `Room` table, one by one, and then inserts the new record into the database.
2. **Display Operation**: The program displays all the records currently inserted in the `Room` table.
3. **Exit Operation**: The program ends its execution and exit. 


The program handles any exceptions that may occur during execution and provides clear messages about the underlying problems.

## Features
- Interaction with the `Room` table of a Maria database using JDBC.
- Insert operation: Prompts the user for values of each attribute and inserts a new record into the `Room` table.
- Display operation: Displays all the records currently inserted in the `Room` table.
- Exception handling: Provides clear error messages for any exceptions that may occur during program execution.
## First Screen 

<img width="474" alt="image" src="https://github.com/NouvBaLQahtani/introduction-to-database-systems-Project-IS230/assets/106460665/7c28c714-8904-4bdf-a29d-a646e20cd8f0">


## Example Output of the three options 
1. INSERT Operation


<img width="474" alt="image" src="https://github.com/NouvBaLQahtani/introduction-to-database-systems-Project-IS230/assets/106460665/2db1d009-6467-45a5-a1d6-25f4a3fd29b6">

2. DISPLAY Operation

<img width="474" alt="image" src="https://github.com/NouvBaLQahtani/introduction-to-database-systems-Project-IS230/assets/106460665/901c5c26-d3e7-4f71-bee3-b5b4d70d3cac">

3. EXIT operation

<img width="474" alt="image" src="https://github.com/NouvBaLQahtani/introduction-to-database-systems-Project-IS230/assets/106460665/797ecf49-2373-4b9b-ac9f-62a1d33d28fa"> 



## Exceptions
The program handles the following exceptions:

- `SQLException`: Displayed when there is an issue with the database connection or SQL query execution.
- `InputMismatchException`: Displayed when the user input does not match the expected data type.
- `Exception`: Displayed for any other unexpected exceptions that may occur during program execution.
  you can see different test cases in the provided report 


## Contact

If you have any questions, feedback, or inquiries regarding this project, please feel free to reach out to me through my Email: NouvAlqahtani@gmail.com .
