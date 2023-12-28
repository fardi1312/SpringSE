package tn.esprit.massoudfardiexblanc.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Foyer;


import java.util.List;

public interface FoyerRepo extends JpaRepository<Foyer,Long> {
    Foyer findByNomFoyer(String nomFoyer);
}
