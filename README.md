
# Wordle Game

A command-line version of the popular game **Wordle**, written in Java.

Players have **5 attempts** to guess a randomly selected **5-letter word**. After each guess, the game provides feedback using color-coded statuses:

- **GREEN** – Correct letter in the correct position
- **YELLOW** – Letter exists in the word but in the wrong position
- **BLACK** – Letter is not in the word at all

---

## Features

- 5-letter word guessing
- Feedback after each guess
- Handles duplicate letters correctly
- Reads word list from a file
- Includes test cases for validation logic

---

## How It Works

- The game selects a word randomly from `dictionary.txt`.
- The user is prompted to guess a 5-letter word.
- The game responds with feedback using `Status`:
  - `GREEN` – Correct letter and position
  - `YELLOW` – Correct letter, wrong position (with count handling)
  - `BLACK` – Letter not in word
- Ends when the word is guessed or attempts run out.

---

## Prerequisites

- Java 8 or above
- Maven (for building and testing)

