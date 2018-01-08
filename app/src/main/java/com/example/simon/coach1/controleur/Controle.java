package com.example.simon.coach1.controleur;

import com.example.simon.coach1.modele.Profil;

/**
 * Created by Simon on 07/01/2018.
 */

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;


    private  Controle() {
        super();
    }

    /**
     *
     * @return une instance du controleur, en créé une nouvelle s'il n'y en a pas
     */
    public final static Controle getInstance() {
        if ( Controle.instance == null) {
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     *  Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(int poids, int taille, int age, int sexe) {
        profil = new Profil(poids, taille, age, sexe);
    }

    /**
     *
     * @return l'img du profil
     */
    public float getImg() {
        return profil.getImg();
    }

    /**
     *
     * @return le message correspondant au profil
     */
    public String getMessage() {
        return profil.getMessage();
    }
}
