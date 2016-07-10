<?php
 ob_start();
  include("conn.php");
  
  $username = mysql_real_escape_string($_POST['username']);
  $password = mysql_real_escape_string($_POST['password']); 
  
  $checklogin = mysql_query("select * from vregistration where username='".$username."' and password='".$password."'") or die(mysql_error());

  $count  = mysql_num_rows($checklogin);
  
 
  if($count>0)
  {
    $message = "Login Successful";
	session_start();
	$_SESSION["username"] = $username;
	echo "<script type='text/javascript'>alert('$message');</script>";
	sleep(5);
	header('Location:/basic.html');
  }
  else
  {
	$message = "No Login Found";
	session_unset(); 
	session_destroy();
	echo "<script type='text/javascript'>alert('$message');</script>";
	sleep(3);
    header('Location:loginerror.html');
  }
  
 ob_end_flush();
?>