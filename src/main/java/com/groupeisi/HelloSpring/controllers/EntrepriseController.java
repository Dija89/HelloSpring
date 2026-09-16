package com.groupeisi.HelloSpring.controllers;

import com.groupeisi.HelloSpring.entities.Entreprise;
import com.groupeisi.HelloSpring.services.EntrepriseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @Operation(
            summary = "Liste des entreprises",
            description = "Retourne la liste de toutes les entreprises"
    )
    @GetMapping
    public List<Entreprise> getAllEntreprises() {
        return entrepriseService.findAll();
    }

    @GetMapping("/{raisonSociale}")
    public Entreprise getEntreprise(
            @PathVariable String raisonSociale) {

        log.info(
                "Recherche entreprise : {}",
                raisonSociale
        );

        Optional<Entreprise> entrepriseBd =
                entrepriseService.findByRaisonSociale(raisonSociale);

        if (entrepriseBd.isPresent()) {
            return entrepriseBd.get();
        }

        return null;
    }

    @PostMapping
    public Entreprise create(
            @RequestBody Entreprise entreprise) {

        log.info(
                "Création entreprise : {}",
                entreprise.getRaisonSociale()
        );

        return entrepriseService.create(entreprise);
    }

    @PutMapping("/{raisonSociale}")
    public Entreprise update(
            @PathVariable String raisonSociale,
            @RequestBody Entreprise entreprise) {

        log.info(
                "Modification entreprise : {}",
                raisonSociale
        );

        return entrepriseService.update(entreprise);
    }

    @DeleteMapping("/{raisonSociale}")
    public void delete(
            @PathVariable String raisonSociale) {

        entrepriseService.delete(raisonSociale);
    }
}
