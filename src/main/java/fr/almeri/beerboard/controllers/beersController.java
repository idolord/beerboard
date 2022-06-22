package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.*;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//indique à spring boot un routage
@Controller
public class beersController {
    //route de GET

    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private MarqueRepository marqueRepository;

    @GetMapping("/beers")
    public String getPageExemple(Model pModel)
    {
        ArrayList<Biere> listBeerFromDatabase = (ArrayList<Biere>) biereRepository.findAll();
        pModel.addAttribute("listbeer",listBeerFromDatabase);

        return "beers";
    }

    @GetMapping("/see-beer")
    public String GetBeerByCode(Model pModel, @RequestParam String marque, @RequestParam String version )
    {
        BiereId idBiere = new BiereId(new Marque(marque),version);
        Biere biere = biereRepository.findById(idBiere).orElseThrow();
        pModel.addAttribute("biere", biere);

        return "/see-beer";
    }

    @GetMapping("/add-beer")
    public String ajouterBiereForm(Model pModel, @RequestParam boolean isMod, @RequestParam(required = false) Marque marque, @RequestParam(required = false) String version)
    {
        pModel.addAttribute("update", isMod);
        Biere biere = new Biere();
        if (isMod)
        {
            BiereId biereid = new BiereId(marque,version);
        }
        pModel.addAttribute("biere", new Biere());
        pModel.addAttribute("listeType", typeRepository.findAll());
        pModel.addAttribute("listeMarque", marqueRepository.findAll());

        return "add-beer";
    }

    @PostMapping("/add-beer")
    public String ajouterBiere (@ModelAttribute Biere biere, Model model)
    {
        biereRepository.save(biere);

        return "redirect:/beers";
    }
}
