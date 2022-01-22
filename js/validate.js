function validate(){

	
	var userID = document.getElementById("userID"); 	
	var phone = document.getElementById("phone"); 
	var address = document.getElementById("address"); 
	var email = document.getElementById("email"); 
	var code = document.getElementById("code"); 		// value is not passing in !!!!!
	var securityCode = document.getElementById("securityCode"); 
	var choosenS = document.getElementById("choosenS"); 
	

	var resultStatus = true;
	var messageStr = "The following errors were detected:\n"; 
//-------------------------------------------------------------------------------
	if(!userID){
		resultStatus = false;
		messageStr += "- Could not find input with id 'useID'\n";
		
	}
	
//-----------------------------------------------------------------------------
	if(!phone){
		resultStatus = false;
		messageStr += "- Could not find input with id 'phone'\n";
	}

	if(!address){
		resultStatus = false;
		messageStr += "- Could not find input with id 'address'\n";
	}

	if(!email){
		resultStatus = false;
		messageStr += "- Could not find input with id 'email'\n";
	}

	if(!code){
		resultStatus = false;
		messageStr += "- Could not find input with id 'code'\n";
	}

	if(securityCode == null){
		resultStatus = false;
		messageStr += "- please get your sercurity code !!'\n";
	}

//-------------------------------------check user input validation ------------------------
	if(resultStatus){

		var userID = userID.value;
		var phone = phone.value;
		var address = address.value;
		var email = email.value;
		var code = code.value;
		var scode = securityCode.value;
		var choosenS = choosenS.value;
		
    
			var today = new Date();
		
			var dd = today.getDate();
			var mm = today.getMonth() + 1;
			var yy = today.getFullYear();
		
			
			dd = checkDate(dd);
			mm = checkDate(mm);
			var twoDigiYear = yy.toString().substr(-2);
		
			var h = today.getHours();
			var m = today.getMinutes();
			var s = today.getSeconds();
			h = checkTime(h);
			m = checkTime(m);
			s = checkTime(s);
		
			var time = dd + "-" +mm + "-" + twoDigiYear + " "+ s + ":" + m + ":" + h ;
			document.getElementById("time").value = time;
			
		
		//=============================================
		 
	
		if(code !=scode){
			resultStatus = false;
			messageStr += "Plase enter valid Security Code !!\n";
		}
		
		if(!/\S+@\S+\.\S+/.test(email)){
			resultStatus = false;
			messageStr += "Plase enter valid email Code !!\n";
		}

	}

	if (!resultStatus){
		alert(messageStr);
		
	}
	 		
	
	return resultStatus;	

}
function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}

function checkDate(i){
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}


	


