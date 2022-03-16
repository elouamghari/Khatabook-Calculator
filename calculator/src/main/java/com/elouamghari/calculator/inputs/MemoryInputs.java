package com.elouamghari.calculator.inputs;

import android.view.View;
import android.widget.TextView;

import com.elouamghari.calculator.R;
import com.elouamghari.calculator.constants.MemoryOperationType;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;
import com.elouamghari.calculator.models.MemoryOperation;
import com.elouamghari.calculator.utils.CalculatorUtils;

import java.util.List;

public class MemoryInputs {
    private final CalculatorResourcesManager<?> resources;
    private final CalculatorDisplayManager display;

    public MemoryInputs(CalculatorResourcesManager<?> resources, CalculatorDisplayManager display) {
        this.resources = resources;
        this.display = display;
    }

    public void setMemory(String type) {
        if (!this.resources.isError()) {
            if (this.resources.getOperation().getNumber1().getDisplay().isEmpty()) {
                return;
            }

            String calculationString = this.display.getCalculationString();
            this.resources.getOperations().add(this.resources.getOperation());
            this.resources.getMemoryOperations().add(new MemoryOperation(this.resources.getOperations(), type, calculationString));
            this.resources.reset();
            TextView mrcButton = ((TextView) this.resources.getCalculatorView().getRoot().findViewById(R.id.buttonMRC));
            if (mrcButton != null){
                mrcButton.setText(String.format(this.resources.getContext().getString(R.string.mrc), this.getMrcResultString(this.getMrcResult())));
                if (mrcButton.getVisibility() == View.GONE) {
                    mrcButton.setVisibility(View.VISIBLE);
                }
            }
            this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
            this.display.showDetails(false);
        }

    }

    public void mrc() {
        if (!this.resources.isError() && !this.resources.getMemoryOperations().isEmpty()) {
            float result = this.getMrcResult();
            if (result < 0.0F) {
                this.display.showError(this.resources.getContext().getString(R.string.invalid_calculator_result));
            } else {
                this.resources.initOperation();
                this.resources.getOperation().setNumber1(String.valueOf(result));
                View mrcButton = this.resources.getCalculatorView().getRoot().findViewById(R.id.buttonMRC);
                if (mrcButton != null){
                    mrcButton.setVisibility(View.GONE);
                }
                this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
                this.display.showDetails(this.display.getMemoryDisplayString());
            }
        }
    }

    private float getMrcResult() {
        float result = 0.0F;
        List<MemoryOperation> operations = this.resources.getMemoryOperations();
        for (MemoryOperation operation : operations){
            if (operation.getType().equals(MemoryOperationType.PLUS)) {
                result += operation.getResult();
            } else if (operation.getType().equals(MemoryOperationType.MINUS)) {
                result -= operation.getResult();
            }
        }
        return result;
    }

    private String getMrcResultString(float result) {
        return this.resources.getMemoryOperations().isEmpty() ? "" : " = " + CalculatorUtils.roundFloatToFixedTwoDecimals(result);
    }
}
