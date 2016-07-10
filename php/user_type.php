<?php

//PHP script that returns the type of user given the username

require "conn.php";
$username = $_POST["username"];
$pwd = $_POST["pwd"];
$sql = "SELECT username,password,volunteertype FROM vregistration where username=".$username;
$result = $conn->query($sql);
if ($result->num_rows > 0) {
     // output data of each row
     while($row = $result->fetch_assoc()) {
	 $name = $row["username"];
	 $password = '"'.$row["password"].'"';
	 if(strcmp($password,$pwd) == 0){
	     echo $row["volunteertype"];
	 }
	else{
	     echo "9";
	}
     }
}
else {
     echo "0";
}
$conn->close();
?>
