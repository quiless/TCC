package com.quiles.workout;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jehwl on 06/03/2017.
 */

public class TelaVideosIntermediario extends AppCompatActivity {

    WebView video;
    TextView titulo;
    TextToSpeech object;
    int treinoSelecionado2;
    String url;
    int exercicioSelecionado2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_videos);

        video = (WebView) findViewById(R.id.webViewVideo);
        titulo = (TextView) findViewById(R.id.txt_nomeExercicio);
        treinoSelecionado2 = getIntent().getExtras().getInt("numeroTreino");
        exercicioSelecionado2 = getIntent().getExtras().getInt("posicao");

        video.setWebViewClient(new MyBrowser());
        video.getSettings().setJavaScriptEnabled(true);

        switch (treinoSelecionado2){
            case 0:
                exercicio1();
                break;
            case 1:
                exercicio2();
                break;
            default:
                padrao();
                break;
        }

        video.loadUrl("https://www.youtube.com/embed/" + url + "?autoplay=1&vq=small");
        video.setWebChromeClient(new WebChromeClient());

        String nomeVideo = getIntent().getExtras().getString("nomeVideo");
        titulo.setText(nomeVideo);

        object = new TextToSpeech(TelaVideosIntermediario.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    object.speak("Watch the videos and start your workout", TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    Toast.makeText(getApplicationContext(), "Não suporta VoiceToSpeech", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class MyBrowser extends WebViewClient {
        public boolean overrideUrlLoading (WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

    public void voltar(View view) {
        Intent intent = new Intent(TelaVideosIntermediario.this, ExerciciosIntermediarios.class);
        intent.putExtra("numeroTreino", treinoSelecionado2);
        startActivity(intent);
    }

    public void exercicio1(){
        switch (exercicioSelecionado2){
            case 0:
                url = "VolWlxfoczo";
                break;
            case 1:
                url = "mKT5HrkozcI";
                break;
            case 2:
                url = "HokyEdjb2HU";
                break;
            case 3:
                url = "klilwY6hEQk";
                break;
            case 4:
                url = "3y3NmaH66A4";
                break;
            case 5:
                url = "zf7eHxhsMsk";
                break;
            default:
                url = "zf7eHxhsMsk";
                break;
        }
    }

    public void exercicio2(){
        switch (exercicioSelecionado2){
            case 0:
                url = "qLX_EciuNvk";
                break;
            case 1:
                url = "bZT-yi1toRU";
                break;
            case 2:
                url = "neotzvhn7Qw";
                break;
            case 3:
                url = "YFCZWaYiJ0U";
                break;
            case 4:
                url = "3y3NmaH66A4";
                break;
            case 5:
                url = "zf7eHxhsMsk";
                break;
            default:
                url = "zf7eHxhsMsk";
                break;
        }
    }

    public void padrao(){
        Toast.makeText(getApplicationContext(), "Exercicio3 Selecionado", Toast.LENGTH_SHORT).show();
    }
}

