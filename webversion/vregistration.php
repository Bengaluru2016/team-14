<?php
require "conn.php";

$user_name = $_POST["username"];
$user_pass = $_POST["password"];

$qry = "select * from logincheck where username like $user_name and password like $user_pass;";

$result = mysqli_query($conn , $qry);


if(mysqli_num_rows($result) > 0){
	echo " Login success";
}
else {
	echo " Login not success";
}
?>