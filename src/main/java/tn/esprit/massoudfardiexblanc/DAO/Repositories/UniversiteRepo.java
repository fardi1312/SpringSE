package tn.esprit.massoudfardiexblanc.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Universite;

import java.time.LocalDate;
import java.util.List;

public interface UniversiteRepo extends JpaRepository<Universite, Long> {

    Universite findByNomUniversite(String nomUniversite);
}
