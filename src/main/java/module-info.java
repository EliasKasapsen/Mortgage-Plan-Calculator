module com.mortgage_plan_calculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mortgage_plan_calculator to javafx.fxml;
    exports com.mortgage_plan_calculator;
}
