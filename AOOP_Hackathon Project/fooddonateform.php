<?php
include 'db_connection.php';
// Form handling logic goes here
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Food Donation Form - Food Donate</title>
    <link rel="stylesheet" href="styles/home.css">
</head>
<body>
    <header>
        <div class="logo">Food <b style="color: #06C167;">Donate</b></div>
        <nav class="nav-bar">
            <ul>
                <li><a href="index.html">Home</a></li>
                <li><a href="about.html">About</a></li>
                <li><a href="contact.html">Contact</a></li>
                <li><a href="profile.php">Profile</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h1>Food Donation Form</h1>
        <form action="fooddonateform.php" method="post">
            <label for="foodType">Type of Food:</label>
            <input type="text" id="foodType" name="foodType" required>
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" required>
            <input type="submit" value="Donate Food">
        </form>
    </main>
</body>
</html>
