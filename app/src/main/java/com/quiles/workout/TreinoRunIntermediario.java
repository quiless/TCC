package com.quiles.workout;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class TreinoRunIntermediario extends AppCompatActivity {

    int exercicio = 0;
    int tempoExercicio;
    int tempoDescanso = 20;
    boolean estadoTreino = false;
    boolean acabandoTempo;
    private String treinoEscolhido;
    private SQLiteDatabase bancoTreino;
    int cDay;
    int cMonth;
    int cYear;
    String day;
    String month;
    String finalDate;
    String dataTreino;

    String[] listaTreino = {
            "Ombro1",
            "Ombro2",
            "Ombro3",
            "Ombro4",
            "Ombro5",
            "Ombro6",
            "Ombro7",
            "Ombro8",
            "Ombro9",
            "Ombro10",
    };

    int[] listaTempos = {
            10,
            10,
            10,
            20,
            20,
            20,
            30,
            30,
            30,
            30
    };


    ProgressBar progressBarTempo;
    TextView exercicioAtual;
    TextView tempo;
    TextView textoProximoExercicio;
    TextView proximoExercicio;
    ImageView imagemEstrela;
    RelativeLayout fundoTela;
    Button btnIniciar;
    CountDownTimer countDownTimer;
    TextToSpeech object;
    int result;
    int treinoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino_run);


        progressBarTempo = (ProgressBar) findViewById(R.id.progressbar_tempo);
        exercicioAtual = (TextView) findViewById(R.id.txt_exercicioAtual);
        tempo = (TextView) findViewById(R.id.txt_tempo);
        textoProximoExercicio = (TextView) findViewById(R.id.txt_textoProximo);
        proximoExercicio = (TextView) findViewById(R.id.txt_proximoExercicio);
        btnIniciar = (Button) findViewById(R.id.btn_iniciarTreino);
        fundoTela = (RelativeLayout) findViewById(R.id.content_treino_run);

        tempoExercicio = listaTempos[exercicio];
        tempo.setText(String.valueOf(exercicio));
        progressBarTempo.setMax(listaTreino.length * 2 );
        progressBarTempo.setProgress(1);

        treinoEscolhido = getIntent().getExtras().getString("treinoEscolhido");

        Toast.makeText(TreinoRunIntermediario.this, "" + treinoEscolhido, Toast.LENGTH_SHORT).show();

        Calendar calander = Calendar.getInstance(TimeZone.getDefault());
        cDay = calander.get(Calendar.DAY_OF_MONTH);
        cMonth = calander.get(Calendar.MONTH) + 1;
        cYear = calander.get(Calendar.YEAR);

        if(cDay < 10){
            day = "0" + cDay;
        } else {
            day = "" + cDay;
        }

        if(cMonth < 10){
            month = "0" + cMonth;
        } else {
            month = "" + cMonth;
        }

        finalDate = "" + day + "/" + month;

        treinoSelecionado = getIntent().getExtras().getInt("numeroTreino");
        String[] listaTreino =  {};
        switch (treinoSelecionado){
            case 0: listaTreino = new String[]{
                    "Ombro 1 Intermediario",
                    "Ombro 2 Intermediario",
                    "Ombro 3 Intermediario",
                    "Ombro 4 Intermediario",
                    "Ombro 5 Intermediario",
                    "Ombro 6 Intermediario"

            };
                break;
            case 1: listaTreino = new String[]{
                    "Costas 1",
                    "Costas 2",
                    "Costas 3",
                    "Costas 4",
                    "Costas 5",
                    "Costas 6"

            };
                break;
            case 2: listaTreino = new String[]{
                    "Trapézio 1",
                    "Trapézio 2",
                    "Trapézio 3",
                    "Trapézio 4",
                    "Trapézio 5",
                    "Trapézio 6"

            };
                break;
            case 3: listaTreino = new String[]{
                    "Biceps 1",
                    "Biceps 2",
                    "Biceps 3",
                    "Biceps 4",
                    "Biceps 5",
                    "Biceps 6"

            };
                break;
            case 4: listaTreino = new String[]{
                    "Triceps 1",
                    "Triceps 2",
                    "Triceps 3",
                    "Triceps 4",
                    "Triceps 5",
                    "Triceps 6"

            };
                break;
            case 5: listaTreino = new String[]{
                    "Antebraço 1",
                    "Antebraço 2",
                    "Antebraço 3",
                    "Antebraço 4",
                    "Antebraço 5",
                    "Antebraço 6"

            };
                break;
            case 6: listaTreino = new String[]{
                    "Perna 1",
                    "Perna 2",
                    "Perna 3",
                    "Perna 4",
                    "Perna 5",
                    "Perna 6"

            };
                break;
            case 7: listaTreino = new String[]{
                    "Abdominal Obliquo 1",
                    "Abdominal Obliquo 2",
                    "Abdominal Obliquo 3",
                    "Abdominal Obliquo 4",
                    "Abdominal Obliquo 5",
                    "Abdominal Obliquo 6"

            };
                break;
            case 8: listaTreino = new String[]{
                    "Abdominal  1",
                    "Abdominal  2",
                    "Abdominal  3",
                    "Abdominal  4",
                    "Abdominal  5",
                    "Abdominal  6"

            };
                break;
            default: listaTreino = new String[]{
                    "Treino  1",
                    "Treino  2",
                    "Treino  3",
                    "Treino  4",
                    "Treino  5",
                    "Treino  6"
            };
                break;
        }

        exercicioAtual.setText(String.valueOf(listaTreino[exercicio]));
        proximoExercicio.setText(String.valueOf(listaTreino[exercicio + 1]));

        object = new TextToSpeech(TreinoRunIntermediario.this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    result = object.setLanguage(Locale.ENGLISH);
                    object.speak("Get Ready", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    Toast.makeText(getApplicationContext(), "Não suporta VoiceSpeech", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void clicaBotao (View view){
        if(!estadoTreino){
            estadoTreino = true;
            btnIniciar.setText("PAUSE");
            nextExercise();
        } else {
            estadoTreino = false;
            btnIniciar.setText("CONTINUE");
            stopTempo();
        }
    }

    public void stopTempo(){
        countDownTimer.cancel();
    }

    public void atualizaLabelTempo(int secondsLeft) {
        if (secondsLeft < 4 && !acabandoTempo){
            acabandoTempo = true;
            object.speak("3 seconds remind", TextToSpeech.QUEUE_FLUSH, null);
        }
        tempo.setText(Integer.toString(secondsLeft));
    }


    public void nextExercise(){
        object.speak("Start", TextToSpeech.QUEUE_FLUSH, null);
        acabandoTempo = false;
        progressBarTempo.setProgress(progressBarTempo.getProgress()+1);
        //tempo de exercicio * 1000
        countDownTimer = new CountDownTimer(tempoExercicio * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                atualizaLabelTempo ((int) millisUntilFinished / 1000 );
            }
            @Override
            public void onFinish() {
                if(exercicio < listaTreino.length){
                    iniciaDescanso();
                } else {
                    acabouTreino();
                }
            }
        }.start();

        fundoTela.setBackgroundColor(Color.parseColor("#373737"));
        exercicioAtual.setText(String.valueOf(listaTreino[exercicio]));
        if(exercicio<listaTreino.length-1){
            proximoExercicio.setText(String.valueOf(listaTreino[exercicio+1]));
        } else {
            proximoExercicio.setText("...");
        }
        tempo.setText(String.valueOf(tempoExercicio));
        exercicio++;
    }

    public void iniciaDescanso() {
        object.speak("Rest!", TextToSpeech.QUEUE_FLUSH, null);
        acabandoTempo = false;
        fundoTela.setBackgroundColor(Color.parseColor("#567EC3"));
        exercicioAtual.setText("Descansar");
        tempoExercicio = tempoDescanso;
        progressBarTempo.setProgress(progressBarTempo.getProgress() + 1);
        countDownTimer = new CountDownTimer(tempoExercicio * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                atualizaLabelTempo((int) millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                nextExercise();
            }
        }.start();

    }

    public void acabouTreino(){
        exercicioAtual.setText("Treino finalizado");
        fundoTela.setBackgroundColor(Color.parseColor("#373737"));
        btnIniciar.setEnabled(false);
        exercicioAtual.setAlpha(0);
        proximoExercicio.setAlpha(0);
        tempo.setAlpha(0);
        textoProximoExercicio.setText("Treino concluído");
    }

    public void onDestroy (){
        super.onDestroy();
        if(object != null){
            object.stop();
            object.shutdown();
        }
    }

    public void adicionarTreino(View view){

        dataTreino = "" + finalDate + " - " + treinoEscolhido;
        Toast.makeText(TreinoRunIntermediario.this, "" + dataTreino, Toast.LENGTH_SHORT).show();

        bancoTreino = openOrCreateDatabase("bancoTreino", MODE_PRIVATE, null);
        bancoTreino.execSQL("CREATE TABLE IF NOT EXISTS meustreinos(id INTEGER PRIMARY KEY AUTOINCREMENT, treino VARCHAR)");

        Toast.makeText(TreinoRunIntermediario.this, "Treino salvo ", Toast.LENGTH_SHORT).show();
        bancoTreino.execSQL("INSERT INTO meustreinos(treino) VALUES('"+ dataTreino +"') ");
    }
}
