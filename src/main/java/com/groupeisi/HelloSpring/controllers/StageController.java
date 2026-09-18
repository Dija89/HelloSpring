package com.groupeisi.HelloSpring.controllers;

import com.groupeisi.HelloSpring.entities.Stage;
import com.groupeisi.HelloSpring.services.StageService;

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
@RequestMapping("/stages")
@Tag(
        name = "Stages",
        description = "API de gestion des stages"
)
public class StageController {

    private final StageService stageService;


    @Operation(
            summary = "Lister les stages",
            description = "Retourne la liste de tous les stages enregistrés"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des stages récupérée avec succès"
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Stage trouvé"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Stage introuvable"
            )
    })
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
            description = "Crée un nouveau stage dans la base de données"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Stage créé avec succès"
    )
    @ResponseStatus(HttpStatus.CREATED)
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Stage modifié avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Stage introuvable"
            )
    })
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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Stage supprimé avec succès"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Stage introuvable"
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
