// The root URL for the RESTful services
var rootURL = "http://localhost:9090/simple_service_webapp_war_exploded/webapi/wines";


var findAll= function () {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		success: renderList
	});
};


var renderList = function (list) {
	//list=data.wine;
        console.log("response");
	$.each(list, function(index, wine) {
		$('#table_body').append('<tr><td>'+wine.name+'</td><td>'+
				wine.grapes+'</td><td>'+wine.country+'</td><td>'+wine.year+
				'</td></tr>');
	});
        $('#table_id').DataTable();
};

var openModal = function(){
	$('#name').val("BLOCK NINE");
	$('#grapes').val("Merlot");
	$('#country').val("Ireland");
	$('#region').val("Athlone");
	$('#year').val("2021");
	$('#myModal').modal('show');
}

//Retrieve the wine list when the DOM is ready
$(document).ready(function(){
	$(document).on("click", '#modalBtn', function(){openModal();});
	findAll();
	
       // $('#tab a').click(function (e) {
       //     e.preventDefault();
        //    $(this).tab('show');
       // });
});

