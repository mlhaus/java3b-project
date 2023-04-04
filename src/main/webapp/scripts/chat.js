// STEP 1: Instantiate a WebSocket Object
const wsMessageUri = "ws://" + document.location.host + document.location.pathname + "/messageEndpoint";
const chatWebsocket = new WebSocket(wsMessageUri);

// STEP 2: Define the 4 endpoint methods
chatWebsocket.onopen = function(event) {

};

chatWebsocket.onclose = function(event) {

};

chatWebsocket.onmessage = function(event) {
    // STEP 12: Handle incoming messages
    updateTextArea(event.data, "in");
};

chatWebsocket.onerror = function(event) {

};

// STEP 5: Define a placeholder for messages
function displayError(msg) {
    var errdiv = document.getElementById("errorText");
    errdiv.innerHTML = msg;
}

/*
 * Send the message to the server endpoint and log the activity to the console
 */
function sendText(json) {
    // console.log("sending text: " + json);
    // STEP 10b: Send json to the WebSocket only if the connection is open
    if(isOpen(chatWebsocket)) {
        chatWebsocket.send(json);
    } else {
        displayError("Connection not available. Try later.")
    }
}
// STEP 10C: check if the WebSocket connection is open
function isOpen(chatWebsocket) {
    return chatWebsocket.readyState === chatWebsocket.OPEN
}

/*
 * Update the textarea by appending the supplied text to the text that is
 * already there.  The text shows up as JSON, so it has to be parsed into
 * a JSON object to let us retrieve the data.
 */
function updateTextArea(data, inOut) {
    // STEP 9b - convert the string back to JSON
    // Parse the data as JSON so the fields can be accessed
    var json = JSON.parse(data);
    // STEP 9c - get data from JSON
    // Use the JSON notation to retrieve the data fields
    var name = json.name;
    var message = json.message;
    // STEP 9d - output the data
    // STEP 9e - style the output in CSS
    // Build the text to display then show it
    var out = (inOut == "in") ? "<div class=\"in\">" : "<div class=\"out\">";
    out += "<p>" + message + "</p><span>";
    out += (inOut == "in") ? name  : "Me";
    out += "</span></div>"
    var textArea = document.getElementById("messages");
    textArea.innerHTML = textArea.innerHTML + out;
    // Attempt to move the scrolling of the textarea to show the lowest item
    // The effectiveness of this varies by browser
    textArea.scrollTop = textArea.scrollHeight;
    // Logging only helps when you have the browser's developer tools open
    // console.log("Writing: " + data);
}

/*
 * Clear any existing text from the message box and set focus there
 */
function prepMessageBox() {
    // STEP 11c - Get form ready for the next message
    var messageBox = document.getElementById("message");
    messageBox.value = "";
    messageBox.focus();
}

// STEP 3: Create an eventHandler
const messageForm = document.getElementById("messageForm");
messageForm.addEventListener("submit", function(event) {
    // STEP 4: When working with forms, prevent the default method of submitting to the servlet.
    // JavaScript will process the form, not Java
    event.preventDefault();
    displayError(""); // Clears any previous error message
    // STEP 6: Get data from the user
    // Get the user name
    var userName = document.getElementById("userName").value;
    // STEP 7: Add logic to validate input
    if (userName === "") {
        displayError("Name is required");
        return;
    }
    // Get the test of the message.  If the message is blank, use "..."
    var message = document.getElementById("message").value;
    if (message === "") {
        displayError("Message is required");
        return;
    }
    // STEP 8: Create JSON and turn it into a string
    // Build a JSON object and convert it to a string so it can be sent
    var json = JSON.stringify({
        "name": userName,
        "message": message
    });
    // STEP 9: Update the current user's screen with the JSON object
    // Update the textarea just like we would with an incoming message
    updateTextArea(json, "out");
    // STEP 10: Send JSON to the server via websocket
    // Send the message
    sendText(json);
    // STEP 11: Reset any input fields for next use
    // Set the message text field to blank so it is ready for the next message
    prepMessageBox();
});