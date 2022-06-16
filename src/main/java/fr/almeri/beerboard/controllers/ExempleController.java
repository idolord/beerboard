package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Pays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

//indique à spring boot un routage
@Controller
public class ExempleController {
    //route de GET
    @GetMapping("/example")
    public String getPageExemple(Model pModel)
    {
        //l'objet Model est instancié à l'appelle de la méthodes et permet l'envoie de données dynamiques à la page.
        pModel.addAttribute("prenom","mari");
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
        pModel.addAttribute("listpays",listapays);

        return "example";
    }

}
