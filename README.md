# Słowobot / Wordmaster (work in progress)
Main target of this program is to automatically solve 4x4 grids in Android game "Słowotok" (also avaliable on desktop browsers). 

## How the game works?

Each round all on-line players have 1 minute and 40 seconds to find as many words from a 4x4 grid (same for everyone). Correct answer is a Polish word in it's basic form. To find each word you need to make a correct path (slide your finger) from the first letter, through the neighbours (you can go horizontally, vertically and diagonally) to the last letter. Minimum word length equals 3 and maximum equals 15.


## What's working?
- adding words to dictionary (a file containing around 2.7 million Polish words takes about 7-10 seconds to load on Windows/4 cores CPU, and eats around 750mb of RAM memory)
- checking if word exists in dictionary (single check takes around 0.1 ms thanks to tree implementation)
- solving 4x4 grids (takes 5-35 ms depending on how many words exists in grid)
- image recognition (scans an image and returns all 16 letters)


## What I'm working on?
- learning android studio, programming on android to make my first android app
- implementing auto-solving (right now my output is a list of words and for each word also a corresponding path - "an instruction" how to make each word. Next step is to implement a method that will translate each instruction into touch input)

### Personal thoughts / actual problems
- first attempts to run my app on android resulted in memory exceptions (my program, especially dictionary, eats too much RAM)
- to solve memory problem I'm thinking of:
  - deleting some of the words from word bank (my file contains not only words in their basic form, but it's not easy to automatically determine if given word is in its basic form and I would have to load only 1.5 million words (delete 1.3 million so almost a half) for the app to start)
  - another way of representing a dictionary (tree takes a bit of space but is extremely quick, actually too quick)

### Firsts tests (running on pc and sending input through adb to my phone)
[youtube link](https://youtu.be/ODCfISpU4Hk)
