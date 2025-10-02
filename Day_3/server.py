import asyncio
import websockets
from datetime import datetime

# Helper buat timestamp
def get_timestamp():
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

# Handler untuk tiap koneksi client
async def handler(websocket):
    print(f"[{get_timestamp()}] Client terhubung")
    await websocket.send(f"[{get_timestamp()}] Selamat datang di WebSocket server!")

    try:
        async for message in websocket:
            print(f"[{get_timestamp()}] Pesan dari client: {message}")
            await websocket.send(f"[{get_timestamp()}] Server menerima: {message}")
    except websockets.exceptions.ConnectionClosed:
        print(f"[{get_timestamp()}] Client terputus")

# Main: menjalankan server di port 8080
async def main():
    async with websockets.serve(handler, "localhost", 8080):
        print(f"[{get_timestamp()}] WebSocket server berjalan di ws://localhost:8080")
        await asyncio.Future()  # biar server tetap jalan

if __name__ == "__main__":
    asyncio.run(main())