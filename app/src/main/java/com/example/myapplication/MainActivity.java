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


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_TIENDA = 1;
    TextView contador;
    double suma=800;
    double click = 1;
    double contpesao = 0;
    double valorAlto = 0;
    DecimalFormat df = new DecimalFormat("#.00");
    DecimalFormat dfbajo = new DecimalFormat("#");
    double incremento=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contador = findViewById(R.id.contador);
        incTemporal(); // Mover la llamada aquí después de la inicialización de contador

    }

    public void Volver(View v){
        finish();
    }
    public void IrTienda(View v){
        Intent intent = new Intent(this, Tienda.class);
        double valorEnviar= suma;
        intent.putExtra("contadorValor",valorEnviar);

        //Con esto recogemos el resultado envidado desde la tienda. startActivityForResult nos envia los datos de vuelta cuando Tienda se cierra.
        Intent intent2 = new Intent(this, Tienda.class);
        startActivityForResult(intent, REQUEST_CODE_TIENDA);
    }

    //Este metodo lo utilizamos para manejar los resultados devueltos por la actividad tienda que fue lanzada con startActivityForResult().
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_TIENDA && resultCode == RESULT_OK) {
            if (data.hasExtra("contadorValorActualizado")) {
                suma = data.getDoubleExtra("contadorValorActualizado", suma);
                // Actualizar el TextView del contador en MainActivity
                contador.setText(String.valueOf(suma));
            }
        }
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
