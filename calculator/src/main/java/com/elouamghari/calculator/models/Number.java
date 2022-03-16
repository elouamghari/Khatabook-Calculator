package com.elouamghari.calculator.models;

import com.elouamghari.calculator.constants.CalculatorMathOperations;
import com.elouamghari.calculator.utils.CalculatorUtils;

public class Number {
    private String display;
    private boolean isPercent;
    private float value;

    public Number() {
        this.display = "";
        this.value = 0.0F;
    }

    public Number(String display, boolean isPercent, float value) {
        this.display = display;
        this.isPercent = isPercent;
        this.value = value;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
        if (!display.isEmpty()) {
            this.value = CalculatorUtils.parseFloat(display);
        } else {
            this.value = 0.0F;
        }

        if (this.isPercent && !display.contains(CalculatorMathOperations.PERCENT)) {
            this.isPercent = false;
        } else if (!this.isPercent && display.contains(CalculatorMathOperations.PERCENT)) {
            this.isPercent = true;
        }

    }

    public void setPercent() {
        if (!this.isPercent) {
            this.isPercent = true;
            this.display = this.display + CalculatorMathOperations.PERCENT;
            this.value = this.getValue() / 100.0F;
        }
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
