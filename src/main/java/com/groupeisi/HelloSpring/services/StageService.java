package com.groupeisi.HelloSpring.services;

import com.groupeisi.HelloSpring.entities.Stage;
import com.groupeisi.HelloSpring.repositories.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StageService {

    private final StageRepository stageRepository;


    // Lister tous les stages
    public List<Stage> findAll() {
        return stageRepository.findAll();
    }


    // Rechercher un stage par son id
    public Optional<Stage> findById(Long id) {
        return stageRepository.findById(id);
    }


    // Créer un nouveau stage
    public Stage create(Stage stage) {
        return stageRepository.save(stage);
    }


    // Modifier un stage existant
    public Stage update(Long id, Stage stage) {

        Optional<Stage> stageBd =
                stageRepository.findById(id);

        if (stageBd.isPresent()) {

            Stage stageExistant =
                    stageBd.get();

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

            return stageRepository.save(
                    stageExistant
            );
        }

        return null;
    }


    // Supprimer un stage
    public void delete(Long id) {
        stageRepository.deleteById(id);
    }
}
