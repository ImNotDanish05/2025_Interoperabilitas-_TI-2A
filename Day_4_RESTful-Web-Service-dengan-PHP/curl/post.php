<?php
$data = array(
    'product_name' => 'danish05_Television Baru',
    'price' => 1000000,
    'quantity' => 10,
    'seller' => 'XYZ Traders'
);

$url = 'http://localhost/Interoperabilitas/Day_4_RESTful-Web-Service-dengan-PHP/index.php/api/products/';
$ch = curl_init($url);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data));
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
$response_json = curl_exec($ch);
curl_close($ch);

$response = json_decode($response_json, true);
print_r($response);
?>
