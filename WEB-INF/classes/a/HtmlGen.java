package a;
public class HtmlGen{
	
//============ For Main Page htmlGen ==================	
	public static String doctype(){
		return "<!DOCTYPE html>\n<html lang=\"en\">";
	}

	public static String title(String title){
		return "<title>" + title + "</title>";
	}


	public static String h1(String heading){
		return "<h1>" + heading + "</h1>";
	}

	public static String h2(String heading){
		return "<h2>" + heading + "</h2>";
	}

	public static String h3(String heading){
		return "<h3>" + heading + "</h3>";
	}
//=======================================
	public static String divForTime(String onload, String id ){
		return "<div onload=\""+ onload + "\"" +" id =\"" + id + "\"></div>";
	}
//=======================================
	public static String div(String c){
		return "<div class=\"" + c +"\">";
	}

	public static String closediv(){
		return "</div>";
	}
//=======================================
	public static String table(String c){
		return "<table class=\"" + c +"\">";
	}
	
	public static String closetable(){
		return "</table>";
	}
//=======================================
	public static String TR(String c){
		return "<TR class=\"" + c +"\">";
	}

	public static String closeTR(){
		return "</TR>";
	}

	public static String TDhead(String i){
		return "<TD class =gray_color >" + i;
	}

	public static String TD(){
		return "<TD class = green_color >";
	}

	public static String closeTD(){
		return "</TD>";
	}

//=======================================
	public static String input(String c){
		return "<input name=\"seatNo\" type=\"submit\" value=\""+ c + "\" id=\""+ c +"\" />";
	}

	public static String button(String c){
		return "<button name=\"seatNo\" type=\"submit\" value=\""+ c + "\" id=\""+ c +"\" >" + c + "</button>";
	}

	public static String lable(String c){
		return "<label  for=\""+ c +"\" >";
	}

	public static String closelable(){
		return "</label>";
	}

	
	//Other methods to generate frequently used tags
	//maybe generate a table
	//...
	//...

//============ For Booking Page htmlGen ==================



//=======================================

	public static String newline(){
		return "<p></p>";
	}


	

//=======================================





}