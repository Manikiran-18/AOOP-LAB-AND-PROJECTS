<?php
session_start();
// Here you can fetch user data from the database and display it
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile - Food Donate</title>
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
                <li><a href="profile.php" class="active">Profile</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h1>Your Profile</h1>
        <p>Welcome, [User Name]!</p>
        <!-- Display user-specific data here -->
    </main>
</body>
</html>
