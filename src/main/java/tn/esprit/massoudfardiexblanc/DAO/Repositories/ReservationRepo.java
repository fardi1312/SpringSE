package tn.esprit.massoudfardiexblanc.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, String> {
}
