function createPopup(headingText, message, okButtonText, cancelButtonText, onOk, onCancel, onClose) {
    var popupContainer = $('<div class="custom-popup-container" id="popup-container"></div>');

    var popupContent = $('<div class="card custom-popup-card"></div>');

    var cardBody = $('<div class="card-body m-3"></div>');

    var closeSpan = $('<span class="close-button">&times;</span>');
    closeSpan.click(function() {
        hidePopup(onClose);
    });

    var h2 = $('<h2>' + headingText + '</h2>');

    var p = $('<p>' + message + '</p>');

    var buttonDiv = $('<div class="text-center" style="margin-top: 20px;"></div>');

    var okButton = $('<button class="btn btn-success">' + okButtonText + '</button>');
    okButton.click(function() {
        hidePopup(onOk);
    });

    var cancelButton = $('<button class="btn btn-danger" style="margin-left: 10px;">' + cancelButtonText + '</button>');
    cancelButton.click(function() {
        hidePopup(onCancel);
    });

    buttonDiv.append(okButton);
    buttonDiv.append(cancelButton);

    cardBody.append(closeSpan);
    cardBody.append(h2);
    cardBody.append(p);
    cardBody.append(buttonDiv);

    popupContent.append(cardBody);
    popupContainer.append(popupContent);

    // Append the popup after the body tag
    $('body').prepend(popupContainer);
}

function hidePopup(onClose) {
    $('#popup-container').fadeOut(400, function() {
        $(this).remove();
        if (onClose) onClose();
    });
}
/* demo for use popup */
/*
$(document).ready(function() {
    createPopup(
        'Confirm', // Heading
        'Your file is currently downloading. Please wait for the download to complete.', // Message
        'OK', // Text for OK button
        'Cancel', // Text for Cancel button
        function() {
            alert('ok');
            // On OK function
            console.log('OK button clicked');

        },
        function() {
            alert('cancel');
            // On Cancel function
            console.log('Cancel button clicked');
        },
        function() {
            alert('close');
            // On Close function
            console.log('Popup closed');
        }
    );
});
*/

////////////////////////////


