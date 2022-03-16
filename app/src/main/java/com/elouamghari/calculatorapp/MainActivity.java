package com.elouamghari.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.elouamghari.calculator.Calculator;
import com.elouamghari.calculatorapp.databinding.ActivityMainBinding;
import com.elouamghari.calculatorapp.databinding.KhatabookCalculatorLayoutBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Calculator<KhatabookCalculatorLayoutBinding> calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calculator = new Calculator<>(this, binding.calculator);
        calculator.setDisplayListener(this::calculatorDisplay);
        calculator.setDetailsListener(this::calculatorDetails);
    }

    private void calculatorDisplay(String text) {
        binding.detailsCard.resultTextView.setText(text);
    }

    private void calculatorDetails(String text) {
        final boolean isDetailsVisible = binding.detailsCard.detailsContainer.getVisibility() == View.VISIBLE;
        if (isDetailsVisible && text.isEmpty()){
            binding.detailsCard.detailsContainer.setVisibility(View.GONE);
        }
        else if(!isDetailsVisible && !text.isEmpty()){
            binding.detailsCard.detailsContainer.setVisibility(View.VISIBLE);
        }
        binding.detailsCard.detailsTextView.setText(text);
    }
}