$(document).ready(function () {

    $("form").submit(function (e) {
        e.preventDefault();
        var val = document.activeElement.value;
        var message = getFields();
        if (val === "encode") {
            encode(message);
        } else if (val === "decode") {
            decode(message);
        }
    });

    $("form input[type=submit]").click(function () {
        $("input[type=submit]", $(this).parents("form")).removeAttr("clicked");
        $(this).attr("clicked", "true");
    });
});

function getFields() {
    var message = document.getElementsByName('message')[0].value;
    var key1 = document.getElementsByName('key1')[0].value;
    var key2 = document.getElementsByName('key2')[0].value;
    var select1 = document.getElementById("controlSelect1");
    var transpositionType1 = select1.options[select1.selectedIndex].text;
    var select2 = document.getElementById("controlSelect2");
    var transpositionType2 = select2.options[select2.selectedIndex].text;
    return formToJSON(message, key1, key2, transpositionType1, transpositionType2);

}

function encode(message) {
    $.ajax({
        type: 'POST',
        url: "api/cipher/encoder",
        contentType: 'application/json',
        dataType: "json",
        data: message,
        success : function(data) {
            $(".result").show();
            document.getElementById('result').innerHTML = data.encodedMessage;
        },
        error: function(XMLHttpRequest, textStatus) {
            alert("some error");
        }
    });
}

/**
 * @desc Helper function to serialize all the form fields into a JSON string
 */
function formToJSON(message, key1, key2, transpositionType1, transpositionType2) {
    return JSON.stringify({
        "message": message,
        "keys": [key1, key2],
        "transpositions": [transpositionType1, transpositionType2]
    });
}
