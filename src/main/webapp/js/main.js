var rootURL = "http://localhost:9090/simple_service_webapp_war_exploded/webapi/wines"

$(document).ready(function(){
    findAll();
})

var findAll = function(){
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json",
        success: renderList
    });
}

function renderList(wineList){
    $.each(wineList, function(index, wine){
        $("#wineList").append('<li><a href=#' + wine.id 
        +'">' + wine.name +'</a></li>');
    })
}