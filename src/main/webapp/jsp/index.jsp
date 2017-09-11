<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/main.css" />

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

    <script type="text/javascript" language="JavaScript" src="js/main.js"></script>
    <script type="text/javascript" language="JavaScript" src="js/deleteItem.js"></script>
    <script type="text/javascript" language="JavaScript" src="js/editItem.js"></script>
    <script type="text/javascript" language="JavaScript" src="js/addItem.js"></script>

    <title>Message Managing System</title>

</head>
<body>
<div class="ui-widget">
    <div class="ui-state-error ui-corner-all">
        <p>
            <strong>Alert:</strong> Incorrect data.
        </p>
    </div>
</div>
    <h1>Message Managing System</h1>
    <div id="content" class="centered">
        <table id="messages" border="1">
            <tr>
                <th>User</th>
                <th>Message</th>
                <th>Actions</th>
            </tr>
            <tbody></tbody>
        </table>
        <button id = "opener" class="button button2">Add item</button>
    </div>
    <div id="dialog" title="Add new item">
        <p>User:<p>
        <input id="uname" type="text"/>
        <p>Message:<p>
        <input id="utext" type="text"/>
    </div>
</body>
</html>