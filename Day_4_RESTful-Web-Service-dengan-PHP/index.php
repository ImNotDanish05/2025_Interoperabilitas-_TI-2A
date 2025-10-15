<?php
header("Content-Type: application/json");
require_once 'functions.php';

// ambil method HTTP
$method = $_SERVER['REQUEST_METHOD'];

// ambil path dari REQUEST_URI
$request_uri = $_SERVER['REQUEST_URI'];
$base_pos = strpos($request_uri, 'index.php');
$path = ($base_pos !== false) ? substr($request_uri, $base_pos + strlen('index.php')) : $request_uri;
$path = trim($path, '/');

// pecah path jadi array segmen
$parts = explode('/', $path);

if ($parts[0] === 'api' && $parts[1] === 'products') {
    switch ($method) {
        case 'GET':
            if (isset($parts[2])) {
                getProduct($parts[2]);   // GET /api/products/2
            } else {
                getAllProducts();        // GET /api/products
            }
            break;

        case 'POST':
            createProduct();             // POST /api/products
            break;

        case 'PUT':
            if (isset($parts[2])) {
                updateProduct($parts[2]); // PUT /api/products/2
            } else {
                echo json_encode(["error" => "ID diperlukan untuk update"]);
            }
            break;

        case 'DELETE':
            if (isset($parts[2])) {
                deleteProduct($parts[2]); // DELETE /api/products/2
            } else {
                echo json_encode(["error" => "ID diperlukan untuk delete"]);
            }
            break;

        default:
            header("HTTP/1.0 405 Method Not Allowed");
            echo json_encode(["error" => "Metode tidak didukung"]);
            break;
    }
} else {
    echo json_encode(["error" => "Endpoint tidak ditemukan"]);
}
