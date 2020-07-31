$(document).ready(function () {
    $('div [id^=collapse]').each(function () {
        if ($('#' + $(this)[0].id + ' ul li').length === 0) {
            $('#' + $(this)[0].id + ' ul').append('<li class="list-group-item">Nenhum item encontrado!</li>');
        } else {
            var i = 0;
            $('#' + $(this)[0].id + ' [id^=collapse][id$=Count]').each(function () {
                i += parseFloat($(this).html().replace("Restante:", ""));
            });
            $('#' + $(this)[0].id + 'Total').html("Total: " + i);
        }
    });
});