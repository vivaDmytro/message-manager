<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" type="text/css" href="css/main.css" />
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

    <script type="text/javascript" language="JavaScript" src="js/message.js"></script>
    <script type="text/javascript" language="JavaScript" src="js/main.js"></script>
    <script type="text/javascript" language="JavaScript" src="js/deleteItem.js"></script>
    <script type="text/javascript" language="JavaScript" src="js/editItem.js"></script>
    <script type="text/javascript" language="JavaScript" src="js/addItem.js"></script>

    <title>Hello</title>

</head>
<body>
    <h1>Main View</h1>
    <div id="content">
        <table id="messages" border="1">
            <tr>
                <th>User</th>
                <th>Message</th>
                <th>Actions</th>
            </tr>
            <tbody></tbody>
        </table>
    </div>
    <div id="dialog" title="Add new item">
        <p>User:<p>
        <input id="uname", type="text"/>
        <p>Message:<p>
        <input id="utext", type="text"/>
    </div>
    <button id = "opener">Open Dialog</button>
</body>
</html>