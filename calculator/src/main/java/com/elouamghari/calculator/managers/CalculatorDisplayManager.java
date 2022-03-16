package com.elouamghari.calculator.managers;

import com.elouamghari.calculator.listeners.CalculatorDetailsListener;
import com.elouamghari.calculator.listeners.CalculatorDisplayListener;
import com.elouamghari.calculator.listeners.CalculatorErrorListener;
import com.elouamghari.calculator.listeners.CalculatorResultListener;
import com.elouamghari.calculator.models.MemoryOperation;
import com.elouamghari.calculator.models.Operation;
import com.elouamghari.calculator.utils.CalculatorUtils;

public class CalculatorDisplayManager {
    private final CalculatorResourcesManager<?> resources;
    private CalculatorDisplayListener displayListener;
    private CalculatorDetailsListener detailsListener;
    private CalculatorErrorListener errorListener;
    private CalculatorResultListener resultListener;

    public CalculatorDisplayManager(CalculatorResourcesManager<?> resources) {
        this.resources = resources;
    }

    public void setDisplayListener(CalculatorDisplayListener displayListener) {
        this.displayListener = displayListener;
    }

    public void setDetailsListener(CalculatorDetailsListener detailsListener) {
        this.detailsListener = detailsListener;
    }

    public void setErrorListener(CalculatorErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void setResultListener(CalculatorResultListener resultListener) {
        this.resultListener = resultListener;
    }

    public String getCalculationString() {
        String s = "";
        if (!this.resources.getOperations().isEmpty()) {
            s = s + ((Operation)this.resources.getOperations().get(0)).getNumber1().getDisplay();
            s = s + ((Operation)this.resources.getOperations().get(0)).getOperation();
            s = s + ((Operation)this.resources.getOperations().get(0)).getNumber2().getDisplay();
        }

        for(int i = 1; i < this.resources.getOperations().size(); ++i) {
            s = s + ((Operation)this.resources.getOperations().get(i)).getOperation();
            s = s + ((Operation)this.resources.getOperations().get(i)).getNumber2().getDisplay();
        }

        if (!this.resources.isNumber1OverWriten()) {
            s = s + this.resources.getOperation().getNumber1().getDisplay();
        }

        if (!this.resources.getOperation().getNumber2().getDisplay().isEmpty()) {
            s = s + this.resources.getOperation().getOperation() + this.resources.getOperation().getNumber2().getDisplay();
        }

        return s;
    }

    public String getMemoryDisplayString() {
        String s = "";

        for (MemoryOperation mo : resources.getMemoryOperations()){
            s = s + mo.getFullDisplay() + "\n";
        }

        return s;
    }

    private String getFullDisplayString() {
        String s = "";
        s = s + this.getMemoryDisplayString();
        s = s + this.getCalculationString();
        return s;
    }

    public void showError(String message) {
        if (this.errorListener != null) {
            this.errorListener.onError(message);
        }

    }

    public void showDisplay(String s) {
        if (this.displayListener != null) {
            this.displayListener.onDisplayChanged(s);
        }

    }

    public void showDetails(boolean withResult) {
        String s = this.getFullDisplayString();
        if (withResult) {
            s = s + " = " + CalculatorUtils.convertToNonScientific(this.resources.getOperation().getResult());
            if (this.resultListener != null && this.resources.getOperation().getResult() > 0.0f){
                this.resultListener.onResultChanged(this.resources.getOperation().getResult());
            }
        }
        this.showDetails(s);
    }

    public void showDetails(String s) {
        if (this.detailsListener != null) {
            this.detailsListener.onDetailsChanged(s);
        }
    }
}
