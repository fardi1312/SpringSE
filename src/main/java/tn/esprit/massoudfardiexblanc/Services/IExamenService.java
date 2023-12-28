package tn.esprit.massoudfardiexblanc.Services;


import tn.esprit.massoudfardiexblanc.DAO.Entities.Bloc;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Foyer;
import tn.esprit.massoudfardiexblanc.DAO.Entities.Universite;

import java.util.List;

public interface IExamenService {

    Universite addOrUpdate(Universite u);
    List<Universite> findAll();
    Universite findById(long id);
    void deleteById(long id);
    void delete(Universite u);

    Foyer addOrUpdate(Foyer f);


    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite (long idUniversite) ;
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite);
    public Foyer ajoutFoyerEtBlocs(Foyer foyer);

}
