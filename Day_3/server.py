import asyncio
import websockets
from datetime import datetime

# Simpan semua client yang terhubung
connected_clients = set()

# Helper timestamp
def get_timestamp():
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

async def handler(websocket):
    # Tambah client baru
    connected_clients.add(websocket)
    print(f"[{get_timestamp()}] Client terhubung")

    # Kirim pesan awal ke client baru
    await websocket.send(f"[{get_timestamp()}] Selamat datang di WebSocket server!")

    try:
        async for message in websocket:
            print(f"[{get_timestamp()}] Pesan dari client: {message}")

            # Broadcast ke semua client yang masih terhubung
            for client in connected_clients:
                if client.open:
                    await client.send(f"[{get_timestamp()}] {message}")

    except websockets.exceptions.ConnectionClosed:
        pass
    finally:
        # Hapus client yang sudah disconnect
        connected_clients.remove(websocket)
        print(f"[{get_timestamp()}] Client terputus")

async def main():
    async with websockets.serve(handler, "0.0.0.0", 8080):
        print(f"[{get_timestamp()}] WebSocket server berjalan di ws://0.0.0.0:8080")
        await asyncio.Future()


if __name__ == "__main__":
    asyncio.run(main())
