function CodeGen(){

	var length = 6,
        charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
        retVal = "";

    for (var i = 0, n = charset.length; i < length; ++i) {
        retVal += charset.charAt(Math.floor(Math.random() * n));
    }

	document.getElementById("securityCode").innerHTML = retVal;

    
	
	document.getElementById("securityCode").value = retVal;
	return retVal;
}

