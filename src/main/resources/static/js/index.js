jQuery(document).ready(function ($) {
    var search_box = $(".search-box");
    $(".start-search").on("click", function (event) {
        var search_terms = search_box.val();
        $.ajax({
            type: "POST",
            url: "/query",
            data: {
                terms: search_terms
            },
            success: function (data) {   ///////////////////////////////自动就传回来了data
                $(".results").empty();
                if (data.length > 0) {
                    var has_data = $("<h1></h1>").text("The sentence's category is     " + data);
                    $(".results").append(has_data);
                } else {
                    var no_data = $("<h1></h1>").text("No according category");
                    $(".results").append(no_data);
                }
            },
            error: function (data) {
                var has_data = $("<h1></h1>").text("err");
                $(".results").append(has_data);
                console.log(data);
            }
        });
    });
});
