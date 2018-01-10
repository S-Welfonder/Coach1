package com.example.simon.coach1;

import android.content.Context;
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

    // propriétés
    private EditText txtPoids ;
    private EditText txtTaille ;
    private EditText txtAge ;
    private TextView lblIMG ;
    private RadioButton rdHomme ;
    private RadioButton rdFemme ;
    private ImageView imgSmiley ;
    private Controle controle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Initialise les objets graphiques
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids) ;
        txtTaille = (EditText) findViewById(R.id.txtTaille) ;
        txtAge = (EditText) findViewById(R.id.txtAge) ;
        lblIMG = (TextView) findViewById(R.id.lblIMG) ;
        rdHomme = (RadioButton) findViewById(R.id.rdHomme) ;
        rdFemme = (RadioButton) findViewById(R.id.rdFemme) ;
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley) ;
        // création du controleur
        controle = Controle.getInstance(this) ;
        // gestion de l'événement sur le bouton calcul
        ecouteCalcul();
        // recupération du profil sérialisé
         recupProfil();

    }

    /**
     * Gestion de l'événement sur le bouton calcul
     */
    private void ecouteCalcul() {
        ((Button) findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
                Integer poids = 0 ;
                Integer taille = 0 ;
                Integer age = 0 ;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                }catch(Exception e){}
                int sexe = 0 ;
                if (rdHomme.isChecked()) {
                    sexe = 1 ;
                }
                // contrôle si tous les champs sont remplis
                if (poids==0 || taille==0 || age==0) {
                    Toast.makeText(MainActivity.this, "Veuillez saisir tous les champs", Toast.LENGTH_SHORT).show();
                }else{
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    /**
     * Affiche le résultat des mesures (image et img)
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe) {
        // envoi des informations au contrôleur pouir créer le profil
        controle.creerProfil(poids, taille, age, sexe, this);
        // récupération du calcul de l'img et du message
        String message = controle.getMessage();
        float img = controle.getImg();
        // choix de l'image et de la couleur en fonction du message
        lblIMG.setTextColor(Color.GREEN);
        imgSmiley.setImageResource(R.drawable.normal);
        if(message.equals("Trop faible")){
            lblIMG.setTextColor(Color.RED);
            imgSmiley.setImageResource(R.drawable.maigre);
        }else{
            if(message.equals("Trop élevé")) {
                lblIMG.setTextColor(Color.RED);
                imgSmiley.setImageResource(R.drawable.graisse);
            }
        }
        // construction du message complet à afficher
        lblIMG.setText(String.format("%.01f", img) + " : IMG " + message);
    }

    /**
     * Récupération d'un profil sérialisé
     */
    private void recupProfil() {
        if(controle.getTaille()!=null) {
            txtTaille.setText(""+controle.getTaille());
            txtPoids.setText(""+controle.getPoids());
            txtAge.setText(""+controle.getAge());
            rdHomme.setChecked(controle.getSexe()==1);
            rdFemme.setChecked(controle.getSexe()==0);
            // déclenchement du calcul de l'IMG
            ((Button) findViewById(R.id.btnCalc)).performClick();
        }
    }

}
