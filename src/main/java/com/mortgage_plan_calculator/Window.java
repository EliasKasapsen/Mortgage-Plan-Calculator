package com.mortgage_plan_calculator;
import java.util.HashMap;
import java.util.UUID;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Window extends Application
{
    private static final String FONT_SIZE_14 = "-fx-font-size: 14px;";

    private static final String LABEL_MAIN_TITLE = "Loan Repayment Plan Calculator";
    private static final String LABEL_DESCRIPTION = "The program calculates a repayment plan.";
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
    
    private VBox resultsDisplayArea;
    
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
        // ========== LEFTSIDE DEFINITIONS ==========
        Label titleLabel = new Label(LABEL_MAIN_TITLE);
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");
        // ----------
        Label subtitleLabel = new Label(LABEL_DESCRIPTION);
        subtitleLabel.setStyle("-fx-font-size: 16px; -fx-alignment: center;");
        // ----------
        Label planNameLabel = createLabel(LABEL_PLAN_NAME);
        TextField planNameField = createTextField(PROMPT_ENTER_NAME);
        planNameField.setPrefWidth(170);
        planNameField.setMaxWidth(170);
        // ----------
        Label loanAmountLabel = createLabel(LABEL_LOAN_AMOUNT);
        TextField loanAmountField = createTextField(PROMPT_ENTER_AMOUNT);
        loanAmountField.setPrefWidth(170);
        loanAmountField.setMaxWidth(170);
        // ----------
        Label equityLabel = createLabel(LABEL_EQUITY);
        TextField equityField = createTextField(PROMPT_ENTER_AMOUNT);
        equityField.setPrefWidth(170);
        equityField.setMaxWidth(170);
        // ----------
        Label interestLabel = createLabel(LABEL_INTEREST_RATE);
        TextField interestField = createTextField(PROMPT_ENTER_INTEREST_RATE);
        interestField.setPrefWidth(170);
        interestField.setMaxWidth(170);
        // ----------
        Label establishmentFeeLabel = createLabel(LABEL_ESTABLISHMENT_FEE);
        TextField establishmentFeeField = createTextField(PROMPT_ENTER_AMOUNT);
        establishmentFeeField.setPrefWidth(170);
        establishmentFeeField.setMaxWidth(170);
        // ----------
        Label adminFeeLabel = createLabel(LABEL_ADMIN_FEE);
        TextField adminFeeField = createTextField(PROMPT_ENTER_AMOUNT);
        adminFeeField.setPrefWidth(170);
        adminFeeField.setMaxWidth(170);
        // ----------
        Label timeOrPaymentLabel = createLabel(LABEL_TIME_OR_PAYMENT);
        // ----------
        HBox timeOrPaymentButtonsBox = new HBox(10);
        Button setRepaymentTimeButton = new Button(LABEL_REPAYMENT_PERIOD);
        setRepaymentTimeButton.setPrefWidth(170);
        setRepaymentTimeButton.setMaxWidth(170);
        setRepaymentTimeButton.setStyle(STYLE_ACTIVE);
        Button setFixedMonthlyRepaymentButton = new Button(LABEL_MONTHLY_TOTAL_PAYMENT);
        setFixedMonthlyRepaymentButton.setPrefWidth(170);
        setFixedMonthlyRepaymentButton.setMaxWidth(170);
        // ----------
        VBox timeOrPaymentFieldBox = new VBox(10);
        TextField repaymentTimeField = createTextField(PROMPT_ENTER_MONTHS);
        repaymentTimeField.setPrefWidth(170);
        repaymentTimeField.setMaxWidth(170);
        TextField fixedRepaymentField = createTextField(PROMPT_ENTER_AMOUNT);
        fixedRepaymentField.setPrefWidth(170);
        fixedRepaymentField.setMaxWidth(170);
        // ----------
        VBox userDefinedOptionsBox = new VBox(10);
        Button userDefinedPlanButton = new Button(LABEL_LIMIT_REVEAL);
        userDefinedPlanButton.setPrefWidth(350);
        userDefinedPlanButton.setMaxWidth(350);
        // ----------
        HBox customPlanButtonsBox = new HBox(10);
        Button setTimeLimitButton = new Button(LABEL_LIMIT_WITH_TIME);
        setTimeLimitButton.setPrefWidth(170);
        setTimeLimitButton.setMaxWidth(170);
        Button setLoanAmountLimitButton = new Button(LABEL_LIMIT_WITH_LOAN_AMOUNT);
        setLoanAmountLimitButton.setPrefWidth(170);
        setLoanAmountLimitButton.setMaxWidth(170);
        // ----------
        TextField timeLimitField = createTextField(PROMPT_ENTER_MONTHS);
        timeLimitField.setPrefWidth(170);
        timeLimitField.setMaxWidth(170);
        TextField loanAmountLimitField = createTextField(PROMPT_ENTER_AMOUNT);
        loanAmountLimitField.setPrefWidth(170);
        loanAmountLimitField.setMaxWidth(170);
        // ----------

        // ---------- INITIAL VISIBILITY SETTINGS ----------
        repaymentTimeField.setVisible(true);
        fixedRepaymentField.setVisible(false);
        userDefinedOptionsBox.setVisible(false);
        userDefinedOptionsBox.setManaged(false);
        timeLimitField.setVisible(false);
        timeLimitField.setManaged(false);
        loanAmountLimitField.setVisible(false);
        loanAmountLimitField.setManaged(false);

        // ---------- BUTTONS FUNCTION DEFINITION ----------
        setRepaymentTimeButton.setOnAction(e -> {
            toggleButtonStyles(setRepaymentTimeButton, setFixedMonthlyRepaymentButton);
            fixedRepaymentField.clear();
            repaymentTimeField.setVisible(true);
            fixedRepaymentField.setVisible(false);
            timeOrPaymentFieldBox.getChildren().setAll(repaymentTimeField); // Cleaner than clear/add
        });
        // ----------
        setFixedMonthlyRepaymentButton.setOnAction(e -> {
            toggleButtonStyles(setFixedMonthlyRepaymentButton, setRepaymentTimeButton);
            repaymentTimeField.clear();
            repaymentTimeField.setVisible(false);
            fixedRepaymentField.setVisible(true);
            timeOrPaymentFieldBox.getChildren().setAll(fixedRepaymentField);
        });
        // ----------
        userDefinedPlanButton.setOnMouseEntered(e -> {
            if (userDefinedOptionsBox.isVisible()) {
                userDefinedPlanButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
            }
        });
        userDefinedPlanButton.setOnMouseExited(e -> {
            if (userDefinedOptionsBox.isVisible()) {
                userDefinedPlanButton.setStyle(STYLE_ACTIVE);
            } else {
                userDefinedPlanButton.setStyle(STYLE_INACTIVE);
            }
        });
        userDefinedPlanButton.setOnAction(e -> {
            boolean isVisible = !userDefinedOptionsBox.isVisible();
            userDefinedOptionsBox.setVisible(isVisible);
            userDefinedOptionsBox.setManaged(isVisible);
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
        // ----------
        setTimeLimitButton.setOnAction(e -> {
            toggleButtonStyles(setTimeLimitButton, setLoanAmountLimitButton);
            loanAmountLimitField.clear();
            timeLimitField.setVisible(true);
            timeLimitField.setManaged(true);
            loanAmountLimitField.setVisible(false);
            loanAmountLimitField.setManaged(false);
        });
        // ----------
        setLoanAmountLimitButton.setOnAction(e -> {
            toggleButtonStyles(setLoanAmountLimitButton, setTimeLimitButton);
            timeLimitField.clear();
            loanAmountLimitField.setVisible(true);
            loanAmountLimitField.setManaged(true);
            timeLimitField.setVisible(false);
            timeLimitField.setManaged(false);
        });
        // ----------

        // ========== RIGHTSIDE DEFINITIONS ==========
        VBox planListBox = new VBox(10);
        planListBox.setPadding(new Insets(10));
        Label planListLabel = createLabel(LABEL_CREATED_PLANS);
        Label noPlansLabel = new Label(LABEL_NO_PLANS);
        noPlansLabel.setStyle("-fx-font-style: italic; -fx-text-fill: gray;");
        planListBox.getChildren().addAll(planListLabel, noPlansLabel);

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
                String planName = planNameField.getText().isEmpty() ? "Plan " + (mPlans.size() + 1) : planNameField.getText();
                double loanAmount = Double.parseDouble(loanAmountField.getText());
                double equity = equityField.getText().isEmpty() ? 0.0 : Double.parseDouble(equityField.getText());
                double interest = Double.parseDouble(interestField.getText());
                double establishmentFee = establishmentFeeField.getText().isEmpty() ? 0.0 : Double.parseDouble(establishmentFeeField.getText());
                double adminFee = adminFeeField.getText().isEmpty() ? 0.0 : Double.parseDouble(adminFeeField.getText());
                int repaymentTime = repaymentTimeField.getText().isEmpty() ? 0 : Integer.parseInt(repaymentTimeField.getText());
                double fixedRepayment = fixedRepaymentField.getText().isEmpty() ? 0.0 : Double.parseDouble(fixedRepaymentField.getText());
                int timeLimit = timeLimitField.getText().isEmpty() ? 0 : Integer.parseInt(timeLimitField.getText());
                double amountLimit = loanAmountLimitField.getText().isEmpty() ? 0.0 : Double.parseDouble(loanAmountLimitField.getText());

                String planID = UUID.randomUUID().toString();
                Calculations calculation = new Calculations(new InputValues(
                    new MandatoryValues(planName, loanAmount, interest),
                    new InterchangeableValues(equity, establishmentFee, adminFee, repaymentTime, fixedRepayment, timeLimit, amountLimit)
                ));
                mPlans.put(planID, calculation);

                if (planListBox.getChildren().contains(noPlansLabel)) { planListBox.getChildren().remove(noPlansLabel); }

                Button planButton = new Button(planName);
                planButton.setPrefWidth(200);
                planButton.setStyle(
                    "-fx-text-overrun: ellipsis;" + // Truncate text with ellipsis if it's too long
                    "-fx-alignment: CENTER_LEFT;"    // Align text to the left for better readability
                );
                planButton.setOnAction(e0 -> showDetailedPlan(planID));
                
                Button viewButton = new Button("VIEW");
                viewButton.setPrefWidth(55);
                viewButton.setStyle("-fx-text-overrun: ellipsis;");
                viewButton.setOnAction(e4 -> viewInNewWindow(planID));
                
                // Create a red "Delete" button
                Button deleteButton = new Button("DELETE");
                deleteButton.setPrefWidth(65);
                deleteButton.setStyle(
                    "-fx-background-color: #e23e32; " + /* Red background */
                    "-fx-text-fill: white; " + /* White text */
                    "-fx-font-size: 12px; " + /* Slightly smaller font */
                    "-fx-font-weight: bold; "
                );
                deleteButton.setOnMouseEntered(e5 -> deleteButton.setStyle(
                    "-fx-background-color: #f44336; " + /* Red background */
                    "-fx-text-fill: white; " + /* White text */
                    "-fx-font-size: 12px; " + /* Slightly smaller font */
                    "-fx-font-weight: bold; "
                ));
                deleteButton.setOnMouseExited(e6 -> deleteButton.setStyle(
                    "-fx-background-color: #e23e32; " + /* Red background */
                    "-fx-text-fill: white; " + /* White text */
                    "-fx-font-size: 12px; " + /* Slightly smaller font */
                    "-fx-font-weight: bold; "
                ));

                HBox planButtonsBox = new HBox(10);
                planButtonsBox.getChildren().addAll(planButton, viewButton, deleteButton);
                planButtonsBox.setUserData(planID);

                deleteButton.setOnAction(e7 -> {
                    planListBox.getChildren().remove(planButtonsBox);
                    mPlans.remove(planID);
                    if (mPlans.isEmpty()) { planListBox.getChildren().add(noPlansLabel); }
                    resultsDisplayArea.getChildren().clear();
                });

                planListBox.getChildren().add(planButtonsBox);

                showDetailedPlan(planID);
            } catch (NumberFormatException f) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Invalid input. Please enter valid numbers.", ButtonType.OK);
                alert.showAndWait();
            }
        });
        
        // Inside your start() method
        HBox mainRoot = new HBox(10); // This is the master container
        mainRoot.setPadding(new Insets(10));

        // LEFT SIDE: Your existing vbox (where the inputs are)
        // Update the main layout to include the plan list section
        VBox leftInputPanel = new VBox(10);
        leftInputPanel.setPadding(new Insets(10));
        leftInputPanel.setAlignment(Pos.TOP_LEFT);
        // ---------- ADD ELEMENTS TO BOXES ----------
        timeOrPaymentButtonsBox.getChildren().addAll(setRepaymentTimeButton, setFixedMonthlyRepaymentButton);
        timeOrPaymentFieldBox.getChildren().add(repaymentTimeField);
        customPlanButtonsBox.getChildren().addAll(setTimeLimitButton, setLoanAmountLimitButton);
        userDefinedOptionsBox.setAlignment(Pos.TOP_LEFT);
        userDefinedOptionsBox.getChildren().addAll(customPlanButtonsBox, timeLimitField, loanAmountLimitField);
        // ----------
        leftInputPanel.getChildren().addAll(
            titleLabel, subtitleLabel,
            planNameLabel, planNameField,
            loanAmountLabel, loanAmountField,
            equityLabel, equityField,
            interestLabel, interestField,
            establishmentFeeLabel, establishmentFeeField,
            adminFeeLabel, adminFeeField,
            timeOrPaymentLabel, timeOrPaymentButtonsBox, timeOrPaymentFieldBox,
            userDefinedPlanButton, userDefinedOptionsBox,
            calculateButton
        );
        ScrollPane leftDetailPanel = new ScrollPane(leftInputPanel);
        leftDetailPanel.setFitToWidth(true);
        leftDetailPanel.setPrefWidth(450);
        leftDetailPanel.setMinWidth(450);
        leftDetailPanel.setStyle("-fx-background-color: transparent;");

        // Create the vertical divider
        Separator verticalDivider = new Separator();
        verticalDivider.setOrientation(Orientation.VERTICAL);
        verticalDivider.setStyle("-fx-padding: 0 5 0 5;");

        // RIGHT SIDE: The new details area
        VBox detailsContent = new VBox(10);
        detailsContent.setPadding(new Insets(10));
        ScrollPane planListPanel = new ScrollPane(planListBox);
        planListPanel.setFitToWidth(true);
        planListPanel.setPrefWidth(800);
        planListPanel.setMinWidth(800);
        planListPanel.setPrefHeight(200);
        planListPanel.setMinHeight(200);
        resultsDisplayArea = new VBox(10);
        detailsContent.getChildren().addAll(
            planListPanel,
            new javafx.scene.control.Separator(),
            resultsDisplayArea
        );
        ScrollPane rightDetailPanel = new ScrollPane(detailsContent);
        rightDetailPanel.setPrefWidth(Region.USE_COMPUTED_SIZE); 
        HBox.setHgrow(rightDetailPanel, Priority.ALWAYS);
        rightDetailPanel.setStyle("-fx-background-color: transparent;");

        // Put them together
        mainRoot.setSpacing(0);
        mainRoot.getChildren().addAll(leftDetailPanel, verticalDivider, rightDetailPanel);

        // Set up the scene and stage
        Scene scene = new Scene(mainRoot, 1250, 700);
        primaryStage.setTitle("LRPC");
        primaryStage.setScene(scene);

        // Enable non aggressive fullscreen mode
        primaryStage.setMaximized(true);

        primaryStage.show();
    }

    private void showDetailedPlan(String planID) {
        resultsDisplayArea.getChildren().clear();
        if (!mPlans.containsKey(planID)) return;

        Calculations calculations = mPlans.get(planID);
        
        Label title = new Label("Plan: " + calculations.getValues().getMandatoryValues().getPlanName());
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox layout = createPlanBox(calculations);

        resultsDisplayArea.getChildren().addAll(title, layout);
    }

    private void viewInNewWindow(String planID) {
        if (!mPlans.containsKey(planID)) return;

        Calculations calculations = mPlans.get(planID);

        Stage popupStage = new Stage();
        popupStage.setTitle("Plan: " + calculations.getValues().getMandatoryValues().getPlanName());

        VBox layout = createPlanBox(calculations);

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 700, 500);
        popupStage.setScene(scene);
        popupStage.show();
    }

    private VBox createPlanBox(Calculations calculations) {
        GridPane grid = new GridPane();
        grid.setHgap(15); grid.setVgap(5);

        // --- ADD THE HEADER ROW ---
        String[] headers = {"Month", "Principal", "Interest", "Admin Fee", "Total", "Remaining"};
        // 1. COMBINED LOOP: Set constraints AND add headers
        for (int col = 0; col < headers.length; col++) {
            // Set centering and width
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setHalignment(javafx.geometry.HPos.CENTER);
            constraints.setPercentWidth(20);
            grid.getColumnConstraints().add(constraints);

            // Add Header Label with a bottom border for the first "line"
            Label headerLabel = new Label(headers[col]);
            headerLabel.setStyle("-fx-font-weight: bold; -fx-padding: 10; -fx-border-color: #cccccc; -fx-border-width: 0 0 2 0;");
            headerLabel.setMaxWidth(Double.MAX_VALUE); // Ensures label fills the cell for the border
            headerLabel.setAlignment(Pos.CENTER);
            grid.add(headerLabel, col, 0);
        }

        // --- ADD THE DATA ROWS ---
        InterchangeableValues i = calculations.getValues().getInterchangeableValues();
        int limit = (i.getRepaymentPeriodLimit() != 0) ? i.getRepaymentPeriodLimit() : i.getRepaymentPeriod();

        // Define the style for horizontal lines
        String rowStyle = "-fx-padding: 10; -fx-border-color: #ddddddff; -fx-border-width: 0 0 1 0;";

        for (int row = 0; row < limit; row++) {
            // Collect the data for this specific row into an array
            String[] rowData = {
                String.valueOf(row + 1),
                String.format("%.2f", calculations.getMonthlyLoanRepaymentAtIndex(row)),
                String.format("%.2f", calculations.getMonthlyInterestPaymentAtIndex(row)),
                String.format("%.2f", i.getAdminFee()),
                String.format("%.2f", calculations.getMonthlyTotalPaymentAtIndex(row)),
                String.format("%.2f", calculations.getRemainingLoanAmountAtIndex(row))
            };

            // Add each piece of data to the grid with the horizontal line style
            for (int col = 0; col < rowData.length; col++) {
                Label cellLabel = new Label(rowData[col]);
                cellLabel.setStyle(rowStyle);
                cellLabel.setMaxWidth(Double.MAX_VALUE); // This makes the border stretch the full column width
                cellLabel.setAlignment(Pos.CENTER);
                grid.add(cellLabel, col, row + 1);
            }
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label summary = new Label(String.format(
            "Total interest: %.2f\nTotal fees: %.2f\nTotal paid: %.2f",
            calculations.getTotalInterestPayment(),
            calculations.getTotalFee(),
            calculations.getTotalPayment()
        ));
        //summary.setStyle("-fx-font-weight: bold;");

        layout.getChildren().addAll(
            new Label("Repayment Plan for " + calculations.getValues().getMandatoryValues().getPlanName()),
            grid,
            summary
        );

        return layout;
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
