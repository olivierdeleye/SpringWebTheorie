package be.vdab.services; 

public interface MailService { 
  void zendMail(String to, String subject, String text); 
}