<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="styles/tic-tac-toe.css">
    <title>Tic-Tac-Toe</title>
</head>

<body>
<h1>Tic-Tac-Toe</h1>
<p><span id="current-player-container"></span><span id="current-player-message-container"></span></p>
<div id="tic-tac-toe-container">
    <svg height="225" width="225" id="figure-container">
        <!-- Board -->
        <line x1="75" y1="0" x2="75" y2="225" />
        <line x1="150" y1="0" x2="150" y2="225" />
        <line x1="0" y1="75" x2="225" y2="75" />
        <line x1="0" y1="150" x2="225" y2="150" />

        <g id="xs-and-os">
        </g>
    </svg>
</div>

<!-- Container for final message -->
<div id="popup-container">
    <div id="modal">
        <h2 id="final-message"></h2>
        <button id="play-button">Play Again</button>
    </div>
</div>

<!-- Notification -->
<div id="notification-container">
    <p>That box has already been clicked</p>
</div>

<script src="scripts/tic-tac-toe.js"> </script>
</body>

</html>