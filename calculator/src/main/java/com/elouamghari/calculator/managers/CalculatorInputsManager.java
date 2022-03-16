package com.elouamghari.calculator.managers;

import com.elouamghari.calculator.constants.CalculatorMathOperations;
import com.elouamghari.calculator.constants.MemoryOperationType;
import com.elouamghari.calculator.inputs.DeleteInput;
import com.elouamghari.calculator.inputs.FloatPointInput;
import com.elouamghari.calculator.inputs.MemoryInputs;
import com.elouamghari.calculator.inputs.NumberInput;
import com.elouamghari.calculator.inputs.OperatorInput;
import com.elouamghari.calculator.inputs.OtherInputs;
import com.elouamghari.calculator.inputs.PercentInput;

public class CalculatorInputsManager {
    private final DeleteInput deleteInput;
    private final NumberInput numberInput;
    private final FloatPointInput floatPointInput;
    private final OperatorInput operatorInput;
    private final PercentInput percentInput;
    private final MemoryInputs memoryInputs;
    private final OtherInputs otherInputs;

    public CalculatorInputsManager(CalculatorResourcesManager resources, CalculatorDisplayManager display) {
        this.deleteInput = new DeleteInput(resources, display);
        this.numberInput = new NumberInput(resources, display);
        this.floatPointInput = new FloatPointInput(resources, display);
        this.operatorInput = new OperatorInput(resources, display);
        this.percentInput = new PercentInput(resources, display);
        this.memoryInputs = new MemoryInputs(resources, display);
        this.otherInputs = new OtherInputs(resources, display);
    }

    public void delete() {
        this.deleteInput.delete();
    }

    public void zero(){
        setNumber(0);
    }

    public void one(){
        setNumber(1);
    }

    public void two(){
        setNumber(2);
    }

    public void three(){
        setNumber(3);
    }

    public void four(){
        setNumber(4);
    }

    public void five(){
        setNumber(5);
    }

    public void six(){
        setNumber(6);
    }

    public void seven(){
        setNumber(7);
    }

    public void eight(){
        setNumber(8);
    }

    public void nine(){
        setNumber(9);
    }

    private void setNumber(int number) {
        this.numberInput.setNumber(number);
    }

    public void setFloatPoint() {
        this.floatPointInput.setPoint();
    }

    public void add(){
        setOperator(CalculatorMathOperations.ADDITION);
    }

    public void multiply(){
        setOperator(CalculatorMathOperations.MULTIPLICATION);
    }

    public void subtract(){
        setOperator(CalculatorMathOperations.SUBTRACTION);
    }

    public void divide(){
        setOperator(CalculatorMathOperations.DIVISION);
    }

    private void setOperator(String operator) {
        this.operatorInput.setOperator(operator);
    }

    public void percent() {
        this.percentInput.percent();
    }

    public void mPlus(){
        addToMemory(MemoryOperationType.PLUS);
    }

    public void mMinus(){
        addToMemory(MemoryOperationType.MINUS);
    }

    private void addToMemory(String type) {
        this.memoryInputs.setMemory(type);
    }

    public void mrc() {
        this.memoryInputs.mrc();
    }

    public void equal() {
        this.otherInputs.equal();
    }

    public void resetCalculator() {
        this.otherInputs.resetCalculator();
    }
}
