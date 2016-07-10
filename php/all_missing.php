<?php
require "conn.php";
echo "HERE<br>";
$sql = "SELECT * FROM academic INNER JOIN school ON academic.sid = school.sid";
echo $sql."<br>";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
     // output data of each row
     while($row = $result->fetch_assoc()) {
	 $name = $row["name"];
	 echo $name."<br>";
     }
}
else{
     echo "No missing";
}
$conn->close();
?>
