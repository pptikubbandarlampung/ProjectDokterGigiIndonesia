package lab_iot_ubl.aseptrisnasetiawan.appvellidcom.Models;



import com.google.gson.annotations.SerializedName;

public class EventItem {

    @SerializedName("id")
    private String id;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("tittle")
    private String title;


    @SerializedName("narasumber")
    private String narasumber;


    @SerializedName("materi")
    private String materi;


    @SerializedName("kouta")
    private String kouta;


    @SerializedName("deskripsi")
    private String deskripsi;


    @SerializedName("lokasi")
    private String lokasi;

    @SerializedName("cp")
    private String cp;

    @SerializedName("email")
    private String email;

    @SerializedName("tgl_mulai")
    private String tgl_mulai;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("batas_early_bird")
    private String batas_early_bird;

    @SerializedName("biaya_early_bird")
    private String biaya_early_bird;

    @SerializedName("batas_reguler")
    private String batas_reguler;

    @SerializedName("biaya_reguler")
    private String biaya_reguler;

    @SerializedName("biaya_late")
    private String biaya_late;

    @SerializedName("batas_late")
    private String batas_late;

    @SerializedName("handon")
    private String handon;

    @SerializedName("pamflet")
    private String pamlet;

    @SerializedName("jam_mulai")
    private String jam_mulai;

    @SerializedName("jam_selesai")
    private String jam_selesai;




    public void setTgl_selesai(String tgl_selesai) {
        this.tgl_selesai= tgl_selesai;
    }

    public String getTgl_selesai() {

        return tgl_selesai;
    }


    public void setTgl_mulai(String tgl_mulai) {
        this.tgl_mulai= tgl_mulai;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }



    public void setDeskripsi(String deskripsi) {
        this.deskripsi= deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }



    public void setBiaya_early_bird(String biaya_early_bird) {
        this.biaya_early_bird= biaya_early_bird;
    }

    public String getBiaya_early_bird() {
        return biaya_early_bird;
    }




    public void setBatas_reguler(String batas_reguler) {
        this.batas_reguler= batas_reguler;
    }

    public String getBatas_reguler() {
        return batas_reguler;
    }


    public void setBiaya_reguler(String biaya_reguler) {
        this.biaya_reguler= biaya_reguler;
    }

    public String getBiaya_reguler() {
        return biaya_reguler;
    }


    public void setBiaya_late(String biaya_late) {
        this.biaya_late= biaya_late;
    }

    public String getBiaya_late() {
        return biaya_late;
    }


    public void setBatas_late(String batas_late) {
        this.batas_late= batas_late;
    }

    public String getBatas_late() {
        return batas_late;
    }



    public void setHandon(String handon) {
        this.handon= handon;
    }

    public String getHandon() {
        return handon;
    }

    public void setBatas_early_bird(String batas_early_bird) {
        this.batas_early_bird= batas_early_bird;
    }

    public String getBatas_early_bird() {
        return batas_early_bird;
    }



    public void setKategori(String kategori) {
        this.kategori= kategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPamlet(String pamlet) {
        this.pamlet = pamlet;
    }

    public String getPamlet() {
        return pamlet;
    }


    public void setNarasumber(String narasumber)
    {
        this.narasumber = narasumber;
    }

    public String getNarasumber() {
        return narasumber;

    }

    public void setMateri(String materi) {
        this.materi = materi;

    }

    public String getMateri() {
        return materi;
    }


    public void setKouta(String kouta) {
        this.kouta = kouta;

    }

    public String getKouta() {
        return kouta;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;

    }

    public String getLokasi() {
        return lokasi;
    }




    public void setCp(String cp) {
        this.cp = cp;

    }

    public String getCp() {
        return cp;
    }



    public void setEmail(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }


    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;

    }

    public String getJam_mulai() {
        return jam_mulai;
    }



    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;

    }

    public String getJam_selesai()
    {
        return jam_selesai;
    }




    @Override
    public String toString() {
        return
                "EventItem{" +
                        "tittle = '" + title + '\'' +
                        ",pamflet= '" + pamlet + '\'' +
                        ",id = '" + id + '\'' +
                        ",batas_reguler  = '" + batas_reguler + '\'' +
                        ",narasumber = '" + narasumber + '\'' +
                        ",materi = '" + materi + '\'' +
                        ",kouta = '" + kouta + '\'' +
                        ",tanggal_mulai = '" + tgl_mulai + '\'' +
                        ",tanggal_selesai = '" + tgl_selesai + '\'' +
                        ",batas_early_bird = '" + batas_early_bird+ '\'' +
                        ",lokasi = '" + lokasi+ '\'' +
                        ",cp = '" + cp+ '\'' +
                        ",email = '" + email+ '\'' +
                        ",kategori= '" + kategori+ '\'' +
                        ",biaya_late = '" + biaya_late+ '\'' +
                        ",batas_late= '" + batas_late+ '\'' +
                        ",biaya_early_bird = '" + biaya_early_bird+ '\'' +
                        ", handon= '" + handon+ '\'' +
                        ",deskripsi = '" + deskripsi+ '\'' +
                        ", pamlet= '" + pamlet+ '\'' +
                        ",jam_mulai = '" + jam_mulai+ '\'' +
                        ", jam_selesai= '" + jam_selesai+ '\'' +
                        "}";
    }
}