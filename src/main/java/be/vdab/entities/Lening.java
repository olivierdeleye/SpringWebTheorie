package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class Lening implements Serializable{

	public interface Stap1{
	}
	
	public interface Stap2{
	}
	
	private static final long serialVersionUID = 1L;
	@NotNull(groups = Stap1.class) 
	@Size(min = 1, max = 50, message = "{Size.tekst}", groups = Stap1.class)
	private String voornaam;
	
	@NotNull(groups = Stap1.class)
	@Size(min = 1, max = 50, message = "{Size.tekst}", groups = Stap1.class)
	private String familienaam;
	
	@NotNull(groups = Stap1.class)
	@Size(min = 1, max = 50, message = "{Size.tekst}", groups = Stap1.class)
	private String doel;
	
	// hier begint groep 2
	@NotNull(groups = Stap2.class)
	@DateTimeFormat(style="S-")
	private Date beginDatum;
	
	@NotNull(groups = Stap2.class)
	@Min(value = 1, groups = Stap2.class)
	@NumberFormat(style = Style.NUMBER)
	private BigDecimal bedrag;
	
	@NotNull(groups = Stap2.class)
	@Min(value = 1, groups = Stap2.class)
	private Integer aantalMaanden;
	
	private List<String> telefoonNrs = new ArrayList<>();
	private Map<Long, Integer> aantalPersonenTenLastePerJaar = new LinkedHashMap<>();
	private final static int AANTAL_JAREN = 3;
	
    public Lening() {
	 beginDatum = new Date();
	 telefoonNrs.add(""); //plaats voor een eerste te tikken telefoonnummer
	 long jaar = Calendar.getInstance().get(Calendar.YEAR) - AANTAL_JAREN; // initialiseren van 3 entries in Map
	 for (int i = 0; i != AANTAL_JAREN; i++) {
	   aantalPersonenTenLastePerJaar.put(jaar++, 0);
	 }
	}
	
	public void nogEenTelefoonNr() {
		telefoonNrs.add("");
	}

    public String getVoornaam() {
		return voornaam;
	}

    public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

    public String getFamilienaam() {
		return familienaam;
	}

    public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

    public String getDoel() {
		return doel;
	}

    public void setDoel(String doel) {
		this.doel = doel;
	}

    public Date getBeginDatum() {
		return beginDatum;
	}

    public void setBeginDatum(Date beginDatum) {
		this.beginDatum = beginDatum;
	}

    public BigDecimal getBedrag() {
		return bedrag;
	}

    public void setBedrag(BigDecimal bedrag) {
		this.bedrag = bedrag;
	}

    public Integer getAantalMaanden() {
		return aantalMaanden;
	}

    public void setAantalMaanden(Integer aantalMaanden) {
		this.aantalMaanden = aantalMaanden;
	}
    
    public List<String> getTelefoonNrs() {
		return telefoonNrs;
	}

	public void setTelefoonNrs(List<String> telefoonNrs) {
		this.telefoonNrs = telefoonNrs;
	}
	
	public Map<Long, Integer> getAantalPersonenTenLastePerJaar() {
	 return aantalPersonenTenLastePerJaar;
	}
	
    public void setAantalPersonenTenLastePerJaar(Map<Long, Integer> aantalPersonenTenLastePerJaar) {
		this.aantalPersonenTenLastePerJaar = aantalPersonenTenLastePerJaar;
	}
    
    @Override
	public String toString() {
	  return voornaam + ' ' + familienaam + ':' + doel;
	}
	
}
