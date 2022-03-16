package com.elouamghari.calculator;

import android.content.Context;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.databinding.ViewDataBinding;

import com.elouamghari.calculator.constants.CalculatorMathOperations;
import com.elouamghari.calculator.constants.MemoryOperationType;
import com.elouamghari.calculator.listeners.CalculatorDetailsListener;
import com.elouamghari.calculator.listeners.CalculatorDisplayListener;
import com.elouamghari.calculator.listeners.CalculatorErrorListener;
import com.elouamghari.calculator.listeners.CalculatorResultListener;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorInputsManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;
import com.elouamghari.calculator.utils.CalculatorUtils;

public class Calculator<D extends ViewDataBinding> {
    private final CalculatorResourcesManager<D> calculatorResourcesManager;
    private final CalculatorDisplayManager calculatorDisplayManager;
    private final CalculatorInputsManager calculatorInputsManager;

    public Calculator(Context context, D calculatorView) {
        this.calculatorResourcesManager = new CalculatorResourcesManager<>(context, calculatorView);
        this.calculatorDisplayManager = new CalculatorDisplayManager(this.calculatorResourcesManager);
        this.calculatorInputsManager = new CalculatorInputsManager(this.calculatorResourcesManager, this.calculatorDisplayManager);
        this.initCalculatorButtons(calculatorView.getRoot());
    }

    public Calculator(Context context, D calculatorView, float result) {
        this(context, calculatorView);
        this.calculatorResourcesManager.getOperation().setNumber1(String.valueOf(CalculatorUtils.roundFloatToFixedTwoDecimals(result)));
    }

    private void initCalculatorButtons(View calculatorView) {
        if (calculatorView == null) {
            throw new NullPointerException("Calculator view cannot be null");
        } else {
            setButtonOnClickListener(calculatorView, R.id.button0);
            setButtonOnClickListener(calculatorView, R.id.button1);
            setButtonOnClickListener(calculatorView, R.id.button2);
            setButtonOnClickListener(calculatorView, R.id.button3);
            setButtonOnClickListener(calculatorView, R.id.button4);
            setButtonOnClickListener(calculatorView, R.id.button5);
            setButtonOnClickListener(calculatorView, R.id.button6);
            setButtonOnClickListener(calculatorView, R.id.button7);
            setButtonOnClickListener(calculatorView, R.id.button8);
            setButtonOnClickListener(calculatorView, R.id.button9);
            setButtonOnClickListener(calculatorView, R.id.buttonAddition);
            setButtonOnClickListener(calculatorView, R.id.buttonSubtraction);
            setButtonOnClickListener(calculatorView, R.id.buttonMultiplication);
            setButtonOnClickListener(calculatorView, R.id.buttonDivision);
            setButtonOnClickListener(calculatorView, R.id.buttonEqual);
            setButtonOnClickListener(calculatorView, R.id.buttonPoint);
            setButtonOnClickListener(calculatorView, R.id.buttonDelete);
            setButtonOnClickListener(calculatorView, R.id.buttonPercent);
            setButtonOnClickListener(calculatorView, R.id.buttonMPlus);
            setButtonOnClickListener(calculatorView, R.id.buttonMMinus);
            setButtonOnClickListener(calculatorView, R.id.buttonCAC);
            setButtonOnClickListener(calculatorView, R.id.buttonMRC);
        }
    }

    private void setButtonOnClickListener(View calculatorView, @IdRes int resId){
        View button = calculatorView.findViewById(resId);
        if (button != null){
            button.setOnClickListener(this::calculatorButtonsClick);
        }
    }

    private void calculatorButtonsClick(View view) {
        if (view.getId() == R.id.button0) {
            this.calculatorInputsManager.zero();
        } else if (view.getId() == R.id.button1) {
            this.calculatorInputsManager.one();
        } else if (view.getId() == R.id.button2) {
            this.calculatorInputsManager.two();
        } else if (view.getId() == R.id.button3) {
            this.calculatorInputsManager.three();
        } else if (view.getId() == R.id.button4) {
            this.calculatorInputsManager.four();
        } else if (view.getId() == R.id.button5) {
            this.calculatorInputsManager.five();
        } else if (view.getId() == R.id.button6) {
            this.calculatorInputsManager.six();
        } else if (view.getId() == R.id.button7) {
            this.calculatorInputsManager.seven();
        } else if (view.getId() == R.id.button8) {
            this.calculatorInputsManager.eight();
        } else if (view.getId() == R.id.button9) {
            this.calculatorInputsManager.nine();
        } else if (view.getId() == R.id.buttonAddition) {
            this.calculatorInputsManager.add();
        } else if (view.getId() == R.id.buttonMultiplication) {
            this.calculatorInputsManager.multiply();
        } else if (view.getId() == R.id.buttonSubtraction) {
            this.calculatorInputsManager.subtract();
        } else if (view.getId() == R.id.buttonDivision) {
            this.calculatorInputsManager.divide();
        }else if (view.getId() == R.id.buttonEqual) {
            this.calculatorInputsManager.equal();
        } else if (view.getId() == R.id.buttonPoint) {
            this.calculatorInputsManager.setFloatPoint();
        } else if (view.getId() == R.id.buttonDelete) {
            this.calculatorInputsManager.delete();
        } else if (view.getId() == R.id.buttonPercent) {
            this.calculatorInputsManager.percent();
        } else if (view.getId() == R.id.buttonMPlus) {
            this.calculatorInputsManager.mPlus();
        } else if (view.getId() == R.id.buttonMMinus) {
            this.calculatorInputsManager.mMinus();
        } else if (view.getId() == R.id.buttonMRC) {
            this.calculatorInputsManager.mrc();
        } else if (view.getId() == R.id.buttonCAC) {
            this.calculatorInputsManager.resetCalculator();
        }
    }

    public void reset() {
        this.calculatorResourcesManager.init();
        this.calculatorDisplayManager.showDisplay("");
        this.calculatorDisplayManager.showDetails(false);
    }

    public void setDisplayListener(CalculatorDisplayListener displayListener) {
        this.calculatorDisplayManager.setDisplayListener(displayListener);
    }

    public void setDetailsListener(CalculatorDetailsListener detailsListener) {
        this.calculatorDisplayManager.setDetailsListener(detailsListener);
    }

    public void setErrorListener(CalculatorErrorListener errorListener) {
        this.calculatorDisplayManager.setErrorListener(errorListener);
    }

    public void setResultListener(CalculatorResultListener resultListener) {
        this.calculatorDisplayManager.setResultListener(resultListener);
    }

    public boolean isError() {
        return this.calculatorResourcesManager.isError();
    }

    public float getResult() {
        return this.calculatorResourcesManager.getOperation().getResult();
    }

    public D getCalculatorView() {
        return this.calculatorResourcesManager.getCalculatorView();
    }

    public CalculatorDisplayManager getCalculatorDisplayManager() {
        return this.calculatorDisplayManager;
    }

    public CalculatorInputsManager getCalculatorInputsManager() {
        return this.calculatorInputsManager;
    }

    public CalculatorResourcesManager<D> getCalculatorResourcesManager() {
        return this.calculatorResourcesManager;
    }
}
