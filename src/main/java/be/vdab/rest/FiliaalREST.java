package be.vdab.rest;

import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.BeanUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import be.vdab.entities.Filiaal;

@XmlRootElement(name = "filiaal")
public class FiliaalREST extends Filiaal{

	private static final long serialVersionUID = 1L;
    private static final String URI_TEMPLATE_SELF = "/filialen/{id}";  
	private Link link;  
	  
    protected FiliaalREST() { // voor JAXB 
	} 
    
	public FiliaalREST(Filiaal filiaal) { 
	  BeanUtils.copyProperties(filiaal, this); 
	  URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path(URI_TEMPLATE_SELF).buildAndExpand(getId()).toUri(); 
	  link = new Link("self", uri); 
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

}
