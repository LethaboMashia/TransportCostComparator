package com.example.transportcostcomparator; // match your actual package

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    private EditText etModeOfTransport, etDistancePerDay, etCostPerKm, etTravelDays;
    private Spinner spinnerTransportType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        etModeOfTransport = view.findViewById(R.id.etModeOfTransport);
        etDistancePerDay = view.findViewById(R.id.etDistancePerDay);
        etCostPerKm = view.findViewById(R.id.etCostPerKm);
        etTravelDays = view.findViewById(R.id.etTravelDays);
        spinnerTransportType = view.findViewById(R.id.spinnerTransportType);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Private Vehicle", "Taxi", "Bus", "Train", "Motorcycle"}
        );
        spinnerTransportType.setAdapter(adapter);

        view.findViewById(R.id.btnCalculateSave).setOnClickListener(v -> onCalculateAndSave());
        view.findViewById(R.id.btnClear).setOnClickListener(v -> clearFields());

        return view;
    }

    private void onCalculateAndSave() {
        String mode = etModeOfTransport.getText().toString().trim();
        String distanceStr = etDistancePerDay.getText().toString().trim();
        String costStr = etCostPerKm.getText().toString().trim();
        String daysStr = etTravelDays.getText().toString().trim();
        String transportType = spinnerTransportType.getSelectedItem().toString();

        if (mode.isEmpty() || distanceStr.isEmpty() || costStr.isEmpty() || daysStr.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double distance = Double.parseDouble(distanceStr);
            double costPerKm = Double.parseDouble(costStr);
            int days = Integer.parseInt(daysStr);

            Transport transport = new Transport(mode, distance, costPerKm, days, transportType);
            TransportCalculator calculator = new TransportCalculator();
            double dailyCost = calculator.calculateDailyCost(transport);
            double monthlyCost = calculator.calculateMonthlyCost(transport);
            double monthlyDistance = calculator.calculateMonthlyDistance(transport);

            TransportDbHelper dbHelper = new TransportDbHelper(requireContext());
            dbHelper.saveRecord(transport, dailyCost, monthlyCost, monthlyDistance);

            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).showReportFragment();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Distance, cost, and days must be numeric", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etModeOfTransport.setText("");
        etDistancePerDay.setText("");
        etCostPerKm.setText("");
        etTravelDays.setText("");
        spinnerTransportType.setSelection(0);
    }
}