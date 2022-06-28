package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.*;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

//indique à spring boot un routage
@Controller
public class BreweriesController {
    //route de GET
    @Autowired
    private BrasserieRepository brasserieRepository;

    @Autowired
    private BiereRepository biereRepository;

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/breweries")
    public String getPageExemple(Model pModel)
    {
        ArrayList<Brasserie> listBrassFromDatabase = (ArrayList<Brasserie>) brasserieRepository.findAll();
        pModel.addAttribute("listbrass",listBrassFromDatabase);

        return "breweries";
    }

    @GetMapping("/see-brewery/{code}")
    public String getPageSeeBrewery(Model pModel, @PathVariable String code)
    {
        Brasserie entity = brasserieRepository.findById(code).orElseThrow();
        pModel.addAttribute("brasserie", entity);
        ArrayList<Biere> biereEntity = biereRepository.getBierefromBrasserie(entity.getCodeBrasserie());
        pModel.addAttribute("bieres", biereEntity);

        return "see-brewery";
    }

    @GetMapping("/add-brewery")
    public String GetPageModifiBrassery(Model pModel, @RequestParam boolean isMod, @RequestParam(required = false) String codeBrass)
    {
        pModel.addAttribute("update", isMod);
        Brasserie brasserie = new Brasserie();
        if (isMod) {
            brasserie = brasserieRepository.findById(codeBrass).orElseThrow();
        }
        pModel.addAttribute("brasserie", brasserie);
        ArrayList<Region> listeRegion = (ArrayList<Region>) regionRepository.findAll();
        pModel.addAttribute("listeRegion", listeRegion);
        return "add-brewery";
    }

    @PostMapping("/valid-brewery")
    public String ValidateBrassery(@ModelAttribute Brasserie brasserie,String update, Model pModel, RedirectAttributes redir)
    {
        if (!Boolean.valueOf(update))
        {
            if (!brasserieRepository.existsById(brasserie.getCodeBrasserie()))
            {
                brasserieRepository.save(brasserie);
                return "redirect:breweries";
            }
            else {
                redir.addFlashAttribute("msg"," L’identifiant de la brasserie existe déjà, veuillez en saisir un nouveau ou vérifier que cette \n" +
                        "brasserie n’existe pas déjà.");
                return "redirect:/add-brewery?isMod=false";
            }
        }
        else {
            brasserieRepository.save(brasserie);
            return "redirect:breweries";
        }
    }

    @GetMapping("/delete-brewery")
    public String supprimerBrasserieForm(Model pModel, @RequestParam String codeBrass, HttpSession session,RedirectAttributes redir)
    {
        if (session.getAttribute("auth") != null)
        {
            if (biereRepository.getBierefromBrasserie(codeBrass).size() > 0)
            {
                redir.addFlashAttribute("msg"," Une bière de cette marque avec cette version existe déjà, veuillez saisir une nouvelle\n" +
                        "version.");
            }else{
                brasserieRepository.deleteById(codeBrass);
            }
            return "redirect:/brewery";

        }else {
            // Page de connexion
            return "login";
        }

    }

}
