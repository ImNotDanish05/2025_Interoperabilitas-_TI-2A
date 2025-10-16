<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Mahasiswa;
use Illuminate\Support\Str;

class MahasiswaSeeder extends Seeder
{
    /**
     * Jalankan seeder untuk tabel mahasiswas.
     */
    public function run(): void
    {
        $names = [
            'Saitama',
            'Genos',
            'Tatsumaki',
            'Bang',
            'King',
            'Fubuki',
            'Mumen Rider',
            'Sonic',
            'Garou',
            'Dr. Kuseno'
        ];

        foreach ($names as $index => $name) {
            Mahasiswa::create([
                'nama' => $name,
                'nim' => 'NIM' . str_pad($index + 1, 5, '0', STR_PAD_LEFT),
                'jurusan' => 'Teknologi Rekayasa Komputer',
            ]);
        }
    }
}
