<?php

require_once 'db.php';

function getAllProducts() {
    global $connection;
    $result = mysqli_query($connection, "SELECT * FROM products");
    $products = [];
    while ($row = mysqli_fetch_assoc($result)) {
        $products[] = $row;
    }
    echo json_encode($products);
}

function getProduct($id) {
    global $connection;
    $id = intval($id);
    $query = "SELECT * FROM products WHERE id = $id";
    $result = mysqli_query($connection, $query);
    echo json_encode(mysqli_fetch_assoc($result));
}

function createProduct() {
    global $connection;
    $data = json_decode(file_get_contents("php://input"), true);
    $name = mysqli_real_escape_string($connection, $data['product_name']);
    $price = floatval($data['price']);
    $quantity = intval($connection, $data['quantity']);
    $seller = mysqli_real_escape_string($connection, $data['seller']);

    $query = "INSERT INTO products (product_name, price, quantity, seller) 
              VALUES ('$name', $price, '$quantity', '$seller')";

    if (mysqli_query($connection, $query)) {
        echo json_encode(["message" => "Produk berhasil ditambahkan"]);
    } else {
        echo json_encode(["error" => mysqli_error($connection)]);
    }
}

function updateProduct($id) {
    global $connection;
    $data = json_decode(file_get_contents("php://input"), true);
    $id = intval($id);
    $name = mysqli_real_escape_string($connection, $data['product_name']);
    $price = floatval($data['price']);
    $quantity = intval($connection, $data['quantity']);
    $seller = mysqli_real_escape_string($connection, $data['seller']);

    $query = "UPDATE products 
              SET product_name='$name', price=$price, quantity='$quantity', seller='$seller' 
              WHERE id=$id";

    if (mysqli_query($connection, $query)) {
        echo json_encode(["message" => "Produk ID $id berhasil diupdate"]);
    } else {
        echo json_encode(["error" => mysqli_error($connection)]);
    }
}

function deleteProduct($id) {
    global $connection;
    $id = intval($id);
    $query = "DELETE FROM products WHERE id=$id";
    if (mysqli_query($connection, $query)) {
        echo json_encode(["message" => "Produk ID $id berhasil dihapus"]);
    } else {
        echo json_encode(["error" => mysqli_error($connection)]);
    }
}