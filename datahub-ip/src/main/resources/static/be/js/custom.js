$('#template').on('change', function(event) {
    event.preventDefault();
    var id = $(this).val();

    alert(id);

    //Send ajax request
    $.ajax({
        method: 'GET',
        url: '/async/connnector/template/' + id,
        // data: { 'id': id }
        //Result of ajax call
    }).done(function(data) {
        $('.config').html(data['config']);
    });

});



$('.card-container').on('click', function(event) {
    event.preventDefault();
    var id = $(this).find('.cardid').html();

    // alert(id);

    //Send ajax request
    $.ajax({
        method: 'GET',
        url: '/secure/card/ajax/show',
        data: { 'id': id }
        //Result of ajax call
    }).done(function(data) {

        $('#cardno').html(data['cardno']);
        $('#phone').html(data['phone']);
        $('#bank').html(data['bank']);
        $('#user').html(data['userName']);
        $('#email').html(data['userEmail']);
        $('#cvv').html(data['cvv']);
        $('#card-edit').attr("href", "/secure/card/edit/" + data['id']);
        $('#card-delete').attr("href", "/secure/card/delete/" + data['id']);


    });
    //Code for opening the Modal
    $('#card-info-modal').openModal();

});


// var iframe_url = "http://localhost:2004/secure/card/iframepage";

// // $('#generate-iframe').click(function(){
// //     alert('Button clicked now create an iframe !');
// //     $('<iframe>')                      // Creates the element
// //     .attr('src',iframe_url) // Sets the attribute spry:region="myDs"
// //     .attr('height',500)
// //     .attr('width',500)
// //     .appendTo('#iframe_div');
// //  });


// var iframe = document.createElement('iframe');
// var html = '<body>Foo</body>';
// iframe.src = 'data:text/html;charset=utf-8,' + encodeURI(html);
// document.body.appendChild(iframe);
// console.log('iframe.contentWindow =', iframe.contentWindow);