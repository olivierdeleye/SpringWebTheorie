package be.vdab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.FiliaalService;

@Controller
@RequestMapping("/")
class IndexController {
	
  private final FiliaalService filiaalService;
  private final Voorkeuren voorkeuren;

 
  @Autowired
  public IndexController(FiliaalService filiaalService, Voorkeuren voorkeuren) {
      this.filiaalService = filiaalService;
      this.voorkeuren = voorkeuren;
      
  }
  
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("aantalFilialen", filiaalService.findAantalFilialen());
    modelAndView.addObject("kleur", voorkeuren.getAchtergrondkleur());
    return modelAndView;
  }
  
  @RequestMapping(method = RequestMethod.GET, params="kleur")
  public ModelAndView kleurKeuze(@RequestParam String kleur) {
	  voorkeuren.setAchtergrondkleur(kleur);
	  ModelAndView modelAndView = new ModelAndView("index");
	  modelAndView.addObject("aantalfilialen", filiaalService.findAantalFilialen());
	  modelAndView.addObject("kleur", kleur);
	  return modelAndView;
  }
  

 
 
}
 

