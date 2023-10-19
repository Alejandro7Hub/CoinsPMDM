package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.text.BreakIterator;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    TextView contador;
    Button compra1;
    Button compra2;
    Button compra3;
    Button compraIncremento;
    double suma=800;
    double click = 1;
    double contpesao = 0;
    double costeMoneda1 = 100;
    double costeMoneda2 = 200;
    double costeIncremento=300;
    double valorAlto = 0;
    DecimalFormat df = new DecimalFormat("#.00");
    DecimalFormat dfbajo = new DecimalFormat("#");
    Toast toast; // Declarar la variable Toast fuera del bloque condicional
    double incremento=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador = findViewById(R.id.contador);
        compra1 = findViewById(R.id.button);
        compra2 = findViewById(R.id.buttonMontonDeMonedas);
        compra3 = findViewById(R.id.buttonIncremento);
        compraIncremento = findViewById(R.id.buttonIncremento);
        incTemporal(); // Mover la llamada aquí después de la inicialización de contador
    }
    public void volver(View v){
        onBackPressed();
    }
    public void onBackPressed() {
        super.onBackPressed(); // Llama al método onBackPressed de la superclase
    }



    public void sumar(View v) {
        suma = suma + click;
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

    public void mejora1(View v) {
        if (suma >= costeMoneda1) {
            suma = suma - costeMoneda1;
            click++;
            contador.setText(String.valueOf(suma));
            costeMoneda1 = costeMoneda1 + 20;
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
            compra1.setText(String.valueOf(dfbajo.format(costeMoneda1)) + " Monedas");
        } else {
            if (contpesao >= 3) {
                toast = Toast.makeText(this, "Que no hay pesao", Toast.LENGTH_LONG);
            } else {
                contpesao++;
                toast = Toast.makeText(this, "No hay....", Toast.LENGTH_SHORT);
            }
            toast.show(); // Mostrar el Toast
        }
    }
    public void mejora2(View v) {
        if (suma >= costeMoneda2) {
            suma = suma - costeMoneda2;
            click=click +4;
            contador.setText(String.valueOf(suma));
            costeMoneda2 = costeMoneda2 + 40;
            if (suma >= 1000000) {
                valorAlto = suma / 1000000;
                contador.setText(String.valueOf(valorAlto) + " M");
            } else if (suma>=1010){
                valorAlto = suma / 1000;
                contador.setText(String.valueOf(df.format(valorAlto)) + " K");
            }else if (suma >= 1000) {
                valorAlto = suma / 1000;
                contador.setText(String.valueOf(dfbajo.format(valorAlto)) + " K");
            } else  {
                contador.setText(String.valueOf(dfbajo.format(suma)));
            }
            compra2.setText(String.valueOf(dfbajo.format(costeMoneda2)) + " Monedas");
        } else {
            if (contpesao >= 3) {
                toast = Toast.makeText(this, "Que no hay pesao", Toast.LENGTH_LONG);
            } else {
                contpesao++;
                toast = Toast.makeText(this, "No hay....", Toast.LENGTH_SHORT);
            }
            toast.show(); // Mostrar el Toast
        }
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
    public void inc(View v){
        if (suma >= costeIncremento) {
            suma = suma - costeIncremento;
            incremento= incremento +2;
            contador.setText(String.valueOf(suma));
            costeIncremento = costeIncremento + 50;
            if (suma >= 1000000) {
                valorAlto = suma / 1000000;
                contador.setText(String.valueOf(valorAlto) + " M");
            } else if (suma>=1010){
                valorAlto = suma / 1000;
                contador.setText(String.valueOf(df.format(valorAlto)) + " K");
            }else if (suma >= 1000) {
                valorAlto = suma / 1000;
                contador.setText(String.valueOf(dfbajo.format(valorAlto)) + " K");
            } else  {
                contador.setText(String.valueOf(dfbajo.format(suma)));

            }
            compra3.setText(String.valueOf(dfbajo.format(costeIncremento)) + " Monedas");

        } else {
            if (contpesao >= 3) {
                toast = Toast.makeText(this, "Que no hay pesao", Toast.LENGTH_LONG);
            } else {
                contpesao++;
                toast = Toast.makeText(this, "No hay....", Toast.LENGTH_SHORT);
            }
            toast.show(); // Mostrar el Toast
        }
    }

}
