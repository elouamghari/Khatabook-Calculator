package com.elouamghari.calculator.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.elouamghari.calculator.Calculator;
import com.elouamghari.calculator.databinding.CalculatorViewLayoutBinding;
import com.elouamghari.calculator.listeners.CalculatorDetailsListener;
import com.elouamghari.calculator.listeners.CalculatorDisplayListener;
import com.elouamghari.calculator.listeners.CalculatorErrorListener;
import com.elouamghari.calculator.listeners.CalculatorResultListener;
import com.elouamghari.calculator.managers.CalculatorDisplayManager;
import com.elouamghari.calculator.managers.CalculatorInputsManager;
import com.elouamghari.calculator.managers.CalculatorResourcesManager;

public class CalculatorView extends FrameLayout {

    private Calculator<CalculatorViewLayoutBinding> calculator;

    public CalculatorView(@NonNull Context context) {
        this(context, null);
    }

    public CalculatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalculatorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        com.elouamghari.calculator.databinding.CalculatorViewLayoutBinding binding = CalculatorViewLayoutBinding.inflate(LayoutInflater.from(getContext()));
        addView(binding.getRoot());
        calculator = new Calculator<>(getContext(), binding);
    }

    public void reset() {
        calculator.reset();
    }

    public void setDisplayListener(CalculatorDisplayListener displayListener) {
        calculator.setDisplayListener(displayListener);
    }

    public void setDetailsListener(CalculatorDetailsListener detailsListener) {
        calculator.setDetailsListener(detailsListener);
    }

    public void setErrorListener(CalculatorErrorListener errorListener) {
        calculator.setErrorListener(errorListener);
    }

    public void setResultListener(CalculatorResultListener resultListener) {
        calculator.setResultListener(resultListener);
    }

    public boolean isError() {
        return calculator.isError();
    }

    public float getResult() {
        return calculator.getResult();
    }

    public CalculatorViewLayoutBinding getCalculatorView() {
        return calculator.getCalculatorView();
    }

    public CalculatorDisplayManager getCalculatorDisplayManager() {
        return calculator.getCalculatorDisplayManager();
    }

    public CalculatorInputsManager getCalculatorInputsManager() {
        return calculator.getCalculatorInputsManager();
    }

    public CalculatorResourcesManager<CalculatorViewLayoutBinding> getCalculatorResourcesManager() {
        return calculator.getCalculatorResourcesManager();
    }

}
