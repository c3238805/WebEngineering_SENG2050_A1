package a;
import java.io.*;

import java.util.Scanner;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/BookingPage"})
public class BookingPage extends HttpServlet {
    private String data = ""; 
    
    public void doGet (HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        
        PrintWriter out = response.getWriter();
        var avaliable = true;       // return true when seat is avaliable      
        String dataSY = "";  




  //================================      
     

  //================================   

        out.println(HtmlGen.doctype()); 

		out.println("<head>"); 
		out.println("<meta charset=\"UTF-8\">"); 
        out.println(HtmlGen.title("Booking Page"));
		out.println("<link rel=\"stylesheet\" href=\"/assignment1/css/style.css\">"); 
        out.println("<script src = \"/assignment1/js/validate.js\"></script>");
        out.println("<script src = \"/assignment1/js/CodeGen.js\"></script>");
        
		out.println("</head>"); 
		
		out.println("<body>"); 

		out.println(HtmlGen.h1("The Good Old Time Movie Online Booking Page")); 
		
		out.println(HtmlGen.divForTime("startTime()", "CurrentTime"));
		

		out.println(HtmlGen.h2("====== Messege ====")); 

        out.println(HtmlGen.div("messegeBox"));

        // validating Request Parameters
        String [] Schar = {"A-" , "B-" , "C-" , "D-" , "E-" , "F-" , "G-" , "H-"};
        String [] num = {"1" , "2" , "3" , "4" , "5" , "6" , "7" , "8"};
                
            
//-------------------------------------------	
        
        
        // pass the choosen seatNo and store as var
        var choosenSeat = request.getParameter("seatNo");
        var checkseat = false;

        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if(choosenSeat.matches(Schar[i]+num[j])){                          
                    checkseat = true;
                    
                }
                    
            }
        }
        
        if(checkseat == false){
            out.println("<p> error occur !! Navaigate to "); 
            out.println("<a href= \"MainPage\"> Main Page</a>"); 
            out.println("</p>");

        }
//-------------------------------------------------------	
    
    

    try{
        // read the txt file then check if contain the SeatNo
        ServletContext ctx = this.getServletContext();
        String fpath = ctx.getRealPath("/WEB-INF/data.txt");       
        
        BufferedReader bf = new BufferedReader(new FileReader(fpath));
      
       String line = bf.readLine();

       while(line !=null) {
            if(line.contains(choosenSeat)){
                avaliable = false;
                
                synchronized(this){
                    dataSY = data;
                    data = line;
                }
                
                avaliable = false;                
            }
            line = bf.readLine();
                                                
        }
        bf.close();    // close the file reader 

       

        
    
    }
	catch (Exception e){
		System.err.println("Error: " + e.getMessage());
		}
        
//================================================================================    

        
        if(avaliable == false ){
            
            out.println("<div id=\"bookinfo\">  been booked !!  booking information : "+ data );           
            out.println("<p> Navaigate to "); 
            out.println("<a href= \"MainPage\"> Main Page</a>"); 
            out.println("</p>");
            out.println("</div>");
        }
        
        
        if(avaliable == true && checkseat == true) {                


           
            // display the form for user to input
//--------------------------------------------------------------------------------------------------------------
            out.println(HtmlGen.div("security"));       // add div for security code
            
            out.println(choosenSeat);
            out.println("avaliable!!");
            
            out.println(HtmlGen.newline());
            out.println("Click the following button to see the Security Code !  ========= >>>>> ");
            out.println("<input type = \"button\" onclick = \"CodeGen()\" value= \"try me\">");    
            out.println("<p> Sercurity Code : </p>");
            out.println("<input name=\"securityCode\" id=\"securityCode\" >");
           
            out.println(HtmlGen.closediv());
//--------------------------------------------------------------------------------------------------------------
            out.println("<form action=\"BookingPage\" method=\"post\" onsubmit=\"return validate();\" >");
            
            out.println("<input type=\"hidden\" name=\"choosenS\" id= \"choosenS\" value = \""+ choosenSeat +"\" /> "); //set choosenSeat
            out.println("<input type=\"hidden\" name=\"time\" id=\"time\" />");
            out.println(HtmlGen.newline());
            out.println(HtmlGen.lable("userID"));
            out.println("UserID:");
            out.println(HtmlGen.closelable());

            out.println("<input type=\"text\" name=\"userID\" id=\"userID\" pattern=\"[a-zA-Z][a-zA-Z ]{2,}\" required/>");       // no number and required
            out.println(HtmlGen.newline());
//--------------------------------------------------------------------------------------------------------------
            out.println(HtmlGen.lable("phone"));
            out.println("Phone:");
            out.println(HtmlGen.closelable());

            out.println("<input type=\"text\" name=\"phone\" id=\"phone\" />");       // phone optional
            out.println(HtmlGen.newline());
//--------------------------------------------------------------------------------------------------------------
            out.println(HtmlGen.lable("address"));
            out.println("Address:");
            out.println(HtmlGen.closelable());

            out.println("<input type=\"text\" name=\"address\" id=\"address\" />");       // phone optional
            out.println(HtmlGen.newline());
 //--------------------------------------------------------------------------------------------------------------           
            out.println(HtmlGen.lable("email"));
            out.println("Email:");
            out.println(HtmlGen.closelable());

            out.println("<input type=\"text\" name=\"email\" id=\"email\" required/>");       // email must @ 
            out.println(HtmlGen.newline());
//--------------------------------------------------------------------------------------------------------------
            out.println(HtmlGen.lable("code"));
            out.println("Secuurity Code:");
            out.println(HtmlGen.closelable());

            out.println("<input type=\"text\" name=\"code\" id=\"code\" required/>");       // must match the code
            out.println(HtmlGen.newline());
//--------------------------------------------------------------------------------------------------------------
                      
            out.println("<input type=\"reset\" value=\"Clear\" />");
            out.println("<input type=\"submit\" value=\"Submit\" />");
            
            out.println(HtmlGen.newline());
           
//--------------------------------------------------------------------------------------------------------------
            out.println("</form>");



        }
                
        out.println(HtmlGen.closediv());
    //=================================================
        out.println("</body>");
    }
    
    
//==========================================================================================

    
public void doPost (HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {

    PrintWriter out = response.getWriter();

    var userID = request.getParameter("userID"); 	
    var phone = request.getParameter("phone"); 
    var address = request.getParameter("address"); 
    var email = request.getParameter("email"); 
    var code = request.getParameter("code");        // value is not pasing in !!!
    var choosenS = request.getParameter("choosenS");
    var time = request.getParameter("time");
    

    int count = 0 ;

    


//----------------------------------------------------------------------------------
try{

    ServletContext ctx = this.getServletContext();
    String fpath = ctx.getRealPath("/WEB-INF/data.txt");

    Scanner sc = new Scanner(new FileReader(fpath));    // scanner 

    // String line = freader.readLine();       // define String line === > start reading line
    String line = sc.nextLine();        // for reading first seat No ...
    
    while(sc.hasNextLine()) {
        if(line.contains(userID)){

            count++;
            line = sc.nextLine();
            
        }
        else{
            
            line = sc.nextLine();
            if(line.contains(userID)){
                count++;               
            } 
            
        }
                                            
    }
    sc.close();    // close the file reader

}
catch (Exception e){
    System.err.println("Error: " + e.getMessage());
    } 
    if(count>=3){
        out.println("<p> Same UserID can not book more than 3 seat ! </p>"); 
        out.println("<p> Navaigate to "); 
        out.println("<a href= \"MainPage\"> Main Page</a>"); 
        out.println("</p>");
        out.println("</div>");
    }
    else {
        try{
            ServletContext ctx = this.getServletContext();
            String fpath = ctx.getRealPath("/WEB-INF/data.txt");
        
        //====================input data into data.txt file============================
            BufferedWriter bf = new BufferedWriter(new FileWriter(fpath,true));
            
            bf.write(choosenS+" "+userID+" "+phone+" "+address+" "+email+" "+code + " " + time );    
            bf.newLine();        
            bf.close();
            
            out.println("<div id=\"booked\">  THE SEAT IS BOOKED FOR YOU ! " );   
            out.println("Seat number: "+choosenS+" User ID: "+userID+" Phone Number: "+phone+" Address: "+address+" Email: "+email+" Sercurity Code: "+code + "Time: " + time);        
            out.println("<p> Navaigate to "); 
            out.println("<a href= \"MainPage\" > Main Page</a>"); 
            out.println("</p>");
            out.println("</div>");                      
    }  
    catch (Exception e){
        System.err.println("Error: " + e.getMessage());
        }
    }

//=================================================================================


    
      
}


       
}

