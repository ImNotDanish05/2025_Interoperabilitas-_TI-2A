<?php

namespace App\Http\Controllers;

use App\Models\Mahasiswa;
use Illuminate\Http\Request;

class MahasiswaController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        return Mahasiswa::all();
        // return "test";
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $validated = $request->validate([
            'nama'    => 'required|string|max:255',
            'nim'     => 'required|string|max:20|unique:mahasiswas',
            'jurusan' => 'required|string',
        ]);

        $mahasiswa = Mahasiswa::create($validated);
        return response()->json([
            'message' => 'Data berhasil disimpan',
            'data' => $mahasiswa
        ]);
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $mahasiswa = Mahasiswa::find($id);

        if (!$mahasiswa) {
            return response()->json([
                'message' => 'Data Mahasiswa ' . $id . ' tidak ditemukan'
            ], 404);
        }

        return response()->json($mahasiswa);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Mahasiswa $mahasiswa)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Mahasiswa $mahasiswa)
    {
        $validated = $request->validate([
            'nama'    => 'string|max:255',
            'nim'     => 'string|max:20|unique:mahasiswas,nim,' . $mahasiswa->id,
            'jurusan' => 'string',
        ]);

        $mahasiswa->update($validated);

        return $mahasiswa;
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Mahasiswa $mahasiswa)
    {
        $mahasiswa->delete();

        return response()->json([
            'message' => 'Deleted successfully'
        ]);
    }
}