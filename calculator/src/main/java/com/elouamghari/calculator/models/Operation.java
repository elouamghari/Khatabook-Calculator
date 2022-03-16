package com.elouamghari.calculator.models;

import com.elouamghari.calculator.constants.CalculatorMathOperations;
import com.elouamghari.calculator.constants.CalculatorPosition;
import com.elouamghari.calculator.utils.CalculatorUtils;

public class Operation {
    private Number number1 = new Number();
    private Number number2 = new Number();
    private String operation = "";
    private float result = 0.0F;
    private int position = 0;

    public Operation() {
    }

    public Number getNumber1() {
        return this.number1;
    }

    public void setNumber1(Number number) {
        this.number1 = number;
        this.result = this.number1.getValue();
        this.position = 0;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setNumber1(String num) {
        this.number1.setDisplay(this.getNumber1().getDisplay() + num);
        this.setNumber1(this.number1);
    }

    public Number getNumber2() {
        return this.number2;
    }

    public void setNumber2(Number number) {
        this.number2 = number;
        if (!this.number2.getDisplay().isEmpty()) {
            this.position = 1;
            this.result = this.calculateResult();
        } else {
            this.position = 2;
            this.result = this.getNumber1().getValue();
        }

    }

    public void setNumber2(String num) {
        this.number2.setDisplay(this.getNumber2().getDisplay() + num);
        this.setNumber2(this.number2);
    }

    public void overwriteNumber1(String num) {
        this.number1.setDisplay(num);
        this.setNumber1(this.number1);
    }

    public void deleteNumber1() {
        String s = this.removeLastCharacter(this.getNumber1().getDisplay());
        this.number1.setDisplay(s);
        this.setNumber1(this.number1);
    }

    private String removeLastCharacter(String str) {
        String result = "";
        if (str != null && str.length() > 0) {
            result = str.substring(0, str.length() - 1);
        }

        return result;
    }

    public void overwriteNumber2(String num) {
        this.number2.setDisplay(num);
        this.setNumber2(this.number2);
    }

    public void deleteNumber2() {
        String s = this.removeLastCharacter(this.getNumber2().getDisplay());
        this.number2.setDisplay(s);
        this.setNumber2(this.number2);
    }

    public void setOperation(String operation) {
        this.operation = operation;
        if (!operation.isEmpty()) {
            this.position = 2;
        } else {
            this.position = 0;
        }

    }

    public void setPercent() {
        if (this.getPosition() == CalculatorPosition.NUMBER_1) {
            this.getNumber1().setPercent();
            this.result = this.number1.getValue();
        } else if (this.getPosition() == CalculatorPosition.OPERATOR) {
            this.setOperation("");
            this.getNumber1().setPercent();
            this.result = this.number1.getValue();
        } else if (this.getPosition() == CalculatorPosition.NUMBER_2) {
            this.getNumber2().setPercent();
            this.result = this.calculateResult();
        }

    }

    public int getPosition() {
        return this.position;
    }

    public float getResult() {
        return CalculatorUtils.roundFloatToFixedTwoDecimals(this.result);
    }

    private float calculateResult() {
        if (this.getOperation().equals(CalculatorMathOperations.ADDITION)) {
            return this.getNumber1().getValue() + this.getNumber2().getValue();
        } else if (this.getOperation().equals(CalculatorMathOperations.SUBTRACTION)) {
            return this.getNumber1().getValue() - this.getNumber2().getValue();
        } else if (this.getOperation().equals(CalculatorMathOperations.MULTIPLICATION)) {
            return this.getNumber1().getValue() * this.getNumber2().getValue();
        } else if (this.getOperation().equals(CalculatorMathOperations.DIVISION)) {
            return this.getNumber2().getValue() == 0.0F ? -1.0F : this.getNumber1().getValue() / this.getNumber2().getValue();
        } else {
            return this.getNumber1().getValue();
        }
    }
}
