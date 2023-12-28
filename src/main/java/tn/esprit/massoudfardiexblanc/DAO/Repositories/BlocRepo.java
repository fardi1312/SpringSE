package tn.esprit.massoudfardiexblanc.DAO.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Bloc;

public interface  BlocRepo extends JpaRepository<Bloc,Long> {

    Bloc findByNomBloc(String nomBloc);
}
