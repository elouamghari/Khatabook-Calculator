package com.elouamghari.calculator.inputs;

import com.elouamghari.calculator.R;
import com.elouamghari.calculator.constants.CalculatorPosition;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;

public class NumberInput {
    private final CalculatorResourcesManager<?> resources;
    private final CalculatorDisplayManager display;

    public NumberInput(CalculatorResourcesManager<?> resources, CalculatorDisplayManager display) {
        this.resources = resources;
        this.display = display;
    }

    public void setNumber(int number) {
        if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_1 && !this.resources.isNumber1OverWriten()) {
            if (this.floatNumbersCount(String.valueOf(this.resources.getOperation().getNumber1().getValue())) >= 3) {
                return;
            }

            if (this.resources.getOperation().getNumber1().getDisplay().equals("0")) {
                this.resources.getOperation().overwriteNumber1("");
            }

            this.resources.getOperation().setNumber1(String.valueOf(number));
            this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
        } else if (this.resources.getOperation().getPosition() == CalculatorPosition.OPERATOR || this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_2) {
            if (this.floatNumbersCount(String.valueOf(this.resources.getOperation().getNumber2().getValue())) >= 3) {
                return;
            }

            if (this.resources.getOperation().getNumber2().getDisplay().equals("0")) {
                this.resources.getOperation().overwriteNumber2("");
            }

            this.resources.getOperation().setNumber2(String.valueOf(number));
            this.display.showDisplay(this.resources.getOperation().getNumber2().getDisplay());
            if (this.resources.getOperation().getResult() <= 0.0F) {
                this.resources.setError(true);
                this.display.showError(this.resources.getContext().getString(R.string.invalid_calculator_result));
                return;
            }

            this.resources.setError(false);
            this.display.showDetails(true);
        }

    }

    private int floatNumbersCount(String numberString) {
        if (!numberString.contains(".")) {
            return 0;
        } else {
            int commaIndex = numberString.indexOf(".");
            return numberString.length() - commaIndex;
        }
    }
}
