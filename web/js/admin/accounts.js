jQuery(document).ready(function($){
    $('#accounts').addClass('highlighted');

    $("#accounts-table tr").click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
    });


    $("#edit-account").click(function(){
        var selectedRow = $("#accounts-table tr.selected");

        var selectedRowId = selectedRow.data("id");
        if (selectedRowId) {
            document.location.href="./account?accountId=" + selectedRowId;
        } else {
            // TODO: create file for All consts in javascipt
            alert("Vyberte riadok prosim");
        }

    });
});
