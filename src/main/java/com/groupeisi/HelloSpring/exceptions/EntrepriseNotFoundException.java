package com.groupeisi.HelloSpring.exceptions;

public class EntrepriseNotFoundException extends RuntimeException {

    public EntrepriseNotFoundException(String raisonSociale) {
        super("Entreprise introuvable : " + raisonSociale);
    }
}
