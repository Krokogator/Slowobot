# Słowobot / Wordmaster (work in progress)
Main target of this program is to automatically solve 4x4 grids in Android game "Słowotok" (game is also available on desktop browsers). 

## How the game works?

Each round all on-line players have 1 minute and 40 seconds to find as many words from a 4x4 grid (same for everyone). Correct answer is a Polish word in it's basic form. To find each word you need to make a correct path (slide your finger) from the first letter, through the neighbours (you can go horizontally, vertically and diagonally) to the last letter. Minimum word length equals 3 and maximum equals 15.

## How my app works? (right now)

Ok so currently we need two devices. One - a PC, second - android device (can be emulated). Android device runs game app, while my app runs on PC obtaining letter grid from device, finding all solutions (words and corresponding paths on the grid), converting them into touch inputs and sending them back to android device.

## What's working? (actually everything, just not the android only version)
- adding words to dictionary
- image recognition (scans an image and returns all 16 letters)
- checking if word exists in dictionary (single check takes around 0.1 ms thanks to tree implementation)
- solving 4x4 grids (takes 5-35 ms depending on how many words exists in grid)


## What I'm working on?
- making my app fully automatic (so it can detect start/end of each round)
- making my project "android only" (no PC required) 

### Personal thoughts
- tree structure is one of the best when creating a dictionary. However might use a lot of RAM.
- android programming (especially when diving deep into bot like programs) isn't that easy :)
- sending touch inputs through adb on android 7.X Nougat seems extremely slow (6.X runs fine)

### Actual problems (mostly android related)
- sending touch inputs from android app
- running app in the background? (so it can take screenshots of the game, and send touch inputs)



### Firsts tests (running on pc and sending input through adb to my phone)
[youtube link](https://youtu.be/ODCfISpU4Hk)
