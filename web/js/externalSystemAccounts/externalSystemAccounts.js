jQuery(document).ready(function($){
    $('#externalSystemAccounts').addClass('highlighted');

    $("#externalSystemAccounts-table tr").click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
    });


    $("#edit-externalSystemAccount").click(function(){
        var selectedRow = $("#externalSystemAccounts-table tr.selected");

        var selectedRowId = selectedRow.data("id");
        if (selectedRowId) {
            document.location.href="./externalSystemAccount?accountId=" + selectedRowId;
        } else {
            alert(warningMessages.selectRow);
        }

    });
});
