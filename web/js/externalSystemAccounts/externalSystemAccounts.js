const TABLE_ID = "#externalSystemAccounts-table";
const SEARCHER_SELECTOR = ".searcher";

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

    let table = $(TABLE_ID).DataTable(
        {
            "select": true,
            "paging": false,
            "ordering": true,
            "info": false
        }
    );

    function onSearch() {
        let searchTerm = $(SEARCHER_SELECTOR + ' input').val();

        let tableFilter = $(TABLE_ID + "_filter input");
        tableFilter.val(searchTerm);
        tableFilter.trigger('search');
    }

    $(SEARCHER_SELECTOR).on("click", 'button', onSearch);
    $(SEARCHER_SELECTOR).on("keypress", 'input', function (e) {
        if (e.which === 13) {
            onSearch();
        }
    });
});
