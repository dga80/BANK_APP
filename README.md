
# Bank app

This project is an individual task where you'll build a banking system that has four account types - StudentChecking, Checking, Savings, and CreditCard - and three user types - Admins, AccountHolders, and ThirdParty.

## Features

AccountHolders can access their own accounts and transfer money between their accounts. Admins can create new accounts and modify the balance of any account. ThirdParty users can send and receive money to other accounts after being added to the database by an admin. The system applies penalties, interest, and fees appropriately.

## Running Tests

All tests are in "BankControllerTest.java"

AccountHolder:
- Make Transaction

Admin:
- UpdateBalance
- Create ThirdParty
- Create SavingAccount
- Create CreditAccount
- Create Checking/StudentAccount
- Make Transaction

ThirdParty:
- Make transaction


## Tech Stack

• Maven/ Java 17/ Spring Boot / Spring Data JPA / Spring Validation/ Spring Web / Spring Boot DevTools / MySQL Connector / Spring Boot Starter Test

## Run Locally

Clone the project

```bash
https://github.com/dga80/BANK_APP.git

## Authors

- @dga80

## Acknowledgements

 - Jaume Sanchez (teacher)
 - Jose Caro (TA)
 - Alejandro Martinez (TA)
 - Ironhack Barcelona Tech bootcamps
