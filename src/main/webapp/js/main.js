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
    $(document).on('click', '#btnUpdate', function (){updateWine()});
    $('#btnSave').hide();
    $('#btnDelete').hide();
    $('#btnUpdate').hide();
    $('#btnDelete').click(function (){
        deleteWine();
    })
    $('#btnEdit').click(function (){
        editWine();
    })
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
    $('#btnDelete').hide();
    $('#btnEdit').hide();
    $('#wineId').val("");
    $('#name').val("").prop('disabled', false);
    $('#grapes').val("").prop('disabled', false);
    $('#country').val("").prop('disabled', false);
    $('#region').val("").prop('disabled', false);
    $('#year').val("").prop('disabled', false);
    $('#description').val("").prop('disabled', false);
    $('#btnSave').show();
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
            $("#wineList").html('');
            $('#windId').val(data.id);
            findAll();
            console.log(jqXHR);
        },
        error: function(jqXHR, textStatus, error){
            alert('addWine error: ' + textStatus + " " + error);
            console.log(jqXHR);
        }
    })
}

var deleteWine = function (){
    console.log('deleteWine');
    $.ajax({
        type: "DELETE",
        url: rootURL + '/' + $('#wineId').val(),
        success: function (data, textStatus, jqXHR){
            alert('Wine deleted successfully');
        },
        error: function (jqXHR, textStatus, errorThrown){
            alert('deleteWine error');
        }
    });

    // delete a ul
    $("#wineList").html('');
    findAll();
}

var editWine = function (){
    $('#name').prop('disabled', false);
    $('#grapes').prop('disabled', false);
    $('#country').prop('disabled', false);
    $('#region').prop('disabled', false);
    $('#year').prop('disabled', false);
    $('#description').prop('disabled', false);
    $('#btnUpdate').show();
}

var updateWine = function (){
    console.log('updateWine');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: rootURL + '/' + $('wineId').val(),
        dataType: "json",
        data: form2JSON(),
        success: function (data, textStatus, jqXHR){
            alert('Wine updated successfully');
        },
        error: function (jqXHR, textStatus, errorThrown){
            alert('updateWine error: ' + textStatus);
        }
    });
}