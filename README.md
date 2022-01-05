# Monopoly17
Milestone 4 <br>
Date: Dec. 6th, 2021

------------------------------------------------------------------------
## Contact Information

### Contact Name:		
* Trong Nguyen
* Francisco De Grano
* Ibrahim Almalki
* Elisha Catherasoo

### Affiliation: 		
- Carleton University - Systems and Computer Engineering
- SYSC 3110 - Software Development Project

------------------------------------------------------------------------
## Description

![Monopoly](https://user-images.githubusercontent.com/55768917/142789708-f3411433-8c5e-487d-9292-2fc26081c0db.gif)

- Monopoly17 is a reproduction of the simplified version of the classic 
board game Monopoly. This release is a text-based and in a graphical 
user-interface playable version of the game built on Java using Swing.

Source code file dependencies:

monopoly17 package:
* CPUPlayer.java
* Dice.java
* DiceGUI.java
* GameBoard.java
* GameBoardGUI.java
* GameState.java
* HumanPlayer.java
* Inactive.java
* Input.java
* Jail.java
* JsonParse.java
* Monopoly.java
* MonopolyGUI.java
* MonopolyInitGUI.java
* Player.java
* PlayerGUI.java
* Property.java
* Railroad.java
* RollDice.java
* Square.java
* SquareGUI.java
* Taxes.java
* Utility.java

verison package:
* UK.json
* US.json

test package:
* HumanPlayerTest.java
* JailTest.java
* MonopolyTest.java
* PropertyTest.java
* RailroadTest.java
* TaxesTest.java
* UtiliyTest.java

Execution source:
* monopoly17.MonopolyInitGUI

The application is supported with various documentation and diagrams 
upon deliverable:

* M4_Group17-ClassUML.drawio.pdf (Class UML diagram)
* M4_Group17-Documentation.pdf	(Documentation)
* M4_Group17-SequenceUML_1.drawio.pdf 
	(Sequence UML for User Saves the Current Game State)
* M4_Group17-SequenceUML_2.drawio.pdf 
	(Sequence UML for User Selects New Game)
* M4_Group17-SequenceUML_3.drawio.pdf 
	(Sequence UML for Loading International Version)
* M4_Group17-SequenceUML_4.drawio.pdf 
	(Sequence UML for User Loads Saved Game)

------------------------------------------------------------------------
## Installation

This program has been tested for Windows 10 and Mac OS Big Sur v11.5.2

------------------------------------------------------------------------
## Dependencies 

The application should work with the most recent version of Java 
programming language update with JDK 17.0.1. 

Monopoly17 has been compiled by a more recent version of the Java 
Runtime (class file version 60.0).

Required External Libraries:
* JDK 
	- openjdk-17.0.1
* JUnit4
	- hamcrest-core-1.3
	- junit-4.13.1
* Maven 
	- jackson-annotations-2.13.0
	- jackson-core-2.13.0
	- jackson-databind-2.13.0
	- junit-4.13.1
	- hamcrest-core-1.3

------------------------------------------------------------------------
## Configurations 

### Compiling

A JAR file is provided, hence the program may be executed directly 
opening the JAR file. Or using the following Windows command prompt, if 
the jar file directory path is known:

	java -jar C:\filepath\Monopoly.jar

Note that the Java Runtime environment must support class file version 
60.0. and that the Java compiler and runtime environment must be the 
same version.

### Running

Once the program is executed the user will be prompted with a game 
start menu screen, The menu will prompt the user to start game which 
then allows the user play the graphically supported Monopoly game. In 
addition the user will be able to see the Monopoly GUI in action. 

------------------------------------------------------------------------
## Monopoly17 Gameplay

### Version 4.0 (Latest Iteration)
In the latest update of the project, we implemented the load/save 
features such that users are able to save the current game state. Then
load a previously saved game state into play. The second feature added
was introducing international verison of the game with customized
square names.

#### Save/Load Feature

Using Java Serialization, we were able to store the state of the game
into a serializable object. Then was done via storing the various
objects in an arraylist which serialized to bytecode store in a .txt 
file. The GUI enabled the user to select a load menu item which restores
the previously saved game session.

#### International Version Customization

Using Jackson JSON parsing, we were able to store the unique names of 
the square contents to a JSON file which stores a specific version of
the game. Different JSON files represent the contents specific to
various internation or customized editions of the game with changes to
the name of the squares. We updated the GUI to display options for 
selecting a game version of choosing, which updates the version of the 
game state accordingly.

------------------------------------------------------------------------
### Version 3.0

#### Special Square

As stated, Monopoly17 is a simplified version of the classic board game 
Monopoly. The games does not have Chance/Community chest cards and this 
version does not allow for mortgages nor sales, auctions, and trades 
between players. Functionalities such as Jail, Utilities, Railroads, 
and purchasing of houses have been fully implemented in this release. 

#### CPU Player

The newest version of monopoly includes an option to introduce CPU 
players into the game. Simply enter a name for the CPU and click "Add 
CPU Player" on the menu page. When the game starts, simply click the 
"CPU Turn" button whenever it is the turn of the CPU, and it will
automatically roll the dice, move its position, buy properties, and
pay rent. At the end of the turn users will have a chance to see what
see what actions the CPU has performed by looking at the information 
panel. Finally, click the "Next Turn" button to move on to the next 
player. 

#### Buying Houses

For this state of the project, the game logic was implemented and 
testing via manual testing via command line console. Followed with 
implementation via the GUI, so far the user is only able to buy houses
for properties that they have a monopoly on. This feature is 
shown via the gameplay information console. We might future decide to
add house tokens onto the game board for future development.

------------------------------------------------------------------------
### Version 2.0

#### GUI-based version

GUI-based version of the game. Display must be in a JFrame, and user 
input is via the mouse.

------------------------------------------------------------------------
### Version 1.0

#### Text-based game via output console

A text-based playable version of the game, i.e., players should be able 
to play the game via the console using the keyboard. There should be a 
command to print the state of each player (where they are on the board, 
how much money they have, which properties they own), a command to buy 
the property they landed on (if available), and a command to pass your 
turn to the next player. Events such as landing on a property owned by 
another player (and therefore paying the indicated rent), the bankruptcy 
of a player, etc. should be printed to the console when applicable.

------------------------------------------------------------------------
## Acknowledgement

Thanks to the support of TAs and Instructors during the development of 
this project.

------------------------------------------------------------------------
## Disclaimer

Copyright disclaimer under section 107 of the Copyright Act 1976, 
allowance is made for “fair use” for purposes such as criticism, 
comment, news reporting, teaching, scholarship, education and research.

Fair use is a use permitted by copyright statute that might otherwise 
be infringing.

------------------------------------------------------------------------
## Appendix

![Board](https://user-images.githubusercontent.com/55768917/138033882-eb6323ed-ee3b-46a7-926f-5bbc4fec40ac.jpg)
![TitleDeeds](https://user-images.githubusercontent.com/55768917/138033890-277cdef8-7b8f-49a2-bb09-c44c7a9459c4.jpg)
