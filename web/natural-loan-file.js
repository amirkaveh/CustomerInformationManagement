/**
 * Created by dotinschool6 on 5/3/2017.
 */

// Attach a submit handler to the form
$("#customerForm").submit(function (event) {
    // Stop form from submitting normally
    event.preventDefault();
    // Get some values from elements on the page:
    var $form = $(this),
        $customerID = $form.find("input[name='customerID']").val(),
        $url = $form.attr("action");
    // Send the data using post
    var posting = $.post($url, {customerID: $customerID});
    // Put the results in their places
    posting.done(function (data) {
        var content = data.split(':');
        if (content[0] == "ok") {
            $("#name").val(content[1]);
            $("#family").val(content[2]);

            $("#customerFormButton").prop("disabled", true);
            $("#customerID").prop("disabled",true);
            $("#loanTypeForm").show();

            $('<input />').attr('type', 'hidden')
                .attr('name', "customerID")
                .attr('value', $customerID)
                .appendTo('#loanForm');
        }
        else {
            content.splice(0, 1);
            alert(content.join(':'))
        }
    });
});

$("#loanForm").submit(function (event) {
    var $loanTypeID = $('#loanType').find(":selected").attr('id');
    $('<input />').attr('type', 'hidden')
        .attr('name', "loanTypeID")
        .attr('value', $loanTypeID)
        .appendTo('#loanForm');

    //disabling loanType select field to not included in form data.
    $('#loanType').prop("disabled",true);
});