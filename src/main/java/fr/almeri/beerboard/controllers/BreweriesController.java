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
public class BreweriesController {
    //route de GET
    @Autowired
    private BrasserieRepository brasserieRepository;

    @GetMapping("/breweries")
    public String getPageExemple(Model pModel)
    {
        ArrayList<Brasserie> listBrassFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        pModel.addAttribute("listbrass",listBrassFromDatabase);

        return "breweries";
    }

    @GetMapping("see-brewery/{code}")
    public String getPageSeeBrewery(Model pModel, String code)
    {
        Brasserie entity = brasserieRepository.findById(code).orElseThrow();
        pModel.addAttribute("brasserie", entity);

        return "see-brewery";
    }

}
