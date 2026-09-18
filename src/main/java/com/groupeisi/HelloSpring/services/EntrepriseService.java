package com.groupeisi.HelloSpring.services;

import com.groupeisi.HelloSpring.entities.Entreprise;
import com.groupeisi.HelloSpring.exceptions.EntrepriseNotFoundException;
import com.groupeisi.HelloSpring.repositories.EntrepriseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;


    // Lister toutes les entreprises
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }


    // Rechercher une entreprise par sa raison sociale
    public Entreprise findByRaisonSociale(String raisonSociale) {

        return entrepriseRepository
                .findById(raisonSociale)
                .orElseThrow(
                        () -> new EntrepriseNotFoundException(
                                raisonSociale
                        )
                );
    }


    // Créer une entreprise
    public Entreprise create(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }


    // Modifier une entreprise
    public Entreprise update(
            String raisonSociale,
            Entreprise entreprise) {

        Entreprise entrepriseExistante =
                entrepriseRepository
                        .findById(raisonSociale)
                        .orElseThrow(
                                () -> new EntrepriseNotFoundException(
                                        raisonSociale
                                )
                        );


        entrepriseExistante.setSecteurActivite(
                entreprise.getSecteurActivite()
        );

        entrepriseExistante.setAdresse(
                entreprise.getAdresse()
        );

        entrepriseExistante.setEmail(
                entreprise.getEmail()
        );

        entrepriseExistante.setTelephone(
                entreprise.getTelephone()
        );


        return entrepriseRepository.save(
                entrepriseExistante
        );
    }


    // Supprimer une entreprise
    public void delete(String raisonSociale) {

        Entreprise entreprise =
                entrepriseRepository
                        .findById(raisonSociale)
                        .orElseThrow(
                                () -> new EntrepriseNotFoundException(
                                        raisonSociale
                                )
                        );

        entrepriseRepository.delete(entreprise);
    }
}
