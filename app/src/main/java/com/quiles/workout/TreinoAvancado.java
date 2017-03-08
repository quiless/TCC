package com.quiles.workout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class TreinoAvancado extends AppCompatActivity {

    ListView listaTreino;
    String treinoSelecionado;
    int numeroTreino;
    String treinoEscolhido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.imagem_voltar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(TreinoAvancado.this);
            }
        });

        listaTreino = (ListView) findViewById(R.id.list_view);
        String[] listaExercicios = {
                "Ombro Avancado",
                "Costas Avancado",
                "Trapézio Avancado",
                "Biceps Avancado",
                "Triceps Avancado",
                "Biceps Avancado",
                "Antebraço Avancado",
                "Perna Avancado",
                "Abdominal Obliquio Avancado",
                "Outro Abdominal Avancado"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listaExercicios);
        listaTreino.setAdapter(adapter);

        listaTreino.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                numeroTreino = position;
                treinoEscolhido = "" + listaTreino.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Hello" + String.valueOf(position), Toast.LENGTH_LONG).show();
                loadTelaExercicios();
            }
        });


    }

    public void loadTelaExercicios(){
        Intent i = new Intent(getApplicationContext(), ExerciciosAvancados.class);
        i.putExtra("numeroTreino", numeroTreino);
        i.putExtra("treinoEscolhido", treinoEscolhido);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
