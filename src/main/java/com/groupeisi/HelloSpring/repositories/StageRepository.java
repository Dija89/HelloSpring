package com.groupeisi.HelloSpring.repositories;

import com.groupeisi.HelloSpring.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository
        extends JpaRepository<Stage, Long> {

}
