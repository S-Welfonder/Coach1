package com.example.simon.coach1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.simon.coach1.controleur.Controle;
import com.example.simon.coach1.modele.Profil;

public class MainActivity extends AppCompatActivity {

    private EditText txtPoids;
    private EditText txtAge;
    private EditText txtTaille;
    private TextView lblIMG;
    private RadioButton rdHomme;
    private ImageView imgSmiley;
    private Controle controle;

    /**
     * Evenement sur le bouton calculer
     */
    private void ecouteCalcul() {
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int poids = 0;
                int taille = 0;
                int age = 0;
                int sexe = 0;
                float img;
                String message;


                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e) {
                }
                if ( rdHomme.isChecked()) {
                    sexe = 1;
                }
                if ( poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }
                else {
                    controle.creerProfil(poids, taille, age, sexe);
                    img = controle.getImg();
                    message = controle.getMessage();
                    if ( message == "Normal") {
                        imgSmiley.setImageResource(R.drawable.normal);
                        lblIMG.setTextColor(Color.GREEN);
                    }
                    else if ( message == "Trop élevé") {
                        imgSmiley.setImageResource(R.drawable.graisse);
                        lblIMG.setTextColor(Color.RED);

                    }
                    else {
                        imgSmiley.setImageResource(R.drawable.maigre);
                        lblIMG.setTextColor(Color.RED);

                    }
                    lblIMG.setText(String.format("%.01f", img) + " : "+ message);

                }
            }
        });
    }






    /**
     * Méthode de récupération des objets graphiques
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtAge = (EditText) findViewById(R.id.txtAge);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        ecouteCalcul();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("test", "test");
        init();
        controle = Controle.getInstance();
    }



}
