package com.mortgage_plan_calculator;
import java.util.HashMap;
import java.util.UUID;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Window extends Application
{
    private static final String FONT_SIZE_14 = "-fx-font-size: 14px;";

    private static final String LABEL_MAIN_TITLE = "Loan Repayment Plan Calculator";
    private static final String LABEL_DESCRIPTION = "The program calculates a repayment plan";
    private static final String LABEL_CREATED_PLANS = "Created plans:";
    private static final String LABEL_NO_PLANS = "No plans calculated";
    private static final String LABEL_PLAN_NAME = "Plan name";
    private static final String LABEL_LOAN_AMOUNT = "Loan amount";
    private static final String LABEL_EQUITY = "Equity";
    private static final String LABEL_INTEREST_RATE = "Interest";
    private static final String LABEL_ESTABLISHMENT_FEE = "Establishment fee";
    private static final String LABEL_ADMIN_FEE = "Administration fee";
    private static final String LABEL_TIME_OR_PAYMENT = "Choose between repayment time and total monthly repayment:";
    private static final String LABEL_REPAYMENT_PERIOD = "Set repayment time";
    private static final String LABEL_MONTHLY_TOTAL_PAYMENT = "Set total monthly repayment";
    private static final String LABEL_LIMIT_REVEAL = "Options for limiting plan";
    private static final String LABEL_LIMIT_WITH_TIME = "Set number of months";
    private static final String LABEL_LIMIT_WITH_LOAN_AMOUNT = "Set remaining loan amount";

    private static final String PROMPT_ENTER_NAME = "Enter name";
    private static final String PROMPT_ENTER_AMOUNT = "Enter amount";
    private static final String PROMPT_ENTER_INTEREST_RATE = "Enter rate (X.XXX)%";
    private static final String PROMPT_ENTER_MONTHS = "Enter in months";
    
    private final HashMap<String, Calculations> mPlans;
    private static final String STYLE_ACTIVE = "-fx-background-color: #45a049; -fx-text-fill: white; -fx-font-weight: bold;";
    private static final String STYLE_INACTIVE = "";
    
    public Window()
    {
        mPlans = new HashMap<>();
    }

    private void toggleButtonStyles(Button active, Button inactive) {
        active.setStyle(STYLE_ACTIVE);
        inactive.setStyle(STYLE_INACTIVE);
    }

    // JavaFX GUI methods
    @Override
    public void start(Stage primaryStage) {
        // Title and subtitle label
        Label titleLabel = new Label(LABEL_MAIN_TITLE);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");
        Label subtitleLabel = new Label(LABEL_DESCRIPTION);
        subtitleLabel.setStyle("-fx-font-size: 16px; -fx-alignment: center;");
        
        // VBox to hold the list of created plans
        VBox planListBox = new VBox(10);
        planListBox.setPadding(new Insets(10));

        Label planListLabel = createLabel(LABEL_CREATED_PLANS);

        // Placeholder label for "No plans calculated"
        Label noPlansLabel = new Label(LABEL_NO_PLANS);
        noPlansLabel.setStyle("-fx-font-style: italic; -fx-text-fill: gray;"); // Styling to make it visually distinct
        
        planListBox.getChildren().addAll(planListLabel, noPlansLabel); // Add it initially

        // Create input labels and fields
        Label planNameLabel = createLabel(LABEL_PLAN_NAME);
        TextField planNameField = createTextField(PROMPT_ENTER_NAME);

        Label loanAmountLabel = createLabel(LABEL_LOAN_AMOUNT);
        TextField loanAmountField = createTextField(PROMPT_ENTER_AMOUNT);

        Label equityLabel = createLabel(LABEL_EQUITY);
        TextField equityField = createTextField(PROMPT_ENTER_AMOUNT);

        Label interestLabel = createLabel(LABEL_INTEREST_RATE);
        TextField interestField = createTextField(PROMPT_ENTER_INTEREST_RATE);

        Label establishmentFeeLabel = createLabel(LABEL_ESTABLISHMENT_FEE);
        TextField establishmentFeeField = createTextField(PROMPT_ENTER_AMOUNT);

        Label adminFeeLabel = createLabel(LABEL_ADMIN_FEE);
        TextField adminFeeField = createTextField(PROMPT_ENTER_AMOUNT);

        // HBox to make the buttons appear next to each other
        HBox buttonsBox = new HBox(10);  // 10px spacing between buttons

        // Use a VBox to arrange the inputs below their titles
        VBox repaymentBox = new VBox(10);
        
        // Label above buttons to explain what the user should choose
        Label chooseLabel = createLabel(LABEL_TIME_OR_PAYMENT);
        
        Button setRepaymentTimeButton = new Button(LABEL_REPAYMENT_PERIOD);
        TextField repaymentTimeField = createTextField(PROMPT_ENTER_MONTHS);

        Button setFixedMonthlyRepaymentButton = new Button(LABEL_MONTHLY_TOTAL_PAYMENT);
        TextField fixedRepaymentField = createTextField(PROMPT_ENTER_AMOUNT);

        // VBox to arrange the button and field vertically
        VBox userDefinedOptionsBox = new VBox(10);

        // HBox to arrange the two buttons horizontally
        HBox customPlanButtonsBox = new HBox(10); // 10px spacing between buttons

        // Main button to reveal user-defined options
        Button userDefinedPlanButton = new Button(LABEL_LIMIT_REVEAL);

        // Sub-buttons for specific customizations
        Button setTimeLimitButton = new Button(LABEL_LIMIT_WITH_TIME);
        TextField timeLimitField = createTextField(PROMPT_ENTER_MONTHS); // Input fields for the user-defined limits

        Button setLoanAmountLimitButton = new Button(LABEL_LIMIT_WITH_LOAN_AMOUNT);
        TextField loanAmountLimitField = createTextField(PROMPT_ENTER_AMOUNT);

        // Set width size on input field
        planNameField.setPrefWidth(150);
        planNameField.setMaxWidth(150);
        loanAmountField.setPrefWidth(150);
        loanAmountField.setMaxWidth(150);
        equityField.setPrefWidth(150);
        equityField.setMaxWidth(150);
        interestField.setPrefWidth(150);
        interestField.setMaxWidth(150);
        establishmentFeeField.setPrefWidth(150);
        establishmentFeeField.setMaxWidth(150);
        adminFeeField.setPrefWidth(150);
        adminFeeField.setMaxWidth(150);
        setRepaymentTimeButton.setPrefWidth(170);
        setRepaymentTimeButton.setMaxWidth(170);
        setFixedMonthlyRepaymentButton.setPrefWidth(170);
        setFixedMonthlyRepaymentButton.setMaxWidth(170);
        repaymentTimeField.setPrefWidth(150);
        repaymentTimeField.setMaxWidth(150);
        fixedRepaymentField.setPrefWidth(150);
        fixedRepaymentField.setMaxWidth(150);
        userDefinedPlanButton.setPrefWidth(350);
        userDefinedPlanButton.setMaxWidth(350);
        setTimeLimitButton.setPrefWidth(170);
        setTimeLimitButton.setMaxWidth(170);
        timeLimitField.setPrefWidth(150);
        timeLimitField.setMaxWidth(150);
        setLoanAmountLimitButton.setPrefWidth(170);
        setLoanAmountLimitButton.setMaxWidth(170);
        loanAmountLimitField.setPrefWidth(150);
        loanAmountLimitField.setMaxWidth(150);

        // Initially hide the sub-buttons
        repaymentTimeField.setVisible(true); // Initially, show repayment time input
        fixedRepaymentField.setVisible(false);
        userDefinedOptionsBox.setVisible(false);
        userDefinedOptionsBox.setManaged(false); // Ensure it doesn't reserve space
        timeLimitField.setVisible(false);
        timeLimitField.setManaged(false);
        loanAmountLimitField.setVisible(false);
        loanAmountLimitField.setManaged(false);
        
        // --- MAIN CHOICE BUTTONS ---
        setRepaymentTimeButton.setStyle(STYLE_ACTIVE);
        setFixedMonthlyRepaymentButton.setStyle(STYLE_INACTIVE);

        setRepaymentTimeButton.setOnAction(e -> {
            toggleButtonStyles(setRepaymentTimeButton, setFixedMonthlyRepaymentButton);
            fixedRepaymentField.clear();
            repaymentTimeField.setVisible(true);
            fixedRepaymentField.setVisible(false);
            repaymentBox.getChildren().setAll(repaymentTimeField); // Cleaner than clear/add
        });

        setFixedMonthlyRepaymentButton.setOnAction(e -> {
            toggleButtonStyles(setFixedMonthlyRepaymentButton, setRepaymentTimeButton);
            repaymentTimeField.clear();
            repaymentTimeField.setVisible(false);
            fixedRepaymentField.setVisible(true);
            repaymentBox.getChildren().setAll(fixedRepaymentField);
        });

        // --- OPTIONS TOGGLE BUTTON ---
        // 1. Initial Style (Standard/Inactive)
        userDefinedPlanButton.setStyle(STYLE_INACTIVE);

        // 2. Hover Logic: Only show light green if the menu is ALREADY open
        userDefinedPlanButton.setOnMouseEntered(e -> {
            if (userDefinedOptionsBox.isVisible()) {
                userDefinedPlanButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
            }
        });

        // 3. Exit Logic: Return to Dark Green if open, or Standard Gray if closed
        userDefinedPlanButton.setOnMouseExited(e -> {
            if (userDefinedOptionsBox.isVisible()) {
                userDefinedPlanButton.setStyle(STYLE_ACTIVE);
            } else {
                userDefinedPlanButton.setStyle(STYLE_INACTIVE);
            }
        });

        // 4. Toggle Logic
        userDefinedPlanButton.setOnAction(e -> {
            boolean isVisible = !userDefinedOptionsBox.isVisible();
            userDefinedOptionsBox.setVisible(isVisible);
            userDefinedOptionsBox.setManaged(isVisible);
            
            // Switch between Active (Green) and Inactive (Gray)
            userDefinedPlanButton.setStyle(isVisible ? STYLE_ACTIVE : STYLE_INACTIVE);
            
            if (!isVisible) {
                timeLimitField.clear();
                loanAmountLimitField.clear();
                // Reset sub-button visuals when hiding the menu
                setTimeLimitButton.setStyle(STYLE_INACTIVE);
                setLoanAmountLimitButton.setStyle(STYLE_INACTIVE);
                timeLimitField.setVisible(false);
                timeLimitField.setManaged(false);
                loanAmountLimitField.setVisible(false);
                loanAmountLimitField.setManaged(false);
            }
        });

        // Set initial styles for the sub-buttons
        setTimeLimitButton.setStyle(STYLE_INACTIVE);
        setLoanAmountLimitButton.setStyle(STYLE_INACTIVE);

        // --- SUB-OPTIONS BUTTONS ---
        setTimeLimitButton.setOnAction(e -> {
            toggleButtonStyles(setTimeLimitButton, setLoanAmountLimitButton);
            loanAmountLimitField.clear();
            timeLimitField.setVisible(true);
            timeLimitField.setManaged(true);
            loanAmountLimitField.setVisible(false);
            loanAmountLimitField.setManaged(false);
        });

        setLoanAmountLimitButton.setOnAction(e -> {
            toggleButtonStyles(setLoanAmountLimitButton, setTimeLimitButton);
            timeLimitField.clear();
            loanAmountLimitField.setVisible(true);
            loanAmountLimitField.setManaged(true);
            timeLimitField.setVisible(false);
            timeLimitField.setManaged(false);
        });

        // Add elements to the layout
        buttonsBox.getChildren().addAll(setRepaymentTimeButton, setFixedMonthlyRepaymentButton);
        repaymentBox.getChildren().add(repaymentTimeField);
        customPlanButtonsBox.getChildren().addAll(setTimeLimitButton, setLoanAmountLimitButton);
        userDefinedOptionsBox.setAlignment(Pos.TOP_LEFT);
        userDefinedOptionsBox.getChildren().addAll(customPlanButtonsBox, timeLimitField, loanAmountLimitField);

        // Button to submit and calculate
        Button calculateButton = new Button("Calculate");

        // Apply styling with reduced padding for a more compact button
        calculateButton.setStyle(
            "-fx-background-color: #45a049; " + /* Greenish background */
            "-fx-text-fill: white; " + /* White text for contrast */
            "-fx-font-size: 14px; " + /* Maintain readable font size */
            "-fx-font-weight: bold; " +
            "-fx-padding: 2 8 2 8; " /* Smaller padding for a compact look */
        );
        calculateButton.setOnMouseEntered(e -> calculateButton.setStyle(
            "-fx-background-color: #4CAF50; " + /* Darker green on hover */
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 2 8 2 8; " /* Keep compact padding on hover */
        ));
        calculateButton.setOnMouseExited(e -> calculateButton.setStyle(
            "-fx-background-color: #45a049; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 2 8 2 8; "
        ));

        calculateButton.setOnAction(e -> {
            try {
                // Handle the click event here, process the inputs
                String planName = planNameField.getText();
                if (planName.isEmpty()) {
                    planName = "Plan " + (mPlans.size() + 1); // Default to "Plan X" if no name is provided
                }

                // Mandatory input values
                double loanAmount = Double.parseDouble(loanAmountField.getText());
                double interest = Double.parseDouble(interestField.getText());
                double establishmentFee = Double.parseDouble(establishmentFeeField.getText());
                double adminFee = Double.parseDouble(adminFeeField.getText());

                
                // Optional input values
                double equity = equityField.getText().isEmpty() ? 0.0 : Double.parseDouble(equityField.getText());
                int repaymentTime = repaymentTimeField.getText().isEmpty() ? 0 : Integer.parseInt(repaymentTimeField.getText());
                double fixedRepayment = fixedRepaymentField.getText().isEmpty() ? 0.0 : Double.parseDouble(fixedRepaymentField.getText());
                int customRepaymentTimeLimit = timeLimitField.getText().isEmpty() ? 0 : Integer.parseInt(timeLimitField.getText()); // Default value
                double customLoanAmountLimit = loanAmountLimitField.getText().isEmpty() ? 0.0 : Double.parseDouble(loanAmountLimitField.getText()); // Default value
                MandatoryValues mandatoryValues;
                InterchangeableValues interchangeableValues;
                InputValues inputValues;
                Calculations calculation;

                // Generate a unique identifier
                String planId = UUID.randomUUID().toString();

                mandatoryValues = new MandatoryValues(planName, loanAmount, interest, establishmentFee, adminFee);
                interchangeableValues = new InterchangeableValues(equity, repaymentTime, fixedRepayment, customRepaymentTimeLimit, customLoanAmountLimit);
                inputValues = new InputValues(mandatoryValues, interchangeableValues);
                calculation = new Calculations(inputValues);
                mPlans.put(planId, calculation);

                // Remove the placeholder label if it's present
                if (planListBox.getChildren().contains(noPlansLabel)) {
                    planListBox.getChildren().remove(noPlansLabel);
                }

                // Create a button for the new plan
                Button planButton = new Button(planName);
                planButton.setPrefWidth(200); // Set fixed width for the plan button
                planButton.setStyle(
                    "-fx-text-overrun: ellipsis; " + // Truncate text with ellipsis if it's too long
                    "-fx-alignment: CENTER_LEFT;"    // Align text to the left for better readability
                );

                // Set an action for the button to open the detailed plan window
                planButton.setOnAction(e0 -> showDetailedPlan(planId));
                
                // Add the button to the plan list
                planListBox.getChildren().add(planButton);

                // Create a red "Delete" button
                Button deleteButton = new Button("Delete");
                deleteButton.setPrefWidth(55); // Set fixed width for the delete button
                deleteButton.setStyle(
                    "-fx-background-color: #e23e32; " + /* Red background */
                    "-fx-text-fill: white; " + /* White text */
                    "-fx-font-size: 12px; " /* Slightly smaller font */
                );
                deleteButton.setOnMouseEntered(e1 -> deleteButton.setStyle(
                    "-fx-background-color: #f44336; " + /* Red background */
                    "-fx-text-fill: white; " + /* White text */
                    "-fx-font-size: 12px; " /* Slightly smaller font */
                ));
                deleteButton.setOnMouseExited(e2 -> deleteButton.setStyle(
                    "-fx-background-color: #e23e32; " + /* Red background */
                    "-fx-text-fill: white; " + /* White text */
                    "-fx-font-size: 12px; " /* Slightly smaller font */
                ));

                // Create an HBox to group the newPlanButton and deleteButton together
                HBox planContainer = new HBox(10); // 10px spacing between buttons
                planContainer.getChildren().addAll(planButton, deleteButton);
                // Associate the planId with the HBox
                planContainer.setUserData(planId);

                // Set an action for the delete button
                deleteButton.setOnAction(e3 -> {
                    // Retrieve the planId associated with this planContainer
                    String planIdToDelete = (String) planContainer.getUserData();

                    // Remove the plan container from the UI
                    planListBox.getChildren().remove(planContainer);

                    // Remove the corresponding entry from the HashMap
                    mPlans.remove(planIdToDelete);

                    // Check if there are no plans left
                    if (mPlans.isEmpty()) {
                        // Add the placeholder label back if no plans remain
                        planListBox.getChildren().add(noPlansLabel); // Add it back to the planListBox
                    }
                });

                // Add the planContainer (which is an HBox) to the planListBox
                planListBox.getChildren().add(planContainer);

                showDetailedPlan(planId);
            } catch (NumberFormatException f) {
                // Handle invalid input gracefully
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter valid numbers.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Update the main layout to include the plan list section
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.getChildren().addAll(
            titleLabel, subtitleLabel,
            planListBox, // Add the "Created Mortgage Plans:" label
            planNameLabel, planNameField,
            loanAmountLabel, loanAmountField,
            equityLabel, equityField,
            interestLabel, interestField,
            establishmentFeeLabel, establishmentFeeField,
            adminFeeLabel, adminFeeField,
            chooseLabel, buttonsBox, repaymentBox,
            userDefinedPlanButton, userDefinedOptionsBox, // Add the button to toggle custom plan options
            calculateButton // Add the calculate button first
        );

        // Wrap the VBox inside a ScrollPane to allow scrolling
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);
        scrollPane.setFitToWidth(true); // Optional: makes sure the content scales to the width of the ScrollPane
        scrollPane.setFitToHeight(false); // Optional: limits scrolling vertically (optional)

        // Set up the scene and stage
        Scene scene = new Scene(scrollPane, 800, 400);
        primaryStage.setTitle("LRPC");
        primaryStage.setScene(scene);

        // Enable non aggressive fullscreen mode
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    private void showDetailedPlan(String index) {
        // Create a new stage for the results
        Stage resultStage = new Stage();
    
        // Create a VBox to hold all the details
        VBox detailsBox = new VBox(10);
        detailsBox.setPadding(new Insets(20));
    
        // Add general details (loan amount, interest rate, etc.)
        if (mPlans.containsKey(index)) {
            Calculations calculations = mPlans.get(index);
            InputValues inputValues = calculations.getValues();
            MandatoryValues mandatoryValues = inputValues.getMandatoryValues();
            InterchangeableValues interchangeableValues = inputValues.getInterchangeableValues();
            
            resultStage.setTitle(mandatoryValues.getPlanName());

            Label loanAmountLabel = new Label(String.format("Loan amount: %.2f", interchangeableValues.getLoanAmountAfterEquity()));
            Label interestRateLabel = new Label(String.format("Interest rate: %.3f%%", mandatoryValues.getInterestRate()));
            Label establishmentFeeLabel = new Label(String.format("Establishment fee: %.2f", mandatoryValues.getEstablishmentFee()));
            Label adminFeeLabel = new Label(String.format("Administration fee: %.2f", mandatoryValues.getAdminFee()));
            Label repaymentPeriodLabel = new Label(String.format("Plan months: %d", interchangeableValues.getRepaymentPeriod()));
            Label totalMonthlyPaymentLabel = new Label(String.format("Total monthly payment: %.2f", interchangeableValues.getTotalMonthlyPayment()));

            // Add headers for the repayment table
            Label tableHeader = new Label("Repayment Plan (Month by Month):");

            detailsBox.getChildren().addAll(loanAmountLabel, interestRateLabel, establishmentFeeLabel, adminFeeLabel, repaymentPeriodLabel, totalMonthlyPaymentLabel, tableHeader);
    
            // Create a GridPane to hold the table-like data
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
    
            // Add headers to the grid
            grid.add(new Label("Month"), 0, 0);
            grid.add(new Label("Principal Payment"), 1, 0);
            grid.add(new Label("Interest Payment"), 2, 0);
            grid.add(new Label("Adminstration Fee"), 3, 0);
            grid.add(new Label("Total Payment"), 4, 0);
            grid.add(new Label("Remaining Loan"), 5, 0);
    
            // Add data rows
            int limit = interchangeableValues.getRepaymentPeriod();
            if (interchangeableValues.getRepaymentPeriodLimit() != 0)
            {
                limit = interchangeableValues.getRepaymentPeriodLimit();
            }
            for (int i = 0; i < limit; i++) {
                grid.add(new Label(String.valueOf(i + 1)), 0, i + 1);
                grid.add(new Label(String.format("%.2f", calculations.getMonthlyLoanRepaymentAtIndex(i))), 1, i + 1);
                grid.add(new Label(String.format("%.2f", calculations.getMonthlyInterestPaymentAtIndex(i))), 2, i + 1);
                grid.add(new Label(String.format("%.2f", mandatoryValues.getAdminFee())), 3, i + 1);
                grid.add(new Label(String.format("%.2f", calculations.getMonthlyTotalPaymentAtIndex(i))), 4, i + 1);
                grid.add(new Label(String.format("%.2f", calculations.getRemainingLoanAmountAtIndex(i))), 5, i + 1);
            }

            // Add the grid to the VBox
            detailsBox.getChildren().add(grid);
    
            // Add total summaries at the bottom
            Label totalInterestLabel = new Label(String.format("Total interest paid: %.2f", calculations.getTotalInterestPayment()));
            Label totalFeeLabel = new Label(String.format("Total fees paid: %.2f", calculations.getTotalFee()));
            Label totalPaidLabel = new Label(String.format("Total paid: %.2f", calculations.getTotalPayment()));
            detailsBox.getChildren().addAll(totalInterestLabel, totalFeeLabel, totalPaidLabel);
        } else {
            detailsBox.getChildren().add(new Label("Error: Invalid input or calculation data."));
        }
    
        // Wrap the VBox in a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(detailsBox);
        scrollPane.setFitToWidth(true);
    
        // Set up the scene and stage
        Scene resultScene = new Scene(scrollPane, 600, 400);
        resultStage.setScene(resultScene);
        resultStage.show();
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle(FONT_SIZE_14);
        return label;
    }

    private TextField createTextField(String promptText)
    {
        TextField field = new TextField();
        field.setPromptText(promptText);
        return field;
    }

   /* private <T extends Region> void handleSwitch(T component, boolean toggle)
    {
        component.setVisible(!toggle);
        component.setManaged(!toggle); // Add this line
        
        // Reset the visibility of input fields when hiding
        if (!toggle) {
            timeLimitField.clear();
            loanAmountLimitField.clear();

            timeLimitField.setVisible(false);
            timeLimitField.setManaged(false);
            loanAmountLimitField.setVisible(false);
            loanAmountLimitField.setManaged(false);
        }
    } */

    public static void main(String[] args) {
        launch(args); // Launch JavaFX application
    }
}
