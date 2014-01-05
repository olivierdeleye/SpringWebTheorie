package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.dao.WerknemerDAO;
import be.vdab.entities.Werknemer;

@Service
@Transactional(readOnly = true)
class WerknemerServiceImpl implements WerknemerService{

	private final WerknemerDAO werknemerDAO;
	private final MailService mailSender; 
	
	@Autowired
	public WerknemerServiceImpl(WerknemerDAO werknemerDAO, MailService mailSender) {
		this.werknemerDAO = werknemerDAO;
		this.mailSender = mailSender;
	}
	
	@Override
	public Iterable<Werknemer> findAll() {
		return werknemerDAO.findAll(new Sort("familienaam", "voornaam"));
	}
	
	//Stuur mail met cron om de minuut
	@Override 
	//@Scheduled(/*cron = "@month"*/ fixedRate=60000)  
	public void stuurMailMetWerknemersMetHoogsteWedde() { 
	  StringBuilder stringBuilder = new StringBuilder();
	  for (Werknemer werknemer : werknemerDAO.findMetHoogsteWedde()) {
		stringBuilder.append(werknemer.getVoornaam()).append(' ').append(werknemer.getFamilienaam()).append(':') 
		    .append(werknemer.getWedde()).append(("<br/>")); 
	  } 
	  mailSender.zendMail("alpha.centauri0211@gmail.com", "Werknemers met de hoogste wedde", stringBuilder.toString()); 
	} 
}
