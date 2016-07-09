<?php
require "conn.php";
$sid = $_POST["sid"];
$classin = $_POST["class"];
$attendance = $_POST["attendance"];
$performance = $_POST["performance"];

echo $sid."<br>";
echo $classin."<br>";
echo $attendance."<br>";

if($attendance == 0){
	//Missing student -- no attendance
	$sql = "update academic set missing=1 where sid=".$sid;
	$result = $conn->query($sql);
	if($result){
		echo "Missing";
	}
	else{
		echo "Couldn't update missing";
	}
}

echo $performance."<br>";

$sql = "insert into academic(sid,class,attendance,performance) values(".$sid.",".$classin.",$attendance,".$performance.")";
echo $sql."<br>";

$result = $conn->query($sql);
if($result){
     echo "Values inserted<br>";
}
else{
	echo "Values not inserted<br>";
}

$conn->close();
?>
