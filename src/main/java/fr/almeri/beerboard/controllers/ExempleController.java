package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.*;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

//indique à spring boot un routage
@Controller
public class ExempleController {
    //route de GET

    @Autowired
    private PaysRepository paysRepository;
    @Autowired
    private BiereRepository biereRepository;
    @Autowired
    private BrasserieRepository brasserieRepository;
    @Autowired
    private MarqueRepository marqueRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/example")
    public String getPageExemple(Model pModel)
    {
        //l'objet Model est instancié à l'appelle de la méthodes et permet l'envoie de données dynamiques à la page.
        pModel.addAttribute("prenom","ido");
        //je renvoie le nom de la page dans les ficher html du template

        Pays pays = new Pays();
        pays.setNomPays("Turkisdétends");
        pays.setConsommation(15236);
        pays.setProduction(2);

        Pays pays1 = new Pays();
        pays1.setNomPays("Turkisdétendsdeuxfois");
        pays1.setConsommation(5236);
        pays1.setProduction(200);

        Pays pays2 = new Pays();
        pays2.setNomPays("Turkisdétendspasdutout");
        pays2.setConsommation(1526);
        pays2.setProduction(400);

        ArrayList<Pays> listapays = new ArrayList<>();
        listapays.add(pays);
        listapays.add(pays1);
        listapays.add(pays2);



        pModel.addAttribute("pays",pays);

        ArrayList<Pays> listPaysFromDatabase = (ArrayList<Pays>) paysRepository.findAll();
        pModel.addAttribute("listpays",listPaysFromDatabase);
        ArrayList<Brasserie> listBrassFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        pModel.addAttribute("listbrass",listBrassFromDatabase);
        ArrayList<Marque> listMarqueFromDatabase = (ArrayList<Marque>) marqueRepository.findAll();
        pModel.addAttribute("listmarque",listMarqueFromDatabase);
        ArrayList<Region> listRegionFromDatabase = (ArrayList<Region>) regionRepository.findAll();
        pModel.addAttribute("listregion",listRegionFromDatabase);
        ArrayList<Type> listTypeFromDatabaseB = (ArrayList<Type>) typeRepository.findAll();
        pModel.addAttribute("listtype", listTypeFromDatabaseB);

        return "example";
    }

}
