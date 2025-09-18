import fetch from "node-fetch";

const url = "http://10.176.99.199:5000/rpc";

const payload = {
  jsonrpc: "2.0",
  method: "sample.add",
  params: [10, 2],
  id: 3
};

const res = await fetch(url, {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify(payload)
});

const json = await res.json();
console.log("Hasil dari server:", json);
