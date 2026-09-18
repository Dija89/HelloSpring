package com.groupeisi.HelloSpring.controllers;

import com.groupeisi.HelloSpring.entities.Entreprise;
import com.groupeisi.HelloSpring.services.EntrepriseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/entreprises")
@Tag(
        name = "Entreprises",
        description = "API de gestion des entreprises"
)
public class EntrepriseController {

    private final EntrepriseService entrepriseService;


    @Operation(
            summary = "Lister les entreprises",
            description = "Retourne la liste de toutes les entreprises enregistrées"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des entreprises récupérée avec succès"
    )
    @GetMapping
    public List<Entreprise> getAllEntreprises() {

        log.info("Liste des entreprises");

        return entrepriseService.findAll();
    }


    @Operation(
            summary = "Rechercher une entreprise",
            description = "Recherche une entreprise à partir de sa raison sociale"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Entreprise trouvée"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entreprise introuvable"
            )
    })
    @GetMapping("/{raisonSociale}")
    public Entreprise getEntreprise(
            @PathVariable String raisonSociale) {

        log.info(
                "Recherche entreprise : {}",
                raisonSociale
        );

        return entrepriseService
                .findByRaisonSociale(raisonSociale);
    }


    @Operation(
            summary = "Créer une entreprise",
            description = "Crée une nouvelle entreprise dans la base de données"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Entreprise créée avec succès"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Entreprise create(
            @RequestBody Entreprise entreprise) {

        log.info(
                "Création entreprise : {}",
                entreprise.getRaisonSociale()
        );

        return entrepriseService.create(entreprise);
    }


    @Operation(
            summary = "Modifier une entreprise",
            description = "Modifie une entreprise existante à partir de sa raison sociale"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Entreprise modifiée avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entreprise introuvable"
            )
    })
    @PutMapping("/{raisonSociale}")
    public Entreprise update(
            @PathVariable String raisonSociale,
            @RequestBody Entreprise entreprise) {

        log.info(
                "Modification entreprise : {}",
                raisonSociale
        );

        return entrepriseService.update(
                raisonSociale,
                entreprise
        );
    }


    @Operation(
            summary = "Supprimer une entreprise",
            description = "Supprime une entreprise à partir de sa raison sociale"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Entreprise supprimée avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Entreprise introuvable"
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{raisonSociale}")
    public void delete(
            @PathVariable String raisonSociale) {

        log.info(
                "Suppression entreprise : {}",
                raisonSociale
        );

        entrepriseService.delete(raisonSociale);
    }
}
