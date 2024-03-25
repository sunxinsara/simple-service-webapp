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
    $('#wineId').val(wine.id).prop('disabled', true);
    $('#name').val(wine.name).prop('disabled', true);
    $('#grapes').val(wine.grapes).prop('disabled', true);
    $('#country').val(wine.country).prop('disabled', true);
    $('#region').val(wine.region).prop('disabled', true);
    $('#year').val(wine.year).prop('disabled', true);
    $('#pic').attr('src', 'pics/' + wine.picture);
    $('#description').val(wine.description).prop('disabled', true);
}
function renderList(wineList){
    $.each(wineList, function(index, wine){
        $("#wineList").append('<li><a href=#' + wine.id 
        +'>' + wine.name +'</a></li>');
    })
}

var newWine = function(){
    $('#wineId').val("");
    $('#name').val("").prop('disabled', false);
    $('#grapes').val("").prop('disabled', false);
    $('#country').val("").prop('disabled', false);
    $('#region').val("").prop('disabled', false);
    $('#year').val("").prop('disabled', false);
    $('#description').val("").prop('disabled', false);
}

var form2JSON = function(){
    return JSON.stringify({
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