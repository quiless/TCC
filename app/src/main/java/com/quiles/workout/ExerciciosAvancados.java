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

/**
 * Created by jehwl on 05/03/2017.
 */

public class ExerciciosAvancados extends AppCompatActivity {

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
                NavUtils.navigateUpFromSameTask(ExerciciosAvancados.this);
            }
        });

        listaExercicios = (ListView) findViewById(R.id.list_exercicios);
        relativeLayout = (RelativeLayout) findViewById(R.id.content_exercicios);
        treinoSelecionado = getIntent().getExtras().getInt("numeroTreino");
        treinoEscolhido = getIntent().getExtras().getString("treinoEscolhido");

        String [] listaExercicio = {};

        switch (treinoSelecionado){
            case 0: listaExercicio = new String[]{
                    "Ombro 1 Avançado",
                    "Ombro 2 Avançado",
                    "Ombro 3 Avançado",
                    "Ombro 4 Avançado",
                    "Ombro 5 Avançado",
                    "Ombro 6 Avançado"

            };
                break;
            case 1: listaExercicio = new String[]{
                    "Costas 1 Avançado",
                    "Costas 2 Avançado",
                    "Costas 3 Avançado",
                    "Costas 4 Avançado",
                    "Costas 5 Avançado",
                    "Costas 6 Avançado"

            };
                break;
            case 2: listaExercicio = new String[]{
                    "Trapézio 1 Avançado",
                    "Trapézio 2 Avançado",
                    "Trapézio 3 Avançado",
                    "Trapézio 4 Avançado",
                    "Trapézio 5 Avançado",
                    "Trapézio 6 Avançado"

            };
                break;
            case 3: listaExercicio = new String[]{
                    "Biceps 1 Avançado",
                    "Biceps 2 Avançado",
                    "Biceps 3 Avançado",
                    "Biceps 4 Avançado",
                    "Biceps 5 Avançado",
                    "Biceps 6 Avançado"

            };
                break;
            case 4: listaExercicio = new String[]{
                    "Triceps 1 Avançado",
                    "Triceps 2 Avançado",
                    "Triceps 3 Avançado",
                    "Triceps 4 Avançado",
                    "Triceps 5 Avançado",
                    "Triceps 6 Avançado"

            };
                break;
            case 5: listaExercicio = new String[]{
                    "Antebraço 1 Avançado",
                    "Antebraço 2 Avançado",
                    "Antebraço 3 Avançado",
                    "Antebraço 4 Avançado",
                    "Antebraço 5 Avançado",
                    "Antebraço 6 Avançado"

            };
                break;
            case 6: listaExercicio = new String[]{
                    "Perna 1 Avançado",
                    "Perna 2 Avançado",
                    "Perna 3 Avançado",
                    "Perna 4 Avançado",
                    "Perna 5 Avançado",
                    "Perna 6 Avançado"

            };
                break;
            case 7: listaExercicio = new String[]{
                    "Abdominal Obliquo 1 Avançado",
                    "Abdominal Obliquo 2 Avançado",
                    "Abdominal Obliquo 3 Avançado",
                    "Abdominal Obliquo 4 Avançado",
                    "Abdominal Obliquo 5 Avançado",
                    "Abdominal Obliquo 6 Avançado"

            };
                break;
            case 8: listaExercicio = new String[]{
                    "Abdominal  1 Avançado",
                    "Abdominal  2 Avançado",
                    "Abdominal  3 Avançado",
                    "Abdominal  4 Avançado",
                    "Abdominal  5 Avançado",
                    "Abdominal  6 Avançado"

            };
                break;
            default: listaExercicio = new String[]{
                    "Treino  1 Avançado",
                    "Treino  2 Avançado",
                    "Treino  3 Avançado",
                    "Treino  4 Avançado",
                    "Treino  5 Avançado",
                    "Treino  6 Avançado"
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
        Intent intent = new Intent(ExerciciosAvancados.this, TreinoRunAvancado.class);
        intent.putExtra("numeroTreino", treinoSelecionado);
        intent.putExtra("treinoEscolhido", treinoEscolhido);
        startActivity(intent);
    }


    public void loadTelaVideos(){
        Intent i = new Intent(getApplicationContext(), TelaVideosAvancado.class);
        i.putExtra("nomeVideo", exercicioSelecionado);
        i.putExtra("numeroTreino", treinoSelecionado);
        i.putExtra("posicao", itemPosition);
        startActivity(i);
    }
}
