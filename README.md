# MortgagePlanCalculator
Unlike simplified calculators, this application separates principal, interest, administration fees, and establishment fees, making it easier to see how much value is lost to fees versus actual debt reduction.
![Loan Repayment Calculator interface](https://i.imgur.com/DPszulM.png)
![Loan Repayment Plan print](https://i.imgur.com/lq6YvWd.png)

### Key features
- Supports fixed repayment period or fixed monthly payment
- Handles equity (down payment) before interest calculations
- Explicit separation of:
  - Principal repayment
  - Interest payment
  - Monthly administration fees
  - One-time establishment fee
- Optional plan limiting
  - Stop after N months
  - Stop when remaining loan reaches a defined amount
- Full month-by-month repayment table
- Summary of:
  - Total interest paid
  - Total fees paid
  - Total amount paid

### Architecture overview
The application is structured with clear responsibility separation:
- App.java<br>
  Application entry point
- Window.java<br>
  JavaFX UI logic, input handling, plan visualization, and navigation
- Calculations.java<br>
  Core repayment logic, amortization, fee handling, and plan limits
- MandatoryValues.java<br>
  Immutable values required for every plan<br>
  (loan amount, interest rate, fees, plan name)
- InterchangeableValues.java<br>
  User-controlled variables that affect plan behavior<br>
  (repayment time, fixed payment, limits, equity)
- InputValues.java<br>
  Container connecting mandatory and interchangeable inputs

### Design philosophy
The goal of this project is clarity over abstraction:
- Fees are never hidden inside interest
- Monthly payments reflect what the borrower actually pays
- Calculations are explicit rather than “financial-black-box”

This makes the tool suitable for education, comparison, and analysis, not just estimation.

## Quick start
### 1. Clone the repository
```
git clone https://github.com/EliasKasapsen/Loan-Repayment-Plan-Calculator-JavaFX.git
cd Loan-Repayment-Plan-Calculator-JavaFX
```
### 2. Compile the project
Make sure you have Java 17 (or your version) installed. Then run:
```
javac -d bin src/main/java/com/example/*.java
```
`-d bin` creates a `bin` folder for compiled classes.

### 3. Run the application
```
java -cp bin com.example.App
```
### Folder Structure
```
demo/
├─ src/
│    └─ main/java/com/example/
│        ├─ App.java
│        ├─ Calculations.java
│        ├─ InputValues.java
│        ├─ InterchangeableValues.java
│        ├─ MandatoryValues.java
│        └─ Window.java
├─ bin/  ← compiled classes will appear here
├─ README.md
└─ .gitignore
```
