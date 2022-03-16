package com.elouamghari.calculator.inputs;

import com.elouamghari.calculator.R;
import com.elouamghari.calculator.constants.CalculatorPosition;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;

public class FloatPointInput {
    private final CalculatorResourcesManager<?> resources;
    private final CalculatorDisplayManager display;

    public FloatPointInput(CalculatorResourcesManager<?> resources, CalculatorDisplayManager display) {
        this.resources = resources;
        this.display = display;
    }

    public void setPoint() {
        if (this.resources.getOperation().getPosition() != CalculatorPosition.NUMBER_2 && this.resources.getOperation().getPosition() != CalculatorPosition.OPERATOR) {
            if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_1) {
                if (this.resources.getOperation().getNumber1().getDisplay().contains(".")) {
                    return;
                }

                if (this.resources.getOperation().getNumber1().getDisplay().isEmpty()) {
                    this.resources.getOperation().setNumber1("0");
                }

                this.resources.getOperation().setNumber1(".");
                this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
            }
        } else {
            if (this.resources.getOperation().getNumber2().getDisplay().contains(".")) {
                return;
            }

            if (this.resources.getOperation().getNumber2().getDisplay().isEmpty()) {
                this.resources.getOperation().setNumber2("0");
            }

            this.resources.getOperation().setNumber2(".");
            this.display.showDisplay(this.resources.getOperation().getNumber2().getDisplay());
            if (this.resources.getOperation().getResult() <= 0.0F) {
                this.display.showError(this.resources.getContext().getString(R.string.invalid_calculator_result));
                return;
            }

            this.display.showDetails(true);
        }

    }
}
