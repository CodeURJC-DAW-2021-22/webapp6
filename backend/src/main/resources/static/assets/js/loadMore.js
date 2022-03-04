var pageNumber = 1;
var pageNumber2 = 1;

function loadMoreTournsMain(){
   
    console.log("pageNumber="+pageNumber);
    $('#loadArea-'+pageNumber).load('/?page='+pageNumber+' #elements');
    pageNumber ++;
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
    $('#loadArea-'+pageNumber).load('/admin?page='+pageNumber+' #elements');
    pageNumber ++;
}