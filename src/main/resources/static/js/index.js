$(function() {

    $(document).on("click", "[name='submit']", function (e) {
        var url = $(this).parents("form").attr("action");
        var data = $(this).parents("form").serialize();
        $("[data-error]").html("");
        $.ajax({
            url: url,
            type: "post",
            data: data,
            success: function (json) {
                if (json && json.tourl != "")
                    window.location.href = json.tourl;
            },
            error: function (json) {
                var json = json.responseJSON;
                $.each(json, function (field, message) {
                    if (field != "tourl") {
                        var name = "[data-error='" + field + "']";
                        if ($(name).length > 0) {
                            $(name).html(message);
                        }
                    }
                });

            }
        });
    })

})


