package com.groupeisi.HelloSpring.controllers;

import com.groupeisi.HelloSpring.entities.Stage;
import com.groupeisi.HelloSpring.services.StageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/stages")
@Tag(
        name = "Stages",
        description = "API de gestion des stages"
)
public class StageController {

    private final StageService stageService;


    @Operation(
            summary = "Lister les stages",
            description = "Retourne la liste de tous les stages"
    )
    @GetMapping
    public List<Stage> getAllStages() {

        log.info("Liste des stages");

        return stageService.findAll();
    }


    @Operation(
            summary = "Rechercher un stage",
            description = "Recherche un stage à partir de son identifiant"
    )
    @GetMapping("/{id}")
    public Stage getStage(
            @PathVariable Long id) {

        log.info(
                "Recherche du stage avec l'id : {}",
                id
        );

        return stageService.findById(id);
    }


    @Operation(
            summary = "Créer un stage",
            description = "Crée un nouveau stage"
    )
    @PostMapping
    public Stage create(
            @RequestBody Stage stage) {

        log.info(
                "Création du stage : {}",
                stage.getSujetDefinitif()
        );

        return stageService.create(stage);
    }


    @Operation(
            summary = "Modifier un stage",
            description = "Modifie un stage existant à partir de son identifiant"
    )
    @PutMapping("/{id}")
    public Stage update(
            @PathVariable Long id,
            @RequestBody Stage stage) {

        log.info(
                "Modification du stage avec l'id : {}",
                id
        );

        return stageService.update(
                id,
                stage
        );
    }


    @Operation(
            summary = "Supprimer un stage",
            description = "Supprime un stage à partir de son identifiant"
    )
    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        log.info(
                "Suppression du stage avec l'id : {}",
                id
        );

        stageService.delete(id);
    }
}
