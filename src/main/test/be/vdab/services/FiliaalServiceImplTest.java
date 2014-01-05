package be.vdab.services;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.dao.FiliaalDAO;
import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalMetDezeNaamBestaatAlException;
import be.vdab.valueobjects.Adres;

public class FiliaalServiceImplTest {

	private FiliaalService filiaalService; 
	private Filiaal filiaal;
	
	 @Before 
	 public void before() { 
	   filiaal = new Filiaal("TestNaam", true, BigDecimal.ONE, new Date(), 
	         new Adres("Straat", "HuisNr", 1000, "Gemeente")); 
	   FiliaalDAO filiaalDAO = Mockito.mock(FiliaalDAO.class);
	   MailService mailService = Mockito.mock(MailService.class);
	   filiaalService = new FiliaalServiceImpl(filiaalDAO,mailService); 
	   Mockito.when(filiaalDAO.findByNaam(filiaal.getNaam())).thenReturn(filiaal);
	 }
	 
	 @Test(expected = FiliaalMetDezeNaamBestaatAlException.class) 
	 public void createWerptFiliaalMetDezeNaamException() { 
	   filiaalService.create(filiaal); 
	 }
}
