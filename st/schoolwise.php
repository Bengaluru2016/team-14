<?php
 ob_start();
  include("conn.php");
  
  
  
  $checklogin = mysql_query("select * from schoolwise") or die(mysql_error());

  $count  = mysql_num_rows($checklogin);
  
 
  if($count>0)
  {
	   echo "<strong>" . "schoolname". " " ."schoolcity". " " . "schooltype". " " . "Total_students". " " . "Average_Performance". " " . "Average_Attendance". " " . "Age"." </strong> <br> ";
     	
    while($row = $result->fetch_assoc()) {
         echo "<br>" . $row["schoolname"]. " " . $row["schoolcity"]. " " . $row["schooltype"]. " " . $row["Total_students"]. " " . $row["Average_Performance"]. " " . $row["Average_Attendance"]. " " . $row["Age"];
     	
	}
		
  }
  else
  {
	$message = "No Records found";
	echo $message;
	session_unset(); 
	session_destroy();
	//echo "<script type='text/javascript'>alert('$message');</script>";
	//sleep(3);
    //header('Location:loginerror.html');
  }
  
 ob_end_flush();
?>