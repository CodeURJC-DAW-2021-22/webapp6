var pageNumber = 1;
function loadMore(){
   
    console.log("pageNumber="+pageNumber);
    $('#loadArea-'+pageNumber).load('/?page='+pageNumber+' #elements');
    pageNumber ++;
}