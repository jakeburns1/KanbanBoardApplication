# Kanban Board JavaFX Application


**This project is a virtual kanban board. It is modeled after online application "Trello" (trello.com).**

I built this project using JavaFX.

This application uses multiple common software design patterns and uses MVC.

Design document: https://docs.google.com/document/d/1HCnET28g79bfOJMg4Qs8C-6boNBXehNn_7HsRd7aRL0/edit?usp=sharing

**Features:**

The user can own and create kanban boards. In these boards you can add lists which contain cards. Cards have attributes such as a name, description,
checklist, and labels. You can add, delete, and move both cards and lists. There is also a filter feature where you can filter cards by the labels they contain. 




**Running the application for the first time**
1. Run the ServerTest.java file to initilize the server and create accounts. RMIREGISTRY is used for the server.
2. Run the MainStartup.java file to launch the application

**Account info:**
The default account:
  username: jakeburns
  password: centre1234

**Running the testing file:**
1. Run the TestTrello.java (located in src/test/java/views) TWICE. Make sure to run it TWICE the first time. Once to initialize the server if it is your first time running it.

