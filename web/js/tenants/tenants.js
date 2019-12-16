jQuery(document).ready(function ($) {
    $('#tenants').addClass('highlighted');

    $("#tenants-table tr").click(function () {
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    $("#tenant-modal.ui.modal").modal({
        onHidden: onHideTenantModal
    });


    $("#new-tenant").click(function () {
        let selectedRow = $("#tenants-table tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            $("#tenant-modal.ui.modal")
                .modal('show');
        } else {
            alert(warningMessages.selectRow);
        }

    });

    $("#edit-tenant").click(function () {
        let selectedRow = $("#tenants-table tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            getTenant(selectedRowId);
        } else {
            alert(warningMessages.selectRow);
        }

    });

    $("#delete-tenant").click(function () {
        let selectedRow = $("#tenants-table tr.selected");

        let selectedRowId = selectedRow.data("id");
        if (selectedRowId !== undefined) {
            $("#delete-modal.ui.modal")
                .modal('show');
        } else {
            alert(warningMessages.selectRow);
        }
    });


})
;

function onHideTenantModal() {
    $('#tenant-modal.ui.modal input[name=tenant-id]').val("automaticky generovany");
    $('#tenant-modal.ui.modal input[name=tenant-name]').val("");
}

function getTenant(id) {
    $.ajax({
        type: "GET",
        url: '../../account/admin/tenant?tenantId=' + id,
        beforeSend: function () {
            console.log("beforeSend");
        },
        success: function (response) {
            $('#tenant-modal.ui.modal input[name=tenant-id]').val(response.tenantId);
            $('#tenant-modal.ui.modal input[name=tenant-name]').val(response.nazov);
            $('#tenant-modal.ui.modal')
                .modal('show');
        },
        error: function (err) {
            alert("Nepodarilo sa nacitat tenanta.")
        }
    })
    ;
}
