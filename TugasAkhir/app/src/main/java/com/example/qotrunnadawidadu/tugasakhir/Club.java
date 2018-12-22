package com.example.qotrunnadawidadu.tugasakhir;

public class Club {

    private String nama, des, pemain, sejarah, foto;

    public Club(String nama, String des, String pemain, String sejarah, String foto) {
        this.nama = nama;
        this.des = des;
        this.pemain = pemain;
        this.sejarah = sejarah;
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getPemain() {
        return pemain;
    }

    public void setBahan(String pemain) {
        this.pemain = pemain;
    }

    public String getSejarah() {
        return sejarah;
    }

    public void setProses(String proses) {
        this.sejarah = sejarah;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Club() {
    }
}
