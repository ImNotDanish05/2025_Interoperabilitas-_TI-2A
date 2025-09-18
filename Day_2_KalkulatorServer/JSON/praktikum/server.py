from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/rpc', methods=['POST'])
def rpc():
    data = request.get_json()

    # Cek method yang diminta client
    if data.get("method") == "sample.add":
        # Jalankan operasi penjumlahan
        result = sum(data.get("params", []))
        return jsonify({
            "jsonrpc": "2.0",
            "result": result,
            "id": data.get("id")
        })
    else:
        # Kalau method tidak ditemukan
        return jsonify({
            "jsonrpc": "2.0",
            "error": "Method not found",
            "id": data.get("id")
        })

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
