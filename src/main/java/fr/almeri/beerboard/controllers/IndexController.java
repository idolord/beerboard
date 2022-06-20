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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
        pModel.addAttribute("updated", dtf.format(LocalDateTime.now()));

        //pieChart
        ArrayList<Region> listRegion =  (ArrayList<Region>) regionRepository.findAll();
        ArrayList<Brasserie> ListBrasserie =  (ArrayList<Brasserie>) brasserieRepository.findAll();
        ArrayList<String> labelsRegion = new ArrayList<>();
        int[] datasPieChart = new int[listRegion.size()];
        for (int i = 1; i < listRegion.size();i++ ) {
            labelsRegion.add(listRegion.get(i).getNomRegion());
            for (Brasserie brass : ListBrasserie) {
                if (listRegion.get(i).getNomRegion() == brass.getRegion().getNomRegion())
                {
                    datasPieChart[i] += 1;
                }
            }
        }

        pModel.addAttribute("labelsPieChart", labelsRegion);
        pModel.addAttribute("datasPieChart", datasPieChart);

        //AreaChart nb biere par taux d'alcool

        pModel.addAttribute("labelsAreaChart", new String[]{"2.6", "5", "7.5"});
        pModel.addAttribute("datasAreaChart", new int[]{1,50,15});


        pModel.addAttribute("labelsBarChart", new String[]{"Pays 1"," Pays 2"});
        pModel.addAttribute("datasConsommation", new int[]{145,99});
        pModel.addAttribute("datasProduction", new int[]{160,100});

        pModel.addAttribute("labelsBarChart1", new String[]{"Brasserie 1", "Brasserie 2"});
        pModel.addAttribute("datasBarChart1", new int[]{5,2});

        ArrayList<String> labelsBarChart2 = new ArrayList<>();
        labelsBarChart2.add("Marque 1");
        labelsBarChart2.add("Marque 2");
        pModel.addAttribute("labelsBarChart2", labelsBarChart2);
        pModel.addAttribute("datasBarChart2", new int[]{1,4});

        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model pModel, RedirectAttributes pRedirectAttributes, HttpSession pSession){
        return "redirect:/";
    }
}
