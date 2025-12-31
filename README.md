# Mortgage Plan Calculator
Unlike simplified calculators, this application separates principal, interest, administration fees, and establishment fees, making it easier to see how much value is lost to fees versus actual debt reduction. The choice to choose Java as the programming language was due to interest in increasing width in code knowledge and taking advantage of JavaFX for UI development.
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

## Prerequisites
To run this project, you need to have the following installed:
- Java JDK 23 (or higher)
- Maven (build automation tool)

### Installation
Clone the repository:
```
git clone https://github.com/EliasKasapsen/Mortgage-Plan-Calculator.git
cd Mortgage-Plan-Calculator
```
Verify your Java version 23:
```
java -version
```

### How to run
Using VS Code (Recommended)
1. Open the folder in VS Code.
2. Install the Extension Pack for Java.
3. Open App.java.
4. Click the Run button above the main method.

### Folder Structure
```
mortgage_plan_calculator
├── README.md
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   └── mortgage_plan_calculator
│   │   │   │       ├── App.java
│   │   │   │       ├── Calculations.java
│   │   │   │       ├── InputValues.java
│   │   │   │       ├── InterchangeableValues.java
│   │   │   │       ├── MandatoryValues.java
│   │   │   │       └── Window.java
│   │   │   └── module-info.java
│   │   └── resources
│   │       └── com
│   │           └── mortgage_plan_calculator
│   │               ├── primary.fxml
│   │               └── secondary.fxml
│   └── test
│       └── java
└── target
    ├── classes
    │   ├── com
    │   │   └── mortgage_plan_calculator
    │   │       ├── App.class
    │   │       ├── Calculations.class
    │   │       ├── InputValues.class
    │   │       ├── InterchangeableValues.class
    │   │       ├── MandatoryValues.class
    │   │       ├── Window.class
    │   │       ├── primary.fxml
    │   │       └── secondary.fxml
    │   └── module-info.class
    └── test-classes

16 directories, 20 files 
```
