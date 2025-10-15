<?php
$connection = mysqli_connect('localhost', 'root', '', 'rest_api');
if (!$connection) {
    die(json_encode(["error" => "Koneksi ke database gagal: " . mysqli_connect_error()]));
}
