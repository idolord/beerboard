package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.*;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
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
    public String getPageExemple(Model pModel,HttpSession session)
    {
        ArrayList<Biere> listBeerFromDatabase = (ArrayList<Biere>) biereRepository.findAll();
        pModel.addAttribute("listbeer",listBeerFromDatabase);

        return "beers";
    }

    @GetMapping("/see-beer")
    public String GetBeerByCode(Model pModel, @RequestParam String marque, @RequestParam String version,HttpSession session )
    {
        BiereId idBiere = new BiereId(new Marque(marque),version);
        Biere biere = biereRepository.findById(idBiere).orElseThrow();
        pModel.addAttribute("biere", biere);

        return "/see-beer";
    }

    @GetMapping("/add-beer")
    public String ajouterBiereForm(Model pModel, @RequestParam boolean isMod, @RequestParam(required = false) Marque marque, @RequestParam(required = false) String version,HttpSession session, RedirectAttributes redir)
    {
        if (session.getAttribute("biere") != null ){
            pModel.addAttribute("biere", session.getAttribute("biere"));
            session.removeAttribute("biere");
        }else {
            Biere biere = new Biere();
            if (isMod)
            {
                BiereId biereid = new BiereId(marque,version);
            }
            pModel.addAttribute("biere", new Biere());
        }
        pModel.addAttribute("update", isMod);
        pModel.addAttribute("listeType", typeRepository.findAll());
        pModel.addAttribute("listeMarque", marqueRepository.findAll());

        return "add-beer";
    }

    @PostMapping("/add-beer")
    public String ajouterBiere (@ModelAttribute Biere biere, String update, Model model, HttpSession session, RedirectAttributes redir)
    {
        if (!Boolean.valueOf(update))
        {
            if (!biereRepository.existsById(new BiereId(biere.getMarque(),biere.getVersion())))
            {
                biereRepository.save(biere);
                return "redirect:/beers";
            }
            else {
                redir.addFlashAttribute("msg"," Une bière de cette marque avec cette version existe déjà, veuillez saisir une nouvelle\n" +
                        "version.");
                session.setAttribute("biere",biere);
                return "redirect:/add-beer?isMod=false";
            }
        }
        else {
            biereRepository.save(biere);
            return "redirect:/beers";
        }
    }

    @GetMapping("/delete-beer")
    public String supprimerBiereForm(Model pModel, @RequestParam Marque marque, @RequestParam String version,HttpSession session)
    {
        if (session.getAttribute("auth") != null)
        {
            // Id bière
            BiereId idBiere = new BiereId(marque,version);

            biereRepository.deleteById(idBiere);

            return "redirect:/beers";

        }else {
            // Page de connexion
            return "login";
        }

    }
}
