package a;
import java.io.*;

import java.util.Scanner;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/MainPage"})
public class MainPage extends HttpServlet{

	private String bookedSY = ""; 
	public void doGet (HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {

		PrintWriter out = response.getWriter();

		out.println(HtmlGen.doctype()); 

		out.println("<head>"); 
		out.println("<meta charset=\"UTF-8\">"); 
        out.println(HtmlGen.title("Main Page"));
		out.println("<link rel=\"stylesheet\" href=\"/assignment1/css/style.css\">"); 
		out.println("</head>"); 
		
		out.println("<body>"); 

		out.println(HtmlGen.h1("The Good Old Time Movie Online Main Page")); 
		
		out.println(HtmlGen.divForTime("startTime()", "CurrentTime"));
		out.println("<script src = \"/assignment1/js/TimeScript.js\"></script>");
		
		
		//============================================================================
		//check if the seat is booked, if booked , boolen : red = ture.
				
		String [] Schar = {"A-" , "B-" , "C-" , "D-" , "E-" , "F-" , "G-" , "H-"};
        String [] num = {"1" , "2" , "3" , "4" , "5" , "6" , "7" , "8"};
		String booked = ""; 
//----------------------------------------------------------------------------------
try{
		ServletContext ctx = this.getServletContext();
        String fpath = ctx.getRealPath("/WEB-INF/data.txt");

        BufferedReader bf = new BufferedReader(new FileReader(fpath));
				
        String line = bf.readLine();
        
		
		while(line !=null) {
			for (int i=0;i<8;i++){
				for (int j=0;j<8;j++){					
					if(line.contains(Schar[i]+num[j])){
						String store = Schar[i]+num[j]+" ";	
						synchronized(this){
							booked += bookedSY+store;
							
						}
																
						
					}						
				}
								
			}

    		line = bf.readLine();
														
		}
		
        
        bf.close();    // close the file reader
		
	}
	catch (Exception e){
		System.err.println("Error: " + e.getMessage());
		}
//===========================================================================
		out.println(HtmlGen.h2("====== Please choose a seat ====")); 
		out.println(HtmlGen.div("table_box"));
		out.println("<form action=\"BookingPage\" method=\"get\" onsubmit=\"return true;\" >");
		out.println(HtmlGen.table("seat_table")); 

		String [] numdata = {"0" , "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8"};
		String [] chardata = {"top", "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H"};
		for(int i = 0; i< 9 ; i++){
			if(i == 0 ){
				out.println(HtmlGen.TR("gray_color"));
				for(int count = 0; count < 9 ; count++){
					if(count == 0){
						out.println(HtmlGen.TDhead(" ")  );
						out.println(HtmlGen.closeTD());
						
					}
					else{
						
						out.println(HtmlGen.TDhead(numdata[count])  );
						out.println(HtmlGen.closeTD());
						
					}
					
				}
				out.println(HtmlGen.closeTR());

			}
			else{
				
				out.println(HtmlGen.TR(chardata[i]));
				for(int count = 0; count < 9 ; count++){

					
					if(count == 0){
						out.println(HtmlGen.TDhead(chardata[i])  );
						out.println(HtmlGen.closeTD());
						
					}
					else{
						
						out.println(HtmlGen.TD()  );	
						out.println(HtmlGen.lable(chardata[i]+"-"+numdata[count]));
						out.println(HtmlGen.closelable());
						//check if the seat is booked,
						String seat = chardata[i]+"-"+numdata[count];
						if(booked.contains(seat)){
							out.println(HtmlGen.button(chardata[i]+"-"+numdata[count]));
						}
						else{							
							out.println(HtmlGen.input(chardata[i]+"-"+numdata[count]));
						}
						


						out.println(HtmlGen.closeTD());
						
					}
					
				}
				out.println(HtmlGen.closeTR());
			}

		}


		out.println(HtmlGen.closetable()); 

		out.println("</form>");

		out.println(HtmlGen.closediv());

		out.println("</body>"); 
				

	}

	


}

