package com.quiles.workout;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class Historico extends AppCompatActivity {

    private EditText treino;
    private ListView listaTreino;
    private Button btnAdicionar;

    private SQLiteDatabase bancoTreino;

    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<Integer> ids;
    private ArrayList<String> itens;

    int cDay;
    int cMonth;
    int cYear;
    String day;
    String month;
    String finalDate;
    String dataTreino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listaTreino = (ListView) findViewById(R.id.list_treino);

        carregaTreinos();

        listaTreino.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                alertaApagarTarefa(position);

                return false;
            }
        });
    }

    public void carregaTreinos(){

        try {

            bancoTreino = openOrCreateDatabase("bancoTreino", MODE_PRIVATE, null);
            bancoTreino.execSQL("CREATE TABLE IF NOT EXISTS meustreinos(id INTEGER PRIMARY KEY AUTOINCREMENT, treino VARCHAR)");

            Cursor cursor = bancoTreino.rawQuery("SELECT * FROM meustreinos ORDER BY id DESC", null);

            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaTreino = cursor.getColumnIndex("treino");

            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();

            itensAdaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, itens);
            listaTreino.setAdapter(itensAdaptador);

            cursor.moveToFirst();
            while (cursor != null) {
                    Log.i("Logx", "ID: " + cursor.getString(indiceColunaId) + " TREINO : " + cursor.getString(indiceColunaTreino));
                itens.add(cursor.getString(indiceColunaTreino));
                ids.add(Integer.parseInt(cursor.getString(indiceColunaId)));
                cursor.moveToNext();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void adicionarTreino(String novoTreino){

        Toast.makeText(Historico.this, "Treino salvo ", Toast.LENGTH_SHORT).show();
        treino.setText("");
        bancoTreino.execSQL("INSERT INTO meustreinos(treino) VALUES('"+ novoTreino +"') ");
        carregaTreinos();
    }

    public void apagarTreino(Integer id){

        try {
            bancoTreino.execSQL("DELETE FROM meustreinos WHERE id="+id);
            Toast.makeText(Historico.this, "Treino removido! ", Toast.LENGTH_SHORT).show();
            carregaTreinos();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void alertaApagarTarefa(Integer idSelecionado){

        String tarefaSelecionada = itens.get(idSelecionado);
        final Integer numeroId = idSelecionado;

        new AlertDialog.Builder(Historico.this)
                .setTitle("Aviso!")
                .setMessage("Deseja apagar o treino : " + tarefaSelecionada + " do histórico? " )
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        apagarTreino(ids.get(numeroId));
                    }
                })
                .setNegativeButton("Não", null).show();
    }

}
