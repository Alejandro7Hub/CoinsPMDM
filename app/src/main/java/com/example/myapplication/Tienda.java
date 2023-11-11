package com.example.myapplication;




import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;







import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tienda extends AppCompatActivity {

    TextView contador;
    Button compra1;
    Button compra2;
    Button compra3;
    Button compra4;

    Button compraIncremento;
    double suma;
    double click = 1;
    double contpesao = 0;
    double costeMoneda1;
    double costeMoneda2;
    double costeMoneda4;
    double costeIncremento;
    double valorAlto = 0;
    DecimalFormat df = new DecimalFormat("#.00");
    DecimalFormat dfbajo = new DecimalFormat("#");
    Toast toast; // Declarar la variable Toast fuera del bloque condicional
    double incremento;

    String[] nombres={"Pila de Monedas"};
    int[] imagenes={R.drawable.coins};
    int[] boton={R.id.buttonPilaDeMonedas2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        View segundoLayout = LayoutInflater.from(this).inflate(R.layout.fila, null);

        compra4 = segundoLayout.findViewById(R.id.buttonPilaDeMonedas2);

        contador = findViewById(R.id.contadorTienda);
        compra1 = findViewById(R.id.buttonPilaDeMonedas1);
        compra2 = findViewById(R.id.buttonMontonDeMonedas);
        compra3 = findViewById(R.id.buttonIncremento);
        compraIncremento = findViewById(R.id.buttonIncremento);



        RecyclerView rv=findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(new AdaptadorFila());





        Bundle mejoras =getIntent().getExtras();
        click = mejoras.getDouble("click");
        suma = mejoras.getDouble("contadorValor");
        costeMoneda1 = mejoras.getDouble("mejora1");
        costeMoneda2 = mejoras.getDouble("mejora2");
        costeMoneda4 = mejoras.getDouble("mejora4");
        costeIncremento = mejoras.getDouble("mejora3");;
        incremento = mejoras.getDouble("incremento");

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
        compra2.setText(String.valueOf(dfbajo.format(costeMoneda2)) + " Monedas");
        compra3.setText(String.valueOf(dfbajo.format(costeIncremento)) + " Monedas");
        compra4.setText(String.valueOf(dfbajo.format(costeMoneda4)) + " Monedas");
        incTemporal();
    }



    public class AdaptadorFila extends RecyclerView.Adapter<AdaptadorFila.AdaptadorFilaHolder> {

        @NonNull
        @Override
        public AdaptadorFilaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorFilaHolder(getLayoutInflater().inflate(R.layout.fila, parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorFilaHolder holder, int position) {
            holder.imprimir(position);
        }
        @Override
        public int getItemCount() {
            return nombres.length;
        }
        private class AdaptadorFilaHolder extends RecyclerView.ViewHolder{
            ImageView iv1;
            TextView tv1;
            public AdaptadorFilaHolder(@NonNull View v){
                super(v);
                iv1=v.findViewById(R.id.imageView4);
                tv1=v.findViewById(R.id.textoCompra4);
                compra4= v.findViewById(R.id.buttonPilaDeMonedas2);

            }
            public void imprimir(int position){
                iv1.setImageResource(imagenes[position]);
                tv1.setText(nombres[position]);
                compra4.setText(boton[position]);
            }
        }









    }

    public void Volver(View v){
        Intent intent = new Intent(this, MainActivity.class);
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

    public void mejora4(View v) {
        if (suma >= costeMoneda4) {
            suma = suma - costeMoneda4;
            contador.setText(String.valueOf(suma));
            costeMoneda4 = costeMoneda4 + 20;
            click++;

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
            compra4.setText(String.valueOf(dfbajo.format(costeMoneda4)) + " Monedas");
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



