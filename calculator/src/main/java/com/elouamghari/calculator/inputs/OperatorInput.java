package com.elouamghari.calculator.inputs;

import com.elouamghari.calculator.constants.CalculatorPosition;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;
import com.elouamghari.calculator.models.Operation;
import com.elouamghari.calculator.utils.CalculatorUtils;

public class OperatorInput {
    private final CalculatorResourcesManager<?> resources;
    private final CalculatorDisplayManager display;

    public OperatorInput(CalculatorResourcesManager<?> resources, CalculatorDisplayManager display) {
        this.resources = resources;
        this.display = display;
    }

    public void setOperator(String operator) {
        if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_1) {
            if (this.resources.getOperation().getNumber1().getDisplay().isEmpty() || this.resources.getOperation().getNumber1().getDisplay().equals("0.")) {
                this.resources.getOperation().setNumber1("0");
            }

            this.resources.getOperation().setOperation(operator);
            this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay() + this.resources.getOperation().getOperation());
            this.display.showDetails(true);
        } else if (this.resources.getOperation().getPosition() == CalculatorPosition.OPERATOR) {
            this.resources.getOperation().setOperation(operator);
            this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay() + this.resources.getOperation().getOperation());
            this.display.showDetails(true);
        } else if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_2 && !this.resources.isError()) {
            Operation previousOperation = this.resources.getOperation();
            this.resources.getOperations().add(previousOperation);
            this.resources.initOperation();
            this.resources.getOperation().setNumber1(CalculatorUtils.convertToNonScientific(previousOperation.getResult()));
            this.resources.getOperation().setOperation(operator);
            this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay() + this.resources.getOperation().getOperation());
            this.display.showDetails(true);
        }

    }
}
