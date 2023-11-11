package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
        TextView easterEgg = findViewById(R.id.easterEgg);
        registerForContextMenu(easterEgg);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(R.drawable.coin);
        menu.setHeaderTitle("Escoge bien");
        getMenuInflater().inflate(R.menu.menu_principal,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mpOp1:
                Toast.makeText(this, "Pulsa la Opción 4 en el submenu", Toast.LENGTH_LONG).show();
                return true;
            case R.id.mpOp2:
                Toast.makeText(this, "Pulsa la Opción 3 seleccionada", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mpOp3:
                Toast.makeText(this, "Enhorabuena tu vida tiene un poco menos de sentido", Toast.LENGTH_LONG).show();
                return true;

            case R.id.mpOp4:
                Toast.makeText(this, "Pulsa la Opción 2 seleccionada", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void jugar(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void salir(View v){
        finish();

    }

}