public class Volunteer{
  private String Vname;
  private String Vic;
  private String Vphone;
  private String level_study;
  private int muet;
  private int hour;

  //default constructor
  public Volunteer(){
      Vname = null;
      Vic = null;
      Vphone = null;
      level_study = null;
      muet  = 0;
      hour = 0;
  }

  // parameterized constructor
  public Volunteer(String vn, String vi, String vp, String ls, int m,int h){
      Vname = vn;
      Vic = vi;
      Vphone = vp;
      level_study = ls;
      muet = m;
      hour = h;
  }

  //accessor method
  public String getVName(){return Vname;}
  public String getVIc(){return Vic;}
  public String getVPhone(){return Vphone;}
  public String getLevel(){return level_study;}
  public int getMuet(){return muet;}
  public int getHour(){return hour;}

  //accessor method
  public void mutatorName(String vn){Vname = vn;}
  public void setVIc(String vi){Vic = vi;}
  public void setVPhone(String vp){Vphone = vp;}
  public void setLevel(String ls){level_study = ls;}
  public void setMuet(int m){muet = m;}
  public void setHour(int h){hour = h;}

  //toString method
  public String toString(){
      return "\t Volunteer name: "+Vname+"\n\t Volunteer IC: "+Vic+"\n\t Volunteer Phone no: "+Vphone+"\n\t Level of study: "+level_study+"\n\t Muet result: "+muet+"\n\t Hour : "+hour+"\n\t Total Allowance : RM "+calcAllowance();
  }  

  //process method
  public double calcAllowance(){
      double totAllowance = 0.00;
      totAllowance = hour * 5.00;
      return totAllowance;
  }
}
  