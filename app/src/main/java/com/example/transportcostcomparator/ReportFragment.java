package com.example.transportcostcomparator;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Locale;

public class ReportFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        TextView tvMode = view.findViewById(R.id.tvMode);
        TextView tvDailyCost = view.findViewById(R.id.tvDailyCost);
        TextView tvMonthlyCost = view.findViewById(R.id.tvMonthlyCost);
        TextView tvMonthlyDistance = view.findViewById(R.id.tvMonthlyDistance);
        TextView tvCategory = view.findViewById(R.id.tvCategory);
        TextView tvRecommendation = view.findViewById(R.id.tvRecommendation);

        TransportDbHelper dbHelper = new TransportDbHelper(requireContext());
        Cursor cursor = dbHelper.getLatestRecord();

        if (cursor != null && cursor.moveToFirst()) {
            String mode = cursor.getString(cursor.getColumnIndexOrThrow(TransportDbHelper.COL_MODE));
            String transportType = cursor.getString(cursor.getColumnIndexOrThrow(TransportDbHelper.COL_TRANSPORT_TYPE));
            double dailyCost = cursor.getDouble(cursor.getColumnIndexOrThrow(TransportDbHelper.COL_DAILY_COST));
            double monthlyCost = cursor.getDouble(cursor.getColumnIndexOrThrow(TransportDbHelper.COL_MONTHLY_COST));
            double monthlyDistance = cursor.getDouble(cursor.getColumnIndexOrThrow(TransportDbHelper.COL_MONTHLY_DISTANCE));

            Recommendation rec = new Recommendation();
            String category = rec.getCategory(monthlyCost);
            String recommendation = rec.getRecommendation(monthlyCost);

            tvMode.setText(mode.isEmpty() ? transportType : mode);
            tvDailyCost.setText(String.format(Locale.US, "R%.0f", dailyCost));
            tvMonthlyCost.setText(String.format(Locale.US, "R%.0f", monthlyCost));
            tvMonthlyDistance.setText(String.format(Locale.US, "%.0f KM", monthlyDistance));
            tvCategory.setText(category);
            tvRecommendation.setText(recommendation);

            cursor.close();
        }

        view.findViewById(R.id.btnCalculateAgain).setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).showDetailsFragment();
            }
        });

        view.findViewById(R.id.btnExitReport).setOnClickListener(v -> requireActivity().finish());

        return view;
    }
}