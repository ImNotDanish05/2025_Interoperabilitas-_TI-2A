import tkinter as tk
from tkinter import ttk, messagebox
from xmlrpc.client import ServerProxy


class ClientGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Kalkulator Client-Server (XML-RPC)")
        self.root.geometry("400x300")

        self.client = None

        # Frame server
        server_frame = ttk.LabelFrame(root, text="Server")
        server_frame.pack(fill="x", padx=10, pady=5)

        ttk.Label(server_frame, text="IP:").grid(row=0, column=0, padx=5, pady=5, sticky="w")
        self.ip_entry = ttk.Entry(server_frame)
        self.ip_entry.insert(0, "127.0.0.1")
        self.ip_entry.grid(row=0, column=1, padx=5, pady=5)

        ttk.Label(server_frame, text="Port:").grid(row=1, column=0, padx=5, pady=5, sticky="w")
        self.port_entry = ttk.Entry(server_frame)
        self.port_entry.insert(0, "1717")
        self.port_entry.grid(row=1, column=1, padx=5, pady=5)

        self.connect_button = ttk.Button(server_frame, text="Connect", command=self.connect_server)
        self.connect_button.grid(row=2, column=0, padx=5, pady=5)

        self.status_label = ttk.Label(server_frame, text="Belum terhubung")
        self.status_label.grid(row=2, column=1, padx=5, pady=5, sticky="w")

        # Frame kalkulator
        kalk_frame = ttk.LabelFrame(root, text="Kalkulator")
        kalk_frame.pack(fill="x", padx=10, pady=5)

        ttk.Label(kalk_frame, text="Operasi:").grid(row=0, column=0, padx=5, pady=5, sticky="w")
        self.operasi_box = ttk.Combobox(
            kalk_frame,
            values=["Penjumlahan", "Pengurangan", "Perkalian", "Pembagian"],
            state="readonly"
        )
        self.operasi_box.current(0)
        self.operasi_box.grid(row=0, column=1, padx=5, pady=5)

        ttk.Label(kalk_frame, text="X:").grid(row=1, column=0, padx=5, pady=5, sticky="w")
        self.x_entry = ttk.Entry(kalk_frame)
        self.x_entry.grid(row=1, column=1, padx=5, pady=5)

        ttk.Label(kalk_frame, text="Y:").grid(row=2, column=0, padx=5, pady=5, sticky="w")
        self.y_entry = ttk.Entry(kalk_frame)
        self.y_entry.grid(row=2, column=1, padx=5, pady=5)

        self.hitung_button = ttk.Button(kalk_frame, text="Hitung", command=self.hitung)
        self.hitung_button.grid(row=3, column=0, padx=5, pady=5)

        self.hasil_label = ttk.Label(kalk_frame, text="Hasil: -")
        self.hasil_label.grid(row=3, column=1, padx=5, pady=5, sticky="w")

    def connect_server(self):
        ip = self.ip_entry.get().strip()
        port = self.port_entry.get().strip()
        url = f"http://{ip}:{port}/RPC2"
        try:
            self.client = ServerProxy(url)
            # Test ping
            self.client.server.hitungPenjumlahan(0, 0)
            self.status_label.config(text=f"✅ Terhubung ke {ip}:{port}")
        except Exception as e:
            self.status_label.config(text=f"❌ Gagal: {e}")
            self.client = None

    def hitung(self):
        if self.client is None:
            messagebox.showwarning("Error", "Belum terhubung ke server!")
            return
        try:
            x = int(self.x_entry.get())
            y = int(self.y_entry.get())

            operasi = self.operasi_box.get()
            if operasi == "Penjumlahan":
                hasil = self.client.server.hitungPenjumlahan(x, y)
            elif operasi == "Pengurangan":
                hasil = self.client.server.hitungPengurangan(x, y)
            elif operasi == "Perkalian":
                hasil = self.client.server.hitungPerkalian(x, y)
            elif operasi == "Pembagian":
                hasil = self.client.server.hitungPembagian(x, y)
            else:
                hasil = "Operasi tidak dikenal"

            self.hasil_label.config(text=f"Hasil: {hasil}")
        except Exception as e:
            self.hasil_label.config(text=f"Error: {e}")


if __name__ == "__main__":
    root = tk.Tk()
    app = ClientGUI(root)
    root.mainloop()
