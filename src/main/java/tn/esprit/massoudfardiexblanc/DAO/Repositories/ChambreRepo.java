package tn.esprit.massoudfardiexblanc.DAO.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Chambre;


import java.time.LocalDate;
import java.util.List;

public interface ChambreRepo extends JpaRepository<Chambre, Long> {

}
