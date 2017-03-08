package com.quiles.workout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class ExerciciosIntermediarios extends AppCompatActivity {

    ListView listaExercicios;
    String exercicioSelecionado;
    int treinoSelecionado;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private RelativeLayout relativeLayout;
    private Button popup;
    WebView video;
    TextToSpeech object;
    int positionExercicio;
    public int itemPosition;
    String treinoEscolhido;


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
                NavUtils.navigateUpFromSameTask(ExerciciosIntermediarios.this);
            }
        });

        listaExercicios = (ListView) findViewById(R.id.list_exercicios);
        relativeLayout = (RelativeLayout) findViewById(R.id.content_exercicios);
        treinoSelecionado = getIntent().getExtras().getInt("numeroTreino");
        treinoEscolhido = getIntent().getExtras().getString("treinoEscolhido");

        String [] listaExercicio = {};

        switch (treinoSelecionado){
            case 0: listaExercicio = new String[]{
                    "Ombro 1 Intermediario",
                    "Ombro 2 Intermediario",
                    "Ombro 3 Intermediario",
                    "Ombro 4 Intermediario",
                    "Ombro 5 Intermediario",
                    "Ombro 6 Intermediario"

            };
                break;
            case 1: listaExercicio = new String[]{
                    "Costas 1 Intermediario",
                    "Costas 2 Intermediario",
                    "Costas 3 Intermediario",
                    "Costas 4 Intermediario",
                    "Costas 5 Intermediario",
                    "Costas 6 Intermediario "

            };
                break;
            case 2: listaExercicio = new String[]{
                    "Trapézio 1 Intermediario",
                    "Trapézio 2 Intermediario",
                    "Trapézio 3 Intermediario",
                    "Trapézio 4 Intermediario",
                    "Trapézio 5 Intermediario",
                    "Trapézio 6 Intermediario"

            };
                break;
            case 3: listaExercicio = new String[]{
                    "Biceps 1 Intermediario",
                    "Biceps 2 Intermediario",
                    "Biceps 3 Intermediario",
                    "Biceps 4 Intermediario",
                    "Biceps 5 Intermediario",
                    "Biceps 6 Intermediario"

            };
                break;
            case 4: listaExercicio = new String[]{
                    "Triceps 1 Intermediario",
                    "Triceps 2 Intermediario",
                    "Triceps 3 Intermediario",
                    "Triceps 4 Intermediario",
                    "Triceps 5 Intermediario",
                    "Triceps 6 Intermediario"

            };
                break;
            case 5: listaExercicio = new String[]{
                    "Antebraço 1 Intermediario",
                    "Antebraço 2 Intermediario",
                    "Antebraço 3 Intermediario",
                    "Antebraço 4 Intermediario",
                    "Antebraço 5 Intermediario",
                    "Antebraço 6 Intermediario"

            };
                break;
            case 6: listaExercicio = new String[]{
                    "Perna 1 Intermediario",
                    "Perna 2 Intermediario",
                    "Perna 3 Intermediario",
                    "Perna 4 Intermediario",
                    "Perna 5 Intermediario",
                    "Perna 6 Intermediario"

            };
                break;
            case 7: listaExercicio = new String[]{
                    "Abdominal Obliquo 1 Intermediario",
                    "Abdominal Obliquo 2 Intermediario",
                    "Abdominal Obliquo 3 Intermediario",
                    "Abdominal Obliquo 4 Intermediario",
                    "Abdominal Obliquo 5 Intermediario",
                    "Abdominal Obliquo 6 Intermediario"

            };
                break;
            case 8: listaExercicio = new String[]{
                    "Abdominal  1 Intermediario",
                    "Abdominal  2 Intermediario",
                    "Abdominal  3 Intermediario",
                    "Abdominal  4 Intermediario",
                    "Abdominal  5 Intermediario",
                    "Abdominal  6 Intermediario"

            };
                break;
            default: listaExercicio = new String[]{
                    "Treino  1 Intermediario",
                    "Treino  2 Intermediario",
                    "Treino  3 Intermediario",
                    "Treino  4 Intermediario",
                    "Treino  5 Intermediario",
                    "Treino  6 Intermediario"
            };
                break;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listaExercicio);
        listaExercicios.setAdapter(adapter);

        listaExercicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemPosition = position;
                String itemValue = (String) listaExercicios.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Exercício : "+itemValue, Toast.LENGTH_SHORT).show();
                exercicioSelecionado = itemValue;
                loadTelaVideos();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void treinoRun(View view){
        Intent intent = new Intent(ExerciciosIntermediarios.this, TreinoRunIntermediario.class);
        intent.putExtra("numeroTreino", treinoSelecionado);
        intent.putExtra("treinoEscolhido", treinoEscolhido);
        startActivity(intent);
    }


    public void loadTelaVideos(){
        Intent i = new Intent(getApplicationContext(), TelaVideosIntermediario.class);
        i.putExtra("nomeVideo", exercicioSelecionado);
        i.putExtra("numeroTreino", treinoSelecionado);
        i.putExtra("posicao", itemPosition);
        startActivity(i);
    }
}
