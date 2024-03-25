var rootURL = "http://localhost:9090/simple_service_webapp_war_exploded/webapi/wines"
var currentWine;
$(document).ready(function(){
    findAll();
    $(document).on('click', '#wineList a', function(){
        var hrefValue = $(this).attr('href');
        findById(hrefValue.substring(1));
    })

    $(document).on('click', '#btnAdd', function(){newWine()});
    $(document).on('click', '#btnSave', function(){addWine()});
})

var findAll = function(){
    console.log('findAll');
    $.ajax({
        type: "GET",
        url: rootURL,
        dataType: "json",
        success: renderList
    });
}

var findById = function(id){
    console.log(id);
    $.ajax({
        type: 'GET',
        url: rootURL + '/' +id,
        dataType: "json",
        success: function (data){
            $('#btnDelete').show();
            console.log('findBYId success: ' + data.name);
            currentWine = data;
            renderDetails(currentWine);
        }
    })
}
var renderDetails=function (wine){
    $('#wineId').val(wine.id);
    $('#name').val(wine.name);
    $('#grapes').val(wine.grapes);
    $('#country').val(wine.country);
    $('#region').val(wine.region);
    $('#year').val(wine.year);
    $('#pic').attr('src', 'pics/' + wine.picture);
    $('#description').val(wine.description);
}
function renderList(wineList){
    $.each(wineList, function(index, wine){
        $("#wineList").append('<li><a href=#' + wine.id 
        +'>' + wine.name +'</a></li>');
    })
}

var newWine = function(){
    $('#wineId').val("");
    $('#wineId').attr('disable');
    $('#name').val("");
    $('#name').attr('enable');
    $('#grapes').val("");
    $('#grapes').attr('enable');
    $('#country').val("");
    $('#country').attr('enable');
    $('#region').val("");
    $('#region').attr('enable');
    $('#year').val("");
    $('#year').attr('enable');
    $('#description').val("");
    $('#description').attr('enable');
}

var form2JSON = function(){
    return JSON.stringify({
        "id": $('#wineId').val(),
        "name": $('#name').val(),
        "grapes": $('#grapes').val(),
        "country": $('#country').val(),
        "region": $('#region').val(),
        "year": $('#year').val(),
        "picture": "",
        "description": $('#description').val(),
    })
}

var addWine = function(){
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: rootURL,
        data: form2JSON(),
        success: function(data, textStatus, jqXHR){
            alert('Wine created successfully');
            $('#windId').val(data.id);
            findAll();
        },
        error: function(jqXHR, textStatus, error){
            alert('addWine error: ' + textStatus);
        }
    })
}