import requests

ip = input("Masukkan IP server (misal: localhost): ")
port = input("Masukkan port server (misal: 1717): ")
url = f"http://{ip}:{port}/rpc"

payload = {
    "jsonrpc": "2.0",
    "method": "sample.add",
    "params": [5, 3],
    "id": 1
}

res = requests.post(url, json=payload).json()
print(res)