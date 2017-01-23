#SuperUserBugReporter

This is the crash reporter for our game. One of the requirements is to have a way the user can email us if the game crashes.

What this does:


1) Upon crashing, user is presented with a pane for them to enter their email address and what they did to cause the crash.

2) Whenever the user presses submit, a few things happen. The email address and contents is written to a text file, and the user
is presented with a beautiful conformation box (thanks JOptionPane for FREEZING every time I tried calling you).. 

3) When the confirm button is clicked, the crash reporter attempts to create and send an email with the contents to the email
entered in the crash reporter and to the developer email account. 

