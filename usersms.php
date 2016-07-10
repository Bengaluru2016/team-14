

<?php 
	function userregsms($mobile='1234567890',$body='abcd'){

		$post_data = array(
    
    'To'    => '.$mobile.',
    'Body'  => $body;

		);

		$exotel_sid = "jpmc51"; // Your Exotel SID - Get it from here: http://my.exotel.in/Exotel/settings/site#api-settings
		$exotel_token = "02188acb7c5c6251fab8c4c452f5c584e24e368d"; // Your exotel token - Get it from here: http://my.exotel.in/Exotel/settings/site#api-settings
		$ch = curl_init();
		curl_setopt($ch, CURLOPT_VERBOSE, 1);
		curl_setopt($ch, CURLOPT_URL, $url);
		curl_setopt($ch, CURLOPT_POST, 1);
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($ch, CURLOPT_FAILONERROR, 0);
		curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
		curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($post_data));
		$http_result = curl_exec($ch);
		$error = curl_error($ch);
	$http_code = curl_getinfo($ch ,CURLINFO_HTTP_CODE);
 
	curl_close($ch);
	print "Response = ".print_r($http_result);
	}

?>