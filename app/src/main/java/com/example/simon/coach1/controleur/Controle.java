package com.example.simon.coach1.controleur;

import android.content.Context;

import com.example.simon.coach1.modele.Profil;
import com.example.simon.coach1.outils.Serializer;

import static com.example.simon.coach1.outils.Serializer.serialize;

/**
 * Created by Simon on 07/01/2018.
 */

public final class Controle {

    private static Controle instance = null;
    private static Profil profil;
    private static String nomFic = "saveProfil";


    private  Controle() {
        super();
    }

    /**
     *
     * @return une instance du controleur, en créé une nouvelle s'il n'y en a pas
     */
    public final static Controle getInstance(Context contexte) {
        if ( Controle.instance == null) {
            Controle.instance = new Controle();
            recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**
     *  Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     * @param contexte
     */
    public void creerProfil(int poids, int taille, int age, int sexe, Context contexte
    ) {
        profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize(nomFic,profil,contexte);
    }

    private static void recupSerialize(Context contexte) {
        profil = (Profil)Serializer.deSerialize(nomFic, contexte);
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
     * @return le message correspondant au profil, null s'il n'y en a pas
     */
    public String getMessage() {
        if (profil == null) {
            return null;
        } else {
            return profil.getMessage();
        }
    }

    /**
     *
     * @return null si la taille est null sinon retourne la taille
     */
    public Integer getTaille() {
        if ( profil == null) {
            return null;
        }
        else {
            return profil.getTaille();
        }
    }

    /**
     *
     * @return null si le poids est null, sinon retourne le poids
     */
    public Integer getPoids() {
        if ( profil == null) {
            return null;
        }
        else {
            return profil.getPoids();
        }
    }

    /**
     *
     * @return null si l'age est null, sinon retourne l'age
     */
    public Integer getAge() {
        if ( profil == null) {
            return null;
        }
        else {
            return profil.getAge();
        }
    }

    /**
     *
     * @return null si le profil est null, sinon retourne le sexe
     */
    public Integer getSexe() {
        if ( profil == null) {
            return null;
        }
        else {
            return profil.getSexe();
        }
    }


}
