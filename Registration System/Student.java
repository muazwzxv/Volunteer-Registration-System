//student class
public class Student{
    private String Sname;
    private String Sic;
    private int age;
    private int hour;
    private String _class;
    
    //default constructor
    public Student(){
      Sname = null;
      Sic = null;
      age = 0;
      hour = 0;
      _class = null;
    }
    
    //paramterized constructor
    public Student(String sn, String sic, int a,int h,String c){
      Sname = sn;
      Sic = sic;
      age = a;
      hour = h;
      _class = c;
    }
    
    //accessor method
    public String getSName(){return Sname;}
    public String getSIc(){return Sic;}
    public int getAge(){return age;}
    public int getHour(){return hour;}
    public String get_class(){return _class;}
  
    //mutato method
    public void setName(String name){Sname = name;}
    public void setSIc(String sic){Sic = sic;}
    public void setAge(int a){age = a;}
    public void setHour(int h){hour = h;}
    public void set_class(String c){_class = c;}
    
    //toString method
    public String toString(){
      return "\t Student name : "+Sname+"\n\t Student IC: "+Sic+"\n\t Student age: "+age+"\n\t Hour : "+hour+"\n\t Class : "+_class;
    }
  }
  