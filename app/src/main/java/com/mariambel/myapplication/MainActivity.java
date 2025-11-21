package com.mariambel.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.mariambel.myapplication.databinding.ActivityMainBinding;
import com.mariambel.myapplication.frmanager.Paginador;

public class MainActivity extends AppCompatActivity {
    int contador;
    Button mainButton;

    private ActivityMainBinding binding;
    TextView mainTV;
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


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Paginador paginador = new Paginador(this,getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(paginador);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String tiempo = String.valueOf(bundle.getLong("tiempoInvertido", 0));
            mainTV.setText(tiempo+" ha tardado en llenar el login.");
        }


    }
}