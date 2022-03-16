package com.elouamghari.calculator.managers;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.elouamghari.calculator.models.MemoryOperation;
import com.elouamghari.calculator.models.Operation;

import java.util.ArrayList;
import java.util.List;

public class CalculatorResourcesManager<D extends ViewDataBinding> {
    private final Context context;
    private final D calculatorView;
    private Operation operation;
    private List<Operation> operations;
    private List<MemoryOperation> memoryOperations;
    private boolean error;
    private boolean isButtonAC;

    public CalculatorResourcesManager(Context context, D calculatorView) {
        this.context = context;
        this.calculatorView = calculatorView;
        this.init();
    }

    public void init() {
        this.reset();
        this.memoryOperations = new ArrayList();
    }

    public void reset() {
        this.operations = new ArrayList();
        this.operation = new Operation();
    }

    public void initOperation() {
        this.operation = new Operation();
    }

    public Context getContext() {
        return this.context;
    }

    public Operation getOperation() {
        return this.operation;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public List<MemoryOperation> getMemoryOperations() {
        return this.memoryOperations;
    }

    public boolean isError() {
        return this.error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isButtonAC() {
        return this.isButtonAC;
    }

    public void setButtonAC(boolean buttonAC) {
        this.isButtonAC = buttonAC;
    }

    public D getCalculatorView() {
        return this.calculatorView;
    }

    public boolean isNumber1OverWriten() {
        return this.operations.size() > 0;
    }
}
