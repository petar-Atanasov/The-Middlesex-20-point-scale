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
- ### `Discretion
---
## Key Concepts Implemented
### 1. Grade validation
The ### `Grade` constructor rejects values outside the valid 1-20 range/

### 2. Percentage-to-grade conversion
### `Grade.fromPercentage(int)` converts a percentage into an MDX grade. In this implementation:
- ### `70+` maps to grade ### `1`
