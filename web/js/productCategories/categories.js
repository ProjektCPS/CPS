jQuery(document).ready(function ($) {

    $('#products').addClass('highlighted');

    $('.special.card .image').dimmer({
        on: 'hover'
    });

    $('.star.rating').rating();

    $('.card .dimmer').dimmer({
            on: 'hover'
        });
});
$('.image').modal('show');
