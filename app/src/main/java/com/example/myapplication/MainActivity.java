package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Log.e("Localizador", extra.getDouble("Clik");
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_TIENDA = 1;
    TextView contador;

    double suma;
    double click;
    double costeMoneda1;
    double costeMoneda2;
    double costeMoneda4;
    double costeIncremento;
    double contpesao = 0;
    double valorAlto = 0;
    DecimalFormat df = new DecimalFormat("#.00");
    DecimalFormat dfbajo = new DecimalFormat("#");
    double incremento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador = findViewById((R.id.contador));



        Bundle datosTienda = getIntent().getExtras();
        if(datosTienda==null){
            click=1;
            suma=800;
            costeMoneda1=100;
            costeMoneda2=200;
            costeMoneda4=100;
            costeIncremento=300;
        }else{
            suma = datosTienda.getDouble("contadorValor");
            costeMoneda1 = datosTienda.getDouble("mejora1");
            costeMoneda2 = datosTienda.getDouble("mejora2");
            costeMoneda4 = datosTienda.getDouble("mejora4");
            costeIncremento = datosTienda.getDouble("mejora3");
            incremento = datosTienda.getDouble("incremento");
            click = datosTienda.getDouble("click");

            if (suma >= 1000000) {
                valorAlto = suma / 1000000;
                contador.setText(String.valueOf(valorAlto) + " M");
            } else if (suma>=1010){
                valorAlto = suma / 1000;
                contador.setText(String.valueOf(df.format(valorAlto)) + " K");
            }else if (suma >= 1000) {
                valorAlto = suma / 1000;
                contador.setText(String.valueOf(dfbajo.format(valorAlto)) + " K");
            } else {
                contador.setText(String.valueOf(dfbajo.format(suma)));
            }
        }

        incTemporal(); // Mover la llamada aquí después de la inicialización de contador
    }

    public void Volver(View v){finish();}
    public void IrTienda(View v){
        Intent intent = new Intent(this, Tienda.class);
        intent.putExtra("contadorValor",suma);
        intent.putExtra("click",click);
        intent.putExtra("mejora1",costeMoneda1);
        intent.putExtra("mejora2",costeMoneda2);
        intent.putExtra("mejora4",costeMoneda4);
        intent.putExtra("mejora3",costeIncremento);
        intent.putExtra("incremento",incremento);
        startActivity(intent);
        finish();
    }



    public void sumar(View v) {
        suma = suma + click;
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.click_moneda);
        v.startAnimation(animation);
        if (suma >= 1000000) {
            valorAlto = suma / 1000000;
            contador.setText(String.valueOf(df.format(valorAlto)) + " M");
        } else if (suma>=1010){
            valorAlto = suma / 1000;
            contador.setText(String.valueOf(df.format(valorAlto)) + " K");
        }else if (suma >= 1000) {
            valorAlto = suma / 1000;
            contador.setText(String.valueOf(dfbajo.format(valorAlto)) + " K");
        } else {
            contador.setText(String.valueOf(dfbajo.format(suma)));
        }
        contpesao = 0;
    }

    public void incTemporal() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            // Realiza algún trabajo en el hilo en segundo plano aquí
            while (true){

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                suma=suma+ incremento;
                handler.post(() ->{
                    // Actualiza la interfaz de usuario en el hilo principal aquí
                    // Por ejemplo, puedes modificar vistas o realizar otras operaciones en la UI.
                    contador.setText(String.valueOf(suma));
                    if (suma >= 1000000) {
                        valorAlto = suma / 1000000;
                        contador.setText(String.valueOf(df.format(valorAlto)) + " M");
                    } else if (suma>=1010){
                        valorAlto = suma / 1000;
                        contador.setText(String.valueOf(df.format(valorAlto)) + " K");
                    }else if (suma >= 1000) {
                        valorAlto = suma / 1000;
                        contador.setText(String.valueOf(dfbajo.format(valorAlto)) + " K");
                    } else  {
                        contador.setText(String.valueOf(dfbajo.format(suma)));
                    }
                });
            }


        });
    }
}
