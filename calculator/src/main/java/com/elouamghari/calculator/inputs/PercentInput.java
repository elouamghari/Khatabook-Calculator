package com.elouamghari.calculator.inputs;

import com.elouamghari.calculator.constants.CalculatorPosition;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;

public class PercentInput {
    private final CalculatorResourcesManager<?> resources;
    private final CalculatorDisplayManager display;

    public PercentInput(CalculatorResourcesManager<?> resources, CalculatorDisplayManager display) {
        this.resources = resources;
        this.display = display;
    }

    public void percent() {
        if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_1) {
            if (this.resources.getOperation().getNumber1().getDisplay().isEmpty()) {
                return;
            }

            this.resources.getOperation().setPercent();
            this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
            this.display.showDetails(true);
        } else if (this.resources.getOperation().getPosition() == CalculatorPosition.OPERATOR) {
            this.resources.getOperation().setOperation("");
            this.resources.getOperation().setPercent();
            this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
            this.display.showDetails(true);
        } else if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_2) {
            if (this.resources.getOperation().getNumber2().getDisplay().isEmpty()) {
                return;
            }

            this.resources.getOperation().setPercent();
            this.display.showDisplay(this.resources.getOperation().getNumber2().getDisplay());
            this.display.showDetails(true);
        }

    }
}
