package tn.esprit.massoudfardiexblanc.DAO.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Bloc;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Chambre;
import tn.esprit.massoudfardiexblanc.DAO.Entities.TypeChambre;


import java.time.LocalDate;
import java.util.List;

public interface ChambreRepo extends JpaRepository<Chambre, Long> {

    long countByTypeAndBloc(TypeChambre type, Bloc bloc);

    long countReservationsByIdChambreAndReservationsEstValideAndReservationsAnneeUniversitaireBetween(long idChambre, boolean b, LocalDate dateDebutAU, LocalDate dateFinAU);
}
