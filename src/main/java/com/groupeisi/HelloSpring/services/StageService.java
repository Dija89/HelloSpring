package com.groupeisi.HelloSpring.services;

import com.groupeisi.HelloSpring.entities.Stage;
import com.groupeisi.HelloSpring.exceptions.StageNotFoundException;
import com.groupeisi.HelloSpring.repositories.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StageService {

    private final StageRepository stageRepository;


    // Lister tous les stages
    public List<Stage> findAll() {
        return stageRepository.findAll();
    }


    // Rechercher un stage par son id
    public Stage findById(Long id) {

        return stageRepository
                .findById(id)
                .orElseThrow(
                        () -> new StageNotFoundException(id)
                );
    }


    // Créer un stage
    public Stage create(Stage stage) {
        return stageRepository.save(stage);
    }


    // Modifier un stage
    public Stage update(Long id, Stage stage) {

        Stage stageExistant =
                stageRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new StageNotFoundException(id)
                        );


        stageExistant.setSujetDefinitif(
                stage.getSujetDefinitif()
        );

        stageExistant.setDateDebut(
                stage.getDateDebut()
        );

        stageExistant.setDateFin(
                stage.getDateFin()
        );

        stageExistant.setStatut(
                stage.getStatut()
        );


        return stageRepository.save(stageExistant);
    }


    // Supprimer un stage
    public void delete(Long id) {

        Stage stage =
                stageRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new StageNotFoundException(id)
                        );

        stageRepository.delete(stage);
    }
}
