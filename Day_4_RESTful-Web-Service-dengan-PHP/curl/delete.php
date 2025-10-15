<?php
$url = 'http://localhost/Interoperabilitas/Day_4_RESTful-Web-Service-dengan-PHP/index.php/api/products/16';
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'DELETE');
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response_json = curl_exec($ch);
curl_close($ch);

$response = json_decode($response_json, true);
print_r($response);
?>