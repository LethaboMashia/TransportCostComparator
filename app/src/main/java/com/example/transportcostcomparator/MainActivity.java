package com.example.transportcostcomparator; // match your actual package

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.navHome).setOnClickListener(v -> showHomeFragment());
        findViewById(R.id.navDetails).setOnClickListener(v -> showDetailsFragment());
        findViewById(R.id.navReport).setOnClickListener(v -> showReportFragment());
        findViewById(R.id.navHelp).setOnClickListener(v -> showHelpFragment());

        if (savedInstanceState == null) {
            showHomeFragment();
        }
    }

    public void showHomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new HomeFragment())
                .commit();
    }

    public void showDetailsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new DetailsFragment())
                .commit();
    }

    public void showReportFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new ReportFragment())
                .commit();
    }

    public void showHelpFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new HelpFragment())
                .commit();
    }
}