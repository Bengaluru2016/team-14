<?php

require "conn.php"

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error)
{
    die("Connection failed: " . $conn->connect_error);
}
// sql to create table
$sql = "insert into Persons(FirstName,LastName,Age) values ('Sai', 'Geetha', 20)"; 

if ($conn->query($sql) === TRUE){
    echo "Record Inserted successfully";
}
else{
    echo "Error: " . $sql . "<br>" . $conn->error;
}
?>
