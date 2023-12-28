package tn.esprit.massoudfardiexblanc.Services;


import tn.esprit.massoudfardiexblanc.DAO.Entities.*;

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
     Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite);
     Foyer ajoutFoyerEtBlocs(Foyer foyer);
    Bloc affecterChambresABloc(List<Long> numChambre, String nomBloc) ;


     Bloc affecterBlocAFoyer(String nomBloc, String nomFoyer) ;

    List<Chambre>  getChambresParNomBloc(String nomBloc);

    long  nbChambreParTypeEtBloc(TypeChambre type, long idBloc);


    void listeChambresParBloc();


    void pourcentageChambreParTypeChambre();


    void  nbPlacesDisponibleParChambreAnneeEnCours();



}


