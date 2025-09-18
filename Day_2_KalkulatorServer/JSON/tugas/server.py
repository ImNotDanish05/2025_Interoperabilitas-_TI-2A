from flask import Flask, request, jsonify
import operator
from functools import reduce

app = Flask(__name__)

@app.route('/rpc', methods=['POST'])
def rpc():
    data = request.get_json()
    method = data.get("method")
    params = data.get("params", [])
    request_id = data.get("id")

    # Pastikan method dan params valid
    if not isinstance(params, list) or not params:
        return jsonify({
            "jsonrpc": "2.0",
            "error": {"code": -32602, "message": "Invalid params: must be a non-empty list"},
            "id": request_id
        })

    # --- Logika untuk memilih metode ---
    if method == "sample.add":
        # Menjumlahkan semua angka di dalam list
        result = sum(params)
        return jsonify({"jsonrpc": "2.0", "result": result, "id": request_id})

    elif method == "sample.multiply":
        # Mengalikan semua angka di dalam list
        # reduce(operator.mul, [2, 3, 4]) akan menghitung (2*3)*4
        result = reduce(operator.mul, params)
        return jsonify({"jsonrpc": "2.0", "result": result, "id": request_id})

    elif method == "sample.subtract":
        # Mengurangi angka pertama dengan semua angka berikutnya
        # reduce(operator.sub, [100, 20, 10]) akan menghitung (100-20)-10
        result = reduce(operator.sub, params)
        return jsonify({"jsonrpc": "2.0", "result": result, "id": request_id})

    else:
        # Jika method tidak ditemukan
        return jsonify({
            "jsonrpc": "2.0",
            "error": {"code": -32601, "message": "Method not found"},
            "id": request_id
        })

if __name__ == '__main__':
    # host="0.0.0.0" agar bisa diakses dari komputer lain di jaringan
    app.run(host="0.0.0.0", port=5000)