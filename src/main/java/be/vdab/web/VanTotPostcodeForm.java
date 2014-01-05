package be.vdab.web;

import be.vdab.contraints.Postcode;

class VanTotPostcodeForm {

	@Postcode
	private Integer vanpostcode;
	@Postcode
	private Integer totpostcode;
	
	public VanTotPostcodeForm() {
	}
	
	VanTotPostcodeForm(Integer vanpostcode, Integer totpostcode) {
		this.vanpostcode = vanpostcode;
		this.totpostcode = totpostcode;
	}
	
	public Integer getVanpostcode() {
		return vanpostcode;
	}
	
	
	
	public Integer getTotpostcode() {
		return totpostcode;
	}
	public void setTotpostcode(Integer totpostcode) {
		this.totpostcode = totpostcode;
	}

	public boolean isValid() {
	  if ((vanpostcode == null) || (totpostcode == null)) {
	    return false;
	  }
	  return vanpostcode <= totpostcode;
	}
	
	@Override
	public String toString() {
	return String.format("%s-%s", vanpostcode, totpostcode);
	}
	
}
