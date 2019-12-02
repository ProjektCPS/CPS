jQuery(document).ready(function($){
    $('#accounts').addClass('highlighted');

    $("#accounts-table tr").click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
    });


    $("#edit-account").click(function(){
        let selectedRow = $("#accounts-table tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            document.location.href="./account?accountId=" + selectedRowId;
        } else {
            alert(warningMessages.selectRow);
        }

    });
});
