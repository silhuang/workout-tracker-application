# Pilates Workout Tracker Application

## Keep all your Pilates & workout choreo in one single app! 


The **Pilates Workout Tracker Application** will allow users to keep track of their favourite workout on a single app. 
When users create a new workout, they can add as many tracks as they wish to the workout. 
**Tracks** contain **different moves in a certain order (choreography)**, where each **move** is associated with a 
**name** and **number of reps**. Originally, this app was intended for Pilates instructors to record the choreography 
of every track their chosen workout in one place, but it can also be used by anyone who would like to 
record their workout in a single application. _This project is of interest to me because I was previously a 
POP Pilates instructor with the UBC Pilates Club_. As I learned different POP choreographies (i.e. workouts), 
I wrote down the moves of every track inside a notebook, but thought it would be more convenient to store 
the information I recorded inside an app that I can access anytime.

**User Stories:**

- As a user, I can add as many tracks as I want to a workout
- As a user, I can add as many moves as I want to any given track
- As a user, I can delete any previously added
  - tracks from a workout
  - moves from a track
  
- As a user, I want to be able to view all the tracks recorded so far my workout
- As a user, I want to be able to view the order and reps of moves in any track

**Data Persistence**
- As a user, when I select "Quit application" from the main menu, I want to be given the option of 
  whether I would like to save my newly created workout to file
- As a user, when I start the application, I have the option of loading my previous workout from file
  by clicking "Load saved workout" from the main menu

## Instructions for Grader

- **Visual Component:** When the application is run, a main menu will pop up first, which will include the visual component, 
a cartoon GIPHY doing an abdominal exercise.
- Next, click on the "Create New Workout" button right below the GIPHY, and enter a title for the new workout you will create.


- **Adding Tracks to the Workout & Moves to Tracks:** A new window will open, showing a workout viewer with no tracks added yet. Click on "Add new track" to add a 
new track the workout. Select a track and click "Add new move" to add a new move to the selected track. All tracks
added so far will be displayed on the left scrollPane, and each time you select a different track, the list of moves, located
on the right scrollPane, will update to show all the moves of the newly selected track. 


- **Deleting Tracks from the Workout and Moves From Tracks:** You can also delete any track of your choice by selecting a track, then clicking on the "Delete track" button. Similarly,
you are also able to delete any move(s) from any selected track by selecting the move you would like to delete, then
clicking on the "Delete move" button.


- **Saving:** Once you are done adding all tracks and associated moves to your workout, click on the small red "x" icon in the 
top left corner of the frame. A separate popup window will appear on the screen asking if you would like to save your workout.
Click "Yes" to save the workout you have just created. Another popup window will appear confirming that the workout has
been saved to file. Click "Ok" to close the application.


- **Loading:** Open the application again, and click on "Load Saved Workout". A popup window will indicate that the saved workout 
has been loaded from file. Click "Ok", and you should now be able to see all the tracks and moves you added to your previous workout
on the workout viewer window that has appeared on screen.

## Reflection

- Overall, I am quite satisfied with the structure of my program, but if I were to refactor it,
the Workout class would have a HashMap of Tracks, where each key is a Track and the corresponding value
is the collection of moves of that track. This would be a much more suitable data structure for
storing data on a workout's tracks and their associated moves, rather than an ArrayList of Track. 
This way, I can also add/remove moves to any track in addition to tracks from the Workout class directly, allowing
for a more compact design. 
