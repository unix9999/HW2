var run_sort = function () {
    var values = $("#values").val();

    var success_callback = function (data) {
        $("#values").val(data);
    };

    $.ajax({type: "POST",
            url: "/sorting",
            data: {values: values},
            success: success_callback
           });
};
