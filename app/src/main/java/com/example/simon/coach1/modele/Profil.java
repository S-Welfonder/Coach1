package com.example.simon.coach1.modele;

import com.example.simon.coach1.outils.Serializer;

import java.io.Serializable;

/**
 * Created by Simon on 30/12/2017.
 */

public class Profil implements Serializable {

    // constantes

    private static final Integer minFemmme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme = 25;



    // propriétées

    private int poids;
    private int taille;
    private int age;
    private int sexe;
    private float img;
    private String message;

    /**
     *  Formule de calcul de l'Img
     */
    private void calculIMG() {
        float tailleEnM = ((float)taille)/100;
        this.img = (float) ((1.2*poids/(tailleEnM*tailleEnM))+ (0.23*age) - (10.83*sexe )- 5.4 ) ;
    }

    /**
     * Fournit le message correspondant en fonction de l'Img
     */
    private void resultIMG() {
        if ( sexe == 0) {
            if (img < minFemmme) {
                message = "Trop faible";
            }
            else if (img >= minFemmme && img < maxFemme) {
                message = "Normal";
            }
            else if ( img > maxFemme) {
                message = "Trop élevé";
            }

        }

        else {
            if (img < minHomme) {
                message = "Trop faible";
            }
            else if (img >= minHomme && img < maxHomme) {
                message = "Normal";
            }
            else if ( img > maxHomme) {
                message = "Trop élevé";
            }
        }
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public Profil(int poids, int taille, int age, int sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    /**
     *
     * @return poids
     */
    public int getPoids() {
        return poids;
    }

    /**
     *
     * @return taille
     */
    public int getTaille() {
        return taille;
    }

    /**
     *
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     *
     * @return sexe
     */
    public int getSexe() {
        return sexe;
    }

    /**
     *
     * @return img
     */
    public float getImg() {
        return img;
    }

    /**
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }




}


