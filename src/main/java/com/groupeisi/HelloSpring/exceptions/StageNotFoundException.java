package com.groupeisi.HelloSpring.exceptions;

public class StageNotFoundException extends RuntimeException {

    public StageNotFoundException(Long id) {
        super("Stage introuvable avec l'id : " + id);
    }
}
