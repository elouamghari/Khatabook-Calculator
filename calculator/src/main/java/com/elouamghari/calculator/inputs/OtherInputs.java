package com.elouamghari.calculator.inputs;

import android.view.View;
import android.widget.TextView;

import com.elouamghari.calculator.R;
import com.elouamghari.calculator.constants.CalculatorPosition;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;
import com.elouamghari.calculator.models.Operation;
import com.elouamghari.calculator.utils.CalculatorUtils;

public class OtherInputs {
    private final CalculatorResourcesManager<?> resources;
    private final CalculatorDisplayManager display;

    public OtherInputs(CalculatorResourcesManager<?> resources, CalculatorDisplayManager display) {
        this.resources = resources;
        this.display = display;
    }

    public void equal() {
        if (!this.resources.isError()) {
            if (this.resources.getOperation().getPosition() == CalculatorPosition.NUMBER_2) {
                Operation previousOperation = this.resources.getOperation();
                this.resources.getOperations().add(previousOperation);
                this.resources.initOperation();
                this.resources.getOperation().setNumber1(CalculatorUtils.convertToNonScientific(previousOperation.getResult()));
                this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay() + this.resources.getOperation().getOperation());
                this.display.showDetails(true);
            } else if (this.resources.getOperation().getPosition() == CalculatorPosition.OPERATOR) {
                this.resources.getOperation().setOperation("");
                this.display.showDisplay(this.resources.getOperation().getNumber1().getDisplay());
                this.display.showDetails(true);
            }
        }

    }

    public void validate() {
        this.equal();
    }

    public void resetCalculator() {
        TextView buttonCAC = ((TextView)this.resources.getCalculatorView().getRoot().findViewById(R.id.buttonCAC));
        if (this.resources.isButtonAC()) {
            this.resources.init();
            if (buttonCAC != null){
                buttonCAC.setText(R.string.c);
            }
            TextView buttonMRC = ((TextView)this.resources.getCalculatorView().getRoot().findViewById(R.id.buttonMRC));
            if (buttonMRC != null){
                if (buttonMRC.getVisibility() == View.VISIBLE) {
                    buttonMRC.setVisibility(View.GONE);
                }
            }
        } else {
            this.resources.reset();
            if (buttonCAC != null){
                buttonCAC.setText(R.string.ac);
            }
        }
        this.resources.setButtonAC(!this.resources.isButtonAC());
        this.display.showDisplay("");
        this.display.showDetails(false);
    }
}
