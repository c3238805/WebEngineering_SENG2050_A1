
function startTime() {
    
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
    document.getElementById("CurrentTime").innerHTML = time;
    setTimeout(startTime, 500);

//=============================================
 



}

function checkTime(i) {
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}

function checkDate(i){
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}



startTime();




