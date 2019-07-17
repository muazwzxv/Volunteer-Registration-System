import java.util.*;
import java.io.*;
public class test{

    public static void main(String[] args){ //throws Exception{
        
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\n");
        boolean loop = false;
        do{
            int resp1 = HomeScreen(in); // Prompt User for input option 
            if(resp1 == 1){// Option for student 
                boolean loopS = false;
                do{
                    clearScreen();
                    int resp = studentScreen(in);
                    if(resp == 1){
                        clearScreen();
                        Student studentData = studentRegister(in);
                        writeStudentRegistration(studentData);
                    } 
                    else if(resp == 2){ 
                        loop = true;
                        continue;
                    }
                    
                    System.out.print("\t Go Back ? (1 - Yes | 2 - No) : ");
                    int resp2 = in.nextInt();
                    if(resp2 == 1){
                        loopS = true;
                    }
                    else{
                        loopS = false;
                    }
                   
                }while(loopS);
            }
            else if(resp1 == 2){ // Option for Volunteer
                boolean loopV = false;
                do{
                    clearScreen();
                    int respV = volunteerScreen(in);
                    if(respV == 1){
                        Volunteer volunteerData = volunteerRegister(in);
                        writeVolunteerRegistration(volunteerData);
                    }
                    else if(respV == 2){
                        loop = true;
                        continue;
                    }

                    System.out.print("\t Go Back ? (1 - Yes | 2 - No) : ");
                    int resp2 = in.nextInt();
                    if(resp2 == 1){
                        loopV = true;
                    }
                    else{
                        loopV = false;
                    }
                   
                }while(loopV);
            }
            else if(resp1 == 3){ // option for Admin
                boolean loopA = false;
                do{
                    clearScreen();
                    Queue studentQueue = readStudent(); // Create a list for each type of class enrolled (English | math | character building)
                    LinkedList<Volunteer>volunteerList = getVolunteerList();
                    int respA = adminScreen(in);
                     
                    if(respA == 1){// Display total number of student
                        clearScreen();
                        countStudent(studentQueue);
                    }
                    else if(respA == 2){ //Display all student info
                        clearScreen();
                        displayStudent(studentQueue);
                    }
                    else if(respA == 3){// Display volunteer (Sorted by volunteering hour)
                        clearScreen();
                        System.out.println("\t *****List of volunteer from the highest to lowest volunteering hour*****");
                        volunteerList.head = volunteerList.mergeSort(volunteerList.head);
                        volunteerList.printList(volunteerList.head);
                        //displaySortedVolunteer(volunteerList.head);
                    }
                    else if(respA == 4){ // Display volunteer with highest allowance
                        clearScreen();
                        displayHighest(volunteerList);
                    }
                    else if(respA == 5){ // Display volunteer with lowest allowance
                        clearScreen();
                        displayLowest(volunteerList);
                    }
                    else if(respA == 6){ // search volunteer
                        clearScreen();
                        searchVolunteer(volunteerList,in);
                    }
                    else if(respA == 7){ // search student
                        clearScreen();
                        searchStudent(studentQueue,in);
                    }
                    else if(respA == 8){
                        loopA = false;
                        continue;
                    }

                    System.out.print("\t Go Back ? (1 - Yes | 2 - No) : ");
                    int resp2 = in.nextInt();
                    if(resp2 == 1){
                        loopA = true;
                    }
                    else{
                        loopA = false;
                    }
                    // Do action here
                }while(loopA);
            }
            else if(resp1 == 4){
                System.exit(0);
            }
            
            System.out.print("\t Select Other Option (1 - Yes | 2 - No) : ");
            int intloop = in.nextInt();
            if(intloop == 1){
                loop = true;
            }
            else{
                loop = false;
            }
        }
        while(loop);
    }

    static void clearScreen(){
        for(int i=0;i<5;i++){
            System.out.println(" ");
        }
    }

    static void displayLowest(LinkedList list){ // Display volunteer with lowest volunteering hour and allowance
        Node current = list.head;
        Node last = list.last;

        Volunteer lowest = (Volunteer) list.head.data;
        while(current != last.next){
            Volunteer temp = (Volunteer) current.data;
            if(temp.calcAllowance() < lowest.calcAllowance()){
                lowest = temp;
            }
            current = current.next;
        }   

        System.out.println("\t*****Volunteer with the lowest allowance*****\n");
        System.out.println(lowest.toString());
    }

    static void displayHighest(LinkedList<E> list){ // Display volunteer with highest volunteering hour and allowance
        Node current = list.head;
        Node last = list.last;

        Volunteer highest = (Volunteer) list.head.data;
        while(current != last.next){
            Volunteer temp = (Volunteer) current.data;
            if(temp.calcAllowance() > highest.calcAllowance()){
                highest = temp;
            }
            current = current.next;
        }

        System.out.println("\t*****Volunteer with the highest allowance*****\n");
        System.out.println(highest.toString());
    }

    static void searchStudent(Queue student,Scanner in){ // Search student info using key(IC number)

        System.out.println("\t *****Search Student***** ");
        System.out.print("\t Enter IC : ");
        String key = in.next();

        LinkedList list = new LinkedList();
        while(!student.isEmpty()){
            list.insertAtBack(student.dequeue());
        }

        Node current = list.head;
        Node last = list.last;

        while(current != last.next){
            Student data = (Student) current.data;
            if(data.getSIc().equalsIgnoreCase(key)){
                System.out.println("\t");
                System.out.println(data.toString());
            }
            current = current.next;
        }
    }

    static void searchVolunteer(LinkedList list,Scanner in){ // Search volunteer info using key(IC number)
        System.out.println("\t *****Search Volunteer***** ");
        System.out.print("\t Enter IC : ");
        String key = in.next();


        Node head = list.head;
        Node tail = list.last;
        Node current;

        current = head;
        while(current != tail.next){
            Volunteer data = (Volunteer) current.data;

            if(data.getVIc().equalsIgnoreCase(key)){
                System.out.println("\t");
                System.out.println(data.toString());
            }
            current = current.next;
        }
    }

    static LinkedList getVolunteerList(){  // Method to fetch volunteer info from io files
       LinkedList<Volunteer>list = new LinkedList<Volunteer>();
       BufferedReader in;
       String read = null;
       StringTokenizer st;
       try{
           in = new BufferedReader(new FileReader("volunteerInfo.txt"));
           while((read = in.readLine()) != null){
               st = new StringTokenizer(read,";");
               String name = st.nextToken();
               String ic = st.nextToken();
               String phone = st.nextToken();
               String study = st.nextToken();
               int muet = Integer.parseInt(st.nextToken());
               int hour = Integer.parseInt(st.nextToken());

               Volunteer data = new Volunteer(name,ic,phone,study,muet,hour);
               list.insertAtBack(data);
           }
       }
       catch(FileNotFoundException fnfe){
           System.err.println(fnfe.getMessage());
       }
       catch(IOException io){
           System.err.println(io.getMessage());
       }
       catch(Exception e){
           System.err.println(e.getMessage());
       }

       return list;
    }

    static void countStudent(Queue student){ //Method to count the total amount of student
        Queue temp = new Queue();
        int counter = 0;
        while(!student.isEmpty()){
            temp.enqueue(student.dequeue());
            counter++;
        }
        while(!temp.isEmpty()){
            student.enqueue(temp.dequeue());
        }

        System.out.println("\t There are "+counter+" registered students");
    }

    static void displayStudent(Queue student){ // Display all student info
        Queue queueE = new Queue(); // English  temp
        Queue queueM = new Queue(); // Math temp
        Queue queueCB = new Queue(); // Character building temp
        Queue tempQueue = new Queue(); // Shitttt infinite loop
        Student temp;
       
        while(!student.isEmpty()){
            temp = (Student) student.dequeue();
            if(temp.get_class().equalsIgnoreCase("English")){
                queueE.enqueue(temp);
            }
            else if(temp.get_class().equalsIgnoreCase("Mathematic")){
                queueM.enqueue(temp);
            }
            else if(temp.get_class().equalsIgnoreCase("character Building")){
                queueCB.enqueue(temp);
            }
            tempQueue.enqueue(temp);
        }
        while(!tempQueue.isEmpty()){
            student.enqueue(tempQueue.dequeue());
        }
        //Display info
        System.out.println("\t Registration display starting from most recent applicant ");
        System.out.println("\n\t *****ENGLISH CLASS ENROLMENT***** ");
        while(!queueE.isEmpty()){
            temp = (Student) queueE.dequeue();
            System.out.println("\n"+temp.toString());
        }
        System.out.println("\n\t *****MATHEMATIC CLASS ENROLMENT***** ");
        while(!queueM.isEmpty()){
            temp = (Student) queueM.dequeue();
            System.out.println("\n"+temp.toString());
        }
        System.out.println("\n\t *****CHAARCTER BUILDING CLASS ENROLMENT***** ");
        while(!queueCB.isEmpty()){
            temp = (Student) queueCB.dequeue();
            System.out.println("\n"+temp.toString());
        }
    }

    static void displayList(LinkedList list){ // Method to display all properties in the list
        Node head = list.head;
        Node tail = list.last;
        Node current;
        
        current = head;
        while(current != tail.next){
            Volunteer data = (Volunteer) current.data;
            System.out.println(data.toString());
            current = current.next;
        }
    }

    static Queue readStudent(){
        Queue queue = new Queue();
        Stack<Object>tempStack = new Stack<Object>();
        BufferedReader in;
        String read = null;
        StringTokenizer st;
        try{
            in = new BufferedReader(new FileReader("StudentInfo.txt"));
            while((read = in.readLine()) != null){
                st = new StringTokenizer(read,";");
                String name = st.nextToken();
                String ic = st.nextToken();
                int age = Integer.parseInt(st.nextToken());
                int hour = Integer.parseInt(st.nextToken());
                String _class = st.nextToken();

                Student data = new Student(name,ic,age,hour,_class);
                tempStack.push(data);
            }
            in.close();

            while(!tempStack.isEmpty()){
                queue.enqueue(tempStack.pop());
            }
        }
        catch(FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
        catch(IOException io){
            System.err.println(io.getMessage());
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        return queue;
    }

    static int HomeScreen(Scanner in){
        clearScreen();
        System.out.println("\t Welcome To Registration Management System ");
        System.out.println("\t 1 - Student ");
        System.out.println("\t 2 - Volunteer ");
        System.out.println("\t 3 - Admin");
        System.out.println("\t 4 - Exit Program");
        System.out.print("\t Option : ");
        int response = in.nextInt();
        return response;
    }

    static int adminScreen(Scanner in){ // Work on this   !!!!!!n 
        System.out.println("\t Admin Option ");
        System.out.println("\t 1 - Display total number of student ");
        System.out.println("\t 2 - Display all student info ");
        System.out.println("\t 3 - Display volunteer (Sorted based on volunteering hours) ");
        System.out.println("\t 4 - DIsplay Volunteer with highest allowance ");
        System.out.println("\t 5 - Display volunteer with the lowest allowance ");
        System.out.println("\t 6 - Search Volunteer ");
        System.out.println("\t 7 - Search Student ");
        System.out.println("\t 8 - Exit ");
        System.out.print("\t Enter Option : ");
        int option = in.nextInt();

        return option;
    }

    static int volunteerScreen(Scanner in){
        System.out.println("\t Volunteers Option ");
        System.out.println("\t 1 - Register As New Volunteers ");
        System.out.println("\t 2 - Exit ");
        System.out.print("\t Option : ");
        int resp = in.nextInt();
        
        return resp;
    } 

    static Volunteer volunteerRegister(Scanner in){
        System.out.println("\t New Volunteer Registration ");
        System.out.print("\t Enter Name : ");
        String name = in.next();
        System.out.print("\t Enter IC : ");
        String ic = in.next();
        System.out.print("\t Enter Phone Number : ");
        String phoneNumber = in.next();
        System.out.print("\t Enter Level of Study (1 - Primary School | 2 - High School | 3 - University | 4 - Working ) : ");
        int resp = in.nextInt();
        String level = null;

        if(resp == 1){
            level = "Primary School";
        }
        else if(resp == 2){
            level = "High School";
        }
        else if(resp == 3){
            level = "University";
        }
        else if(resp == 4){
            level = "Working";
        }

        System.out.println("\t Enter Muet Score : ");
        System.out.println("\t *Enter 0 if you have no Muet score yet ");
        System.out.print("\t Band : ");
        int muet = in.nextInt();

        System.out.print("\t Enter Hour You wish to Volunteer : ");
        int hour = in.nextInt();
        
        Volunteer data = new Volunteer(name,ic,phoneNumber,level,muet,hour);

        return data;
    }

    static int studentScreen(Scanner in){
        //Scanner in = new Scanner(System.in);
        System.out.println("\t Student Option ");
        System.out.println("\t 1 - Register As New Student ");
        System.out.println("\t 2 - Exit ");
        System.out.print("\t Option : ");
        int resp = in.nextInt();
        return resp;
    }

    static Student studentRegister(Scanner in){
        //Scanner in = new Scanner(System.in);
        System.out.println("\t New Registration ");
        System.out.print("\t Enter Name : ");
        String name = in.next(); 

        System.out.print("\t Enter IC Number : ");
        String ic = in.next();

        System.out.print("\t Enter Age : ");
        int age = in.nextInt();

        System.out.print("\t Enter Class you Wish To Enroll (1 - English | 2 - Mathematic | 3 - Character Building) : ");
        int type = in.nextInt();
        String classType = null;
        if(type == 1){
            classType = "English";
        }
        else if(type == 2){
            classType = "Mathematic";
        }
        else if(type == 3){
            classType = "Character Building";
        }

        System.out.println("\t Enter Sessions ( 1 Session = 6 hours) ");
        System.out.print("\t Option : ");
        int session = in.nextInt();
        int totalhour = session*6;

        Student data = new Student(name,ic,age,totalhour,classType);
        return data;
    }

    static void writeVolunteerRegistration(Volunteer data){
        try(PrintWriter writeVolunteer = new PrintWriter(new BufferedWriter(new FileWriter("volunteerInfo.txt",true)))){
            writeVolunteer.println(data.getVName()+";"+data.getVIc()+";"+data.getVPhone()+";"+data.getLevel()+";"+data.getMuet()+";"+data.getHour());
            writeVolunteer.close();
        }
        catch(FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        }
        catch(IOException io){
            System.out.println(io.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }

    static void writeStudentRegistration(Student data){
        try(PrintWriter writeStudent = new PrintWriter(new BufferedWriter(new FileWriter("StudentInfo.txt",true)))){
            writeStudent.println(data.getSName()+";"+data.getSIc()+";"+data.getAge()+";"+data.getHour()+";"+data.get_class());
            writeStudent.close();
        }
        catch(FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        }
        catch(IOException io){
            System.out.println(io.getMessage());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
}