package tn.esprit.massoudfardiexblanc.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Etudiant;


import java.util.List;

public interface EtudiantRepo extends JpaRepository<Etudiant,Long> {

}
