package com.example.transportcostcomparator;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Locale;

public class HomeFragment extends Fragment implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textToSpeech = new TextToSpeech(requireContext(), this);

        View btnListen = view.findViewById(R.id.btnListen);
        btnListen.setOnClickListener(v -> {
            if (textToSpeech != null) {
                textToSpeech.speak(
                        "Welcome to the Transport Cost Comparator App",
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null
                );
            }
        });

        View btnStart = view.findViewById(R.id.btnStartCalculation);
        btnStart.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).showDetailsFragment();
            }
        });

        View btnExit = view.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> requireActivity().finish());

        return view;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(Locale.US);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}