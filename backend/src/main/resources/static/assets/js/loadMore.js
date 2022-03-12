var pageNumber = 1;
var pageNumber2 = 1;

function loadMoreTournsMain(){
   
    console.log("pageNumber="+pageNumber);
    $('#loadArea-'+pageNumber).load('/?page='+pageNumber+' #elements');
    pageNumber ++;
}

function loadMoreMyTournsMain(){
   
    console.log("pageNumber="+pageNumber2);
    $('#loadArea2-'+pageNumber2).load('/?page='+pageNumber2+' #elements2');
    pageNumber2 ++;
}

function loadMoreTournsAdmin(){
   
    console.log("pageNumber="+pageNumber);
    $('#loadArea-'+pageNumber).load('/admin?page='+pageNumber+' #elements');
    pageNumber ++;
}

function loadMoreUsersAdmin(){
   
    console.log("pageNumber="+pageNumber2);
    $('#loadArea2-'+pageNumber2).load('/admin?page='+pageNumber2+' #elements2');
    pageNumber2 ++;
}

function loadMoreTournsUser(){
   
    console.log("pageNumber="+pageNumber);
    $('#loadArea-'+pageNumber).load('/user_profile?page='+pageNumber+' #elements');
    pageNumber ++;
}

function loadMoreUserPairs(){
   
    console.log("pageNumber="+pageNumber2);
    $('#loadArea2-'+pageNumber2).load('/user_profile?page='+pageNumber2+' #elements2');
    pageNumber2 ++;
}

function loadMoreInscription(tournId){
    var tournId = document.querySelector("#tournId").textContent;
    console.log("pageNumber="+pageNumber);
    $('#loadArea-'+pageNumber).load('/tourns/'+tournId+'?page='+pageNumber+' #elements');
    pageNumber ++;
}
