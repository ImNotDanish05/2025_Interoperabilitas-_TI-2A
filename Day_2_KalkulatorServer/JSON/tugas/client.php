<?php
$url = "http://10.176.99.199:5000/rpc";

$data = array(
    "jsonrpc" => "2.0",
    "method"  => "sample.subtract",
    "params"  => array(7, 4),
    "id"      => 2
);

$options = array(
    "http" => array(
        "header"  => "Content-Type: application/json\r\n",
        "method"  => "POST",
        "content" => json_encode($data)
    )
);

$context = stream_context_create($options);
$result = file_get_contents($url, false, $context);

echo $result;
?>
