package com.quiles.workout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Exercicios extends AppCompatActivity {

    ListView listaExercicios;
    String exercicioSelecionado;
    int treinoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.imagem_voltar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(Exercicios.this);
            }
        });

        listaExercicios = (ListView) findViewById(R.id.list_exercicios);
        treinoSelecionado = getIntent().getExtras().getInt("numeroTreino");

        String [] listaExercicio = {};

        switch (treinoSelecionado){
            case 0: listaExercicio = new String[]{
                    "Ombro 1",
                    "Ombro 2",
                    "Ombro 3",
                    "Ombro 4",
                    "Ombro 5",
                    "Ombro 6"

            };
                break;
            case 1: listaExercicio = new String[]{
                    "Costas 1",
                    "Costas 2",
                    "Costas 3",
                    "Costas 4",
                    "Costas 5",
                    "Costas 6"

            };
                break;
            case 2: listaExercicio = new String[]{
                    "Trapézio 1",
                    "Trapézio 2",
                    "Trapézio 3",
                    "Trapézio 4",
                    "Trapézio 5",
                    "Trapézio 6"

            };
                break;
            case 3: listaExercicio = new String[]{
                    "Biceps 1",
                    "Biceps 2",
                    "Biceps 3",
                    "Biceps 4",
                    "Biceps 5",
                    "Biceps 6"

            };
                break;
            case 4: listaExercicio = new String[]{
                    "Triceps 1",
                    "Triceps 2",
                    "Triceps 3",
                    "Triceps 4",
                    "Triceps 5",
                    "Triceps 6"

            };
                break;
            case 5: listaExercicio = new String[]{
                    "Antebraço 1",
                    "Antebraço 2",
                    "Antebraço 3",
                    "Antebraço 4",
                    "Antebraço 5",
                    "Antebraço 6"

            };
                break;
            case 6: listaExercicio = new String[]{
                    "Perna 1",
                    "Perna 2",
                    "Perna 3",
                    "Perna 4",
                    "Perna 5",
                    "Perna 6"

            };
                break;
            case 7: listaExercicio = new String[]{
                    "Abdominal Obliquo 1",
                    "Abdominal Obliquo 2",
                    "Abdominal Obliquo 3",
                    "Abdominal Obliquo 4",
                    "Abdominal Obliquo 5",
                    "Abdominal Obliquo 6"

            };
                break;
            case 8: listaExercicio = new String[]{
                    "Abdominal  1",
                    "Abdominal  2",
                    "Abdominal  3",
                    "Abdominal  4",
                    "Abdominal  5",
                    "Abdominal  6"

            };
                break;
            default: listaExercicio = new String[]{
                    "Treino  1",
                    "Treino  2",
                    "Treino  3",
                    "Treino  4",
                    "Treino  5",
                    "Treino  6"
            };
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listaExercicio);
        listaExercicios.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
