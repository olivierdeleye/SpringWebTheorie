package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.exceptions.FiliaalMetDezeNaamBestaatAlException;

@Service
@Transactional(readOnly = true)
class FiliaalServiceImpl implements FiliaalService {

	 private final FiliaalDAO filiaalDAO;
	 private final MailService mailService;
	  @Autowired // met deze annotation injecteert Spring de parameter filiaalDAO 
	  // met de bean die de interface FiliaalDAO implementeert 
	  public FiliaalServiceImpl(FiliaalDAO filiaalDAO,  MailService mailService) { 
	    this.filiaalDAO = filiaalDAO;
	    this.mailService = mailService;
	  }
	  
	  //READ
	  @Override 
	  public Filiaal read(long id) { 
	    return filiaalDAO.findOne(id); 
	  } 
	  
	  //CREATE
	  @Override
	  @Transactional(readOnly = false) 
	  public void create(Filiaal filiaal) { 
	    try { 
	      filiaalDAO.save(filiaal); 
	      mailService.zendMail("alpha.centauri0211@gmail.com", "Nieuw filiaal", 
	        "Het filiaal <strong>"   + filiaal.getNaam() +  
	        "</strong> werd toegevoegd.<br>Je kan <a href='"+  
	        ServletUriComponentsBuilder.fromCurrentContextPath().path( 
	          "/filialen/{id}/wijzigen").buildAndExpand(filiaal.getId()).toUri() + 
	          "'>hier</a> het filiaal wijzigen"); 
	     } 
	     catch (DataIntegrityViolationException ex) { 
	      if (filiaalDAO.findByNaam(filiaal.getNaam()) != null) { 
	        throw new FiliaalMetDezeNaamBestaatAlException(); 
	      } 
	      throw ex; 
	    } 
	  } 
	  
	  //UPDATE
	  @Override
	  @Transactional(readOnly = false) 
	  public void update(Filiaal filiaal) {
		Filiaal anderFiliaal = filiaalDAO.findByNaam(filiaal.getNaam());
	    if ((anderFiliaal != null) && (anderFiliaal.getId() != filiaal.getId())) {
	    	throw new FiliaalMetDezeNaamBestaatAlException();
	    }
	    filiaalDAO.save(filiaal); 
	  } 
	  
	  //DELETE
	  @Override
	  @Transactional(readOnly = false) 
	  public void delete(long id) { 
		Filiaal filiaal = filiaalDAO.findOne(id);
	    if( ! filiaal.getWerknemers().isEmpty()) {
	      throw new FiliaalHeeftNogWerknemersException(); 
	    }
	    filiaalDAO.delete(id);
	  } 
	  
	  //FINDALL
	  @Override 
	  public Iterable<Filiaal> findAll() { 
	    return filiaalDAO.findAll(); 
	  }

	  //FIND BY POSTCODE
	  @Override
	  @PreAuthorize("hasRole('manager')") 
	  public Iterable<Filiaal> findByPostcodeBetween(int van, int tot) { 
	    return filiaalDAO.findByAdresPostcodeBetweenOrderByNaamAsc(van, tot); 
	  } 
	  
	  //FIND AANTAL FILIALEN
	  @Override 
	  public long findAantalFilialen() { 
	   return filiaalDAO.count(); 
	  } 
	  
	 
	  
	  

}
