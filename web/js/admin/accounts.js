const TABLE_ID = "#accounts-table";
const SEARCHER_SELECTOR = ".searcher";

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
