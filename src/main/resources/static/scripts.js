var i = 0;

function updatePage() {
    $.ajax({
        url: "/parser/getParsingStateInfo",
        type: "get",
        contentType: "json",
        success: function(data) {
            var totalPageCount = data["totalPageCount"];
            var parsedPageCount = data["parsedPageCount"];

            $("#totalPageCount").text('Всього сторінок: ' + totalPageCount);
            $("#parsedPageCount").text('Кількість оброблених сторінок:' + parsedPageCount);
        }
    });

    setTimeout(updatePage, 1000);
}

updatePage();