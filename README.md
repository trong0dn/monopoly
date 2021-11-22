# Monopoly17
Milestone 3 <br>
Date: Nov. 22nd, 2021

------------------------------------------------------------------------
## Contact Information

### Contact Name:		
* Trong Nguyen - 100848232
* Francisco De Grano - 101147447
* Ibrahim Almalki - 101142978
* Elisha Catherasoo - 101148507

### Affiliation: 		
- Carleton University - Systems and Computer Engineering
- SYSC 3110 - Software Development Project

------------------------------------------------------------------------
## Description

![Monopoly](https://user-images.githubusercontent.com/55768917/142789708-f3411433-8c5e-487d-9292-2fc26081c0db.gif)

- Monopoly17 is a reproduction of the simplified version of the classic 
board game Monoopoly. This release is a text-based and in a graphical 
user-interface playable version of the game built on Java using Swing.

Source code file dependencies:

monopoly17 package:
* CPUPlayer.java
* Dice.java
* DiceGUI.java
* GameBoard.java
* GameBoardGUI.java
* HumanPlayer.java
* Inactive.java
* Input.java
* Jail.java
* Monopoly.java
* MonopolyController.java
* MonopolyGUI.java
* Player.java
* PlayerGUI.java
* Property.java
* RollDice.java
* Square.java
* SquareGUI.java
* Taxes.java
* Utility.java

test package:
* HumanPlayerTest.java
* MonopolyTest.java
* PropertyTest.java

Execution source:
* monopoly17.MonopolyGUI

The application is supported with various documentation and diagrams 
upon deliverable:

* M3_Group17-ClassUML.drawio.pdf (Class UML diagram)
* M3_Group17-Documentation.pdf	(Documentation)
* M3_Group17-SequenceUML_1.drawio.pdf (Sequence UML for Creating Player)
* M3_Group17-SequenceUML_2.drawio.pdf (Sequence UML for Buying Property)
* M3_Group17-SequenceUML_3.drawio.pdf (Sequence UML for Player Position)
* M3_Group17-SequenceUML_4.drawio.pdf (Sequence UML for Player Turn)

------------------------------------------------------------------------
## Installation

This program has been tested for Windows 10 and Mac OS Big Sur v11.5.2

------------------------------------------------------------------------
## Dependencies 

The application should work with the most recent version of Java 
programming language update with JDK 16.0.2. 

No other supplementary libraries and testing file with modular 
dependencies are required to run this application.

Monopoly has been compiled by a more recent version of the Java 
Runtime (class file version 60.0).

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
## Monopoly17 gameplay

### Modifications to the current version

As stated, Monopoly17 is a simplified version of the classic board game 
Monopoly. The games does not have Chance/Community chest cards and this 
version does not allow for mortgages nor sales, auctions, and trades 
between players. Functionalities such as Jail, Utilities, Railroads, 
and purchasing of houses have been fully implemented in this release. 

### CPU Player

The newest version of monopoly includes an option to introduce CPU 
players into the game. Simply enter a name for the CPU and click "Add 
CPU Player" on the menu page. When the game starts, simply click the 
"CPU Turn" button whenever it is the turn of the CPU, and it will
automatically roll the dice, move its position, buy properties, and
pay rent. At the end of the turn users will have a chance to see what
see what actions the CPU has performed by looking at the information 
panel. Finally, click the "Next Turn" button to move on to the next 
player. 

### Winning the game

To win the current Monopoly17 games, the remain player to not run out 
of money or is not bankrupt will be the winner. As players lose in the 
game, they will be removed from the game.

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
