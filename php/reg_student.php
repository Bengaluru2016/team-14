<?php
require "conn.php";
echo "here";
$sid = $_GET["sid"];
$school_name = $_GET["schoolname"];
$city = $_GET["city"];
$type = $_GET["schooltype"];

echo $sid."<br>";
echo $school_name."<br>";
echo $city."<br>";
echo $type."<br>";

$sql = "INSERT INTO school(sid,schoolname,schoolcity,schooltype) VALUES (".$sid.",".$school_name.",".$city.",".$type.")";
echo $sql."<br>";

//Check if the the student is missing
$sql = "SELECT missing FROM ";
if()

?>
