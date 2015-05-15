$('.btn').click(function(){
    if(confirm("Do you really want to delete this driver?") == true) {
        $.ajax({
            type: 'GET',
            url: 'deletecar',
            error: function(jqXHR, error, errorThrown) {
                if(jqXHR.status&&jqXHR.status==400){
                    alert('We can not delete this car!');
                    window.location.replace("cars");
                }
            }
        });
    }
});
