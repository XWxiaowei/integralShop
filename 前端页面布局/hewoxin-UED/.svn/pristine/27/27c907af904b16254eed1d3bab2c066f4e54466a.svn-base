/**
 * Created by yovino on 2016/8/5.
 */
(function () {
    $('.icon_delete').on('click', function () {
        $('.mod').addClass('hide');
        $('.del').removeClass('hide');
        $('#popup').modal('show');
    });

    $('#delete-btn').on('click', function () {
        $('.mod').addClass('hide');
        $('.del').removeClass('hide');
        $('#popup').modal('show');
    });


    $('#btn_cancel').on('click', function () {
        $('#popup_cancel').modal('show');
    });


    dialogListeners();

    function dialogListeners() {
        $('.cancel').on('click', function () {
            $('#popup').modal('hide');
            $('#popup_cancel').modal('hide');
        });

        $('.confirm').on('click', function () {
            $('#popup').modal('hide');
            $('#popup_cancel').modal('hide');
        });
    }
})();