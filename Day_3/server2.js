const WebSocket = require('ws');

// Helper buat timestamp
function getTimestamp() {
  return new Date().toLocaleString(); // contoh: "02/10/2025, 13:45:20"
}

// Membuat server WebSocket di port 8080
const wss = new WebSocket.Server({ port: 8080 });

wss.on('connection', (ws) => {
  console.log(`[${getTimestamp()}] Client terhubung`);

  // Kirim pesan awal ke client
  ws.send(`[${getTimestamp()}] Selamat datang di WebSocket server!`);

  // Event ketika server menerima pesan dari client
  ws.on('message', (message) => {
    console.log(`[${getTimestamp()}] Pesan dari client: ${message}`);

    // Broadcast ke semua client yang terhubung
    wss.clients.forEach((client) => {
      if (client.readyState === WebSocket.OPEN) {
        client.send(`[${getTimestamp()}] ${message}`);
      }
    });
  });

  // Event ketika koneksi client ditutup
  ws.on('close', () => {
    console.log(`[${getTimestamp()}] Client terputus`);
  });
});

console.log(`[${getTimestamp()}] WebSocket server berjalan di ws://localhost:8080`);
