package fr.almeri.beerboard.controllers;

import fr.almeri.beerboard.models.Brasserie;
import fr.almeri.beerboard.models.Region;
import fr.almeri.beerboard.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class IndexController {
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

    @GetMapping("/")
    public String home(Model pModel, HttpSession pSession){
        pModel.addAttribute("bieres", (int) biereRepository.count() );
        pModel.addAttribute("brasseries", (int) brasserieRepository.count());
        pModel.addAttribute("marque", (int)marqueRepository.count());
        pModel.addAttribute("region", (int)regionRepository.count());


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        pModel.addAttribute("updated", dtf.format(LocalDateTime.now()));

        //pieChart
        pModel.addAttribute("labelsPieChart", brasserieRepository.getRegionFromBrasserieAsc());
        pModel.addAttribute("datasPieChart", brasserieRepository.getRegionNumberFromBrasserieAsc());

        //AreaChart nb biere par taux d'alcool

        pModel.addAttribute("labelsAreaChart", biereRepository.getThxAlcoolAsc());
        pModel.addAttribute("datasAreaChart", biereRepository.getNbrThxAlcoolAsc());


        pModel.addAttribute("labelsBarChart", paysRepository.getNomsPaysAsc());
        pModel.addAttribute("datasConsommation", paysRepository.getConsoPaysAsc());
        pModel.addAttribute("datasProduction", paysRepository.getProdPaysAsc());


        pModel.addAttribute("labelsBarChart1", brasserieRepository.getBrasserieAsc());
        pModel.addAttribute("datasBarChart1", marqueRepository.getMarquesBrasserieAsc());


        pModel.addAttribute("labelsBarChart2", biereRepository.getmarqueAlcoolAsc());
        pModel.addAttribute("datasBarChart2", biereRepository.getNbrVerparmarqueAlcoolAsc());

        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model pModel, RedirectAttributes pRedirectAttributes, HttpSession pSession){
        return "redirect:/";
    }
}
