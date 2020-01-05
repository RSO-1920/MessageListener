var wsUri = "ws://" + document.location.hostname + ":" + document.location.port + document.location.pathname + "customer";
var websocket = new WebSocket(wsUri);

websocket.onopen = function(evt) { onOpen(evt) };
websocket.onmessage = function(evt) { onMessage(evt) };
websocket.onerror = function(evt) { onError(evt) };
var output = document.getElementById("output");

function sendMessage() {
    websocket.send(textField.value);
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}

function onMessage(event) {
    console.log("onMessage: " + event.data);
    responseField.innerHTML += event.data + "\n";
}

function onError(event) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + event.data);
}

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}