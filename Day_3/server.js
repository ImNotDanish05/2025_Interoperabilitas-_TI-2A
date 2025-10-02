const WebSocket = require('ws');

// Membuat server WebSocket di port 8080
const wss = new WebSocket.Server({ port: 8080 });

wss.on('connection', (ws) => {
  console.log('Client terhubung');

  // Kirim pesan awal ke client
  ws.send('Selamat datang di WebSocket server!');

  // Event ketika server menerima pesan dari client
  ws.on('message', (message) => {
    console.log(`Pesan dari client: ${message}`);
    // Balas pesan ke client
    ws.send(`Server menerima: ${message}`);
  });

  // Event ketika koneksi client ditutup
  ws.on('close', () => {
    console.log('Client terputus');
  });
});

console.log('WebSocket server berjalan di ws://localhost:8080');
