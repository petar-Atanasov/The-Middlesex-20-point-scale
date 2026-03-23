# The Middlesex 20 Point Scale

## Project Overview
This project implements a small Java system for working with the **Middlesex University 20-point grading scale**. It models grades, academic profiles, and degree classifications, and uses **JUnit 5 testing** to verify that each rquired task behaves correctly.
---
## Project Purpose
The main purpose of the project is not only to build the grading logic, but to understand the importance of **unit testing** by writting test for each required feature from the project brief. The repository shows how to test constructors, boundary conditions, ivalid inputs, equivalance classes, and classification rules in a structured way.

The goal of this project is to demonstrate:
- how the Middlesex 20-point scale can be represented in code
- how percentage marks can be converted into MDX grade points
- how module profiles and full degree results can be classified
- how **JUnit testing** can be used to validate both normal behaviour and edge cases

### This makes the project a good example of test-driven academic software development, where correctness matters as much as implementation.
---
## What the Project Does
The system is split into a few simple Java classes:
### `Grade`
Represents one grade on the Middlesex 20-point scale. A valid grade must be between 1 and 20. it also supports:
- returning the numeric point value
- classifying the grade into degree bands
- converting a precentage mark into a MDX grade point

### `Profile`
Represents a collection of grades and detemines:
- the overall classification of that profile
- whether the profile is considered clear or not clear

### `Degree`
Represents year 2 and year 3 profiles and calculates the final degree classification using the profile result and discretion rules.

### `Classification`
An enum that defines the possible outcomes:
- ### `First`
- ### `UpperSecond`
- ### `LowerSecond`
- ### `Third`
- ### `Fail`
- ### `Discretion`
---
## Key Concepts Implemented
### 1. Grade validation
The `Grade` constructor rejects values outside the valid 1-20 range.

### 2. Percentage-to-grade conversion
### `Grade.fromPercentage(int)` converts a percentage into an MDX grade. In this implementation:
- ### `70+` maps to grade ### `1`
- ### `60+` maps to ### `2`
- ### `50+ maps to ### `3`
- ### `40+` maps to ### `4`
- below `40` maps to  `5`
- ### `-1` is treated as non-participation and returns ### `20`

### 3. Grade classification
A grade point is mapped to classification band:
- ### `1-4` --> First
- ### `5-8` --> Upper Second
- ### `9-12` --> Lower Second
- ### `13-16` --> Third
- ### `17-20` --> Fail

### 4. Profile classificaiton 
### `Profile` analyses a list of grades and classifies it based on how many grades fall into the higher bands. It also checks whether a profile is clear, especially for stronger classifications such as First and Upper Second.

### 5. Degree classification
### `Degree` compares year 2 and year 3 profiles. If both years have the same classifcation, that result is awarded, If one year is one class above the other and clear, the higher result is awarded. Otherwise, the result becomes ### `Dicretion`.
---
## Testing Methods Used
### Boundary Value Testing
The test check values at and around important limits, such as:
- valid grade boundaries: ### `1` and ### `20`
- invalid grade boundaries: ### `0` and ### `21`
- valid percentage boundaries: ### `-1`, ### `0` and ### `100`
- invalid percentage boundaries: ### `-2` and ### `101`

### Equivalence Class Testing
The testing group inputs into meaningfull categories and verify one or more representative examples from each class. For example:
- grade classifications are tested across First, Upper Second, Lower Second, Third and Fail
- percentage conversion is tested across the different grade bands
- profile and degree classifications are tested across the main classification outcomes

### Exception Testing
The test suite uses ### `assertThrows` to ensure invalid inputs correctly raise ### `IllegalArgumentException`.
### Parametirised Testing 
The project uses JUnit 5 parametirised tests with ### `@ValueSource` and ### `@MethodSource` to reduce repetition and cover many classes cleanly.
---
## How to Run the Project
Compile the JAva files and run the JUnit tests in your IDE or build tool enviroment. The repository structure suggests a simple JAva project layout with source files and test files in ### `src`/

if you use an IDE such as Exlipse or IntelliJ, import the project and run:
- ### `GradeTest`
- ### `ProfileTest`
- ### `DegreeTest`

### These tests verify the behaviour of the entire grading system.
