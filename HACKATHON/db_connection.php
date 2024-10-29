<?php
// Change these parameters according to your database configuration
$connection = mysqli_connect("localhost", "root", "");
$db = mysqli_select_db($connection, 'demo');

// Check connection
if (!$connection) {
    die("Connection failed: " . mysqli_connect_error());
}
?>
