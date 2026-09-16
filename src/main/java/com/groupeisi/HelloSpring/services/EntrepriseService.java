package com.groupeisi.HelloSpring.services;

import com.groupeisi.HelloSpring.entities.Entreprise;
import com.groupeisi.HelloSpring.repositories.EntrepriseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;


    // Lister toutes les entreprises
    public List<Entreprise> findAll() {
        return entrepriseRepository.findAll();
    }


    // Rechercher une entreprise par sa raison sociale
    public Optional<Entreprise> findByRaisonSociale(String raisonSociale) {
        return entrepriseRepository.findById(raisonSociale);
    }


    // Créer une nouvelle entreprise
    public Entreprise create(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }


    // Modifier une entreprise existante
    public Entreprise update(
            String raisonSociale,
            Entreprise entreprise) {

        Optional<Entreprise> entrepriseBd =
                entrepriseRepository.findById(raisonSociale);

        if (entrepriseBd.isPresent()) {

            Entreprise entrepriseExistante =
                    entrepriseBd.get();

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

        return null;
    }


    // Supprimer une entreprise
    public void delete(String raisonSociale) {
        entrepriseRepository.deleteById(raisonSociale);
    }
}
