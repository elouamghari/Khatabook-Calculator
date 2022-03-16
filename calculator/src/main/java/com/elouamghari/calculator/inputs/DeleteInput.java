package com.elouamghari.calculator.inputs;

import com.elouamghari.calculator.R;
import com.elouamghari.calculator.constants.CalculatorPosition;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;
import com.elouamghari.calculator.models.Operation;

public class DeleteInput {
    private final CalculatorResourcesManager<?> resources;
    private final CalculatorDisplayManager display;

    public DeleteInput(CalculatorResourcesManager<?> resources, CalculatorDisplayManager display) {
        this.resources = resources;
        this.display = display;
    }

    public void delete() {
        if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_2) {
            this.resources.getOperation().deleteNumber2();
            if (this.resources.getOperation().getNumber2().getDisplay().isEmpty()) {
                this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay() + this.resources.getOperation().getOperation());
            } else {
                this.display.showDisplay(this.resources.getOperation().getNumber2().getDisplay());
                if (this.resources.getOperation().getResult() <= 0.0F) {
                    this.resources.setError(true);
                    this.display.showError(this.resources.getContext().getString(R.string.invalid_calculator_result));
                    return;
                }
            }

            this.resources.setError(false);
            this.display.showDetails(true);
        } else {
            Operation previousOperation;
            if (this.resources.getOperation().getPosition() == CalculatorPosition.OPERATOR) {
                this.resources.getOperation().setOperation("");
                if (this.resources.isNumber1OverWriten()) {
                    previousOperation = (Operation)this.resources.getOperations().get(this.resources.getOperations().size() - 1);
                    this.resources.initOperation();
                    this.resources.getOperation().setNumber1(previousOperation.getNumber1());
                    this.resources.getOperation().setOperation(previousOperation.getOperation());
                    this.resources.getOperation().setNumber2(previousOperation.getNumber2());
                    this.resources.getOperations().remove(this.resources.getOperations().size() - 1);
                    this.display.showDisplay(this.resources.getOperation().getNumber2().getDisplay());
                } else {
                    this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
                }
                this.display.showDetails(true);
            } else if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_1) {
                if (this.resources.isNumber1OverWriten()) {
                    previousOperation = (Operation)this.resources.getOperations().get(this.resources.getOperations().size() - 1);
                    this.resources.initOperation();
                    this.resources.getOperation().setNumber1(previousOperation.getNumber1());
                    this.resources.getOperation().setOperation(previousOperation.getOperation());
                    this.resources.getOperation().setNumber2(previousOperation.getNumber2());
                    this.resources.getOperations().remove(this.resources.getOperations().size() - 1);
                    this.display.showDisplay(this.resources.getOperation().getNumber2().getDisplay());
                    this.display.showDetails(true);
                } else {
                    this.resources.getOperation().deleteNumber1();
                    this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
                    this.display.showDetails(false);
                }
            }
        }

    }
}
