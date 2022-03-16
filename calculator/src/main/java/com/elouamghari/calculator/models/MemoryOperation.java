package com.elouamghari.calculator.models;

import java.util.List;

public class MemoryOperation {
    private List<Operation> operations;
    private String type;
    private String calculationText;

    public MemoryOperation() {
    }

    public MemoryOperation(List<Operation> operations, String type, String calculationText) {
        this.operations = operations;
        this.type = type;
        this.calculationText = calculationText;
    }

    public float getResult() {
        return this.operations != null && this.operations.size() > 0 ? ((Operation)this.operations.get(this.operations.size() - 1)).getResult() : 0.0F;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCalculationText() {
        return this.calculationText;
    }

    public void setCalculationText(String calculationText) {
        this.calculationText = calculationText;
    }

    public String getFullDisplay() {
        return this.getType() + "(" + this.getCalculationText() + ")=" + this.getResult();
    }
}