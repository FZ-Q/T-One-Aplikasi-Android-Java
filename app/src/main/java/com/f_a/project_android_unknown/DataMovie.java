package com.f_a.project_android_unknown;

import com.f_a.project_android_unknown.model.ModelMovie;

import java.util.ArrayList;

public class DataMovie {
    private static String[] bioskopNames = {
            "JOKER",
            "Danur 3 : Sunyaruri",
            "Dilan 1991",
            "Now You See Me 3 - COMING SOON",
            "SIN",
            "Hangout",
            "One Piece Stampede",
            "Weathering With You"
    };

    private static String[] bioskopPrice = {
            "Rp 35.000",
            "Rp 35.000",
            "Rp 25.000",
            "Rp 30.000",
            "Rp 25.000",
            "Rp 25.000",
            "Rp 30.000",
            "Rp 25.000",
            "Rp 30.000"
    };

    private static String[] bioskopTime = {
            "Senin:\n13.00, 15.35, 18.10 \nSelasa:\n12.30, 14.40, 19.15",
            "Rabu: \n11.30, 13.00, 15.30 \nKamis: \n13.00, 14.40, 18.10",
            "Sabtu:\n13.00, 15.35, 18.10 \nMinggu:\n13.00, 15.35, 18.10",
            "Rabu: \n14.00, 16.35, 19.10 \nJumat: \n15.00, 18.35, 20.10",
            "Selasa:\n12.00, 14.15, 18.40\nRabu:  \n10.00, 12.35, 16.30",
            "Senin:\n13.00, 15.35, 18.10 \nSelasa:\n12.30, 14.40, 19.15",
            "Selasa:\n11.30, 13.00, 15.30 \nRabu: \n13.00, 14.40, 18.10",
            "Sabtu:\n14.00, 16.35, 19.10 \nMinggu:\n15.00, 18.35, 20.10"
    };

    private static String[] bioskopDetail = {
            "Joker berpusat di sekitar musuh bebuyutan ikonik dan merupakan cerita fiksi yang asli dan berdiri sendiri yang belum pernah diangkat ke layar lebar sebelumnya. Arthur Fleck adalah seorang pria yang berjuang untuk menemukan jalannya di masyarakat yang terpecah belah di Gotham. Sebagai seorang badut sewaan di siang hari, dia bercita-cita menjadi stand-up komedian di malam harinamun menyadari bahwa dia selalu menjadi lelucon bagi orang lain.\n" +
                    "Terperangkap dalam siklus eksistensi antara apatis dan kekejaman, Arthur membuat satu keputusan buruk yang menyebabkan reaksi berantai dari penumpukan peristiwa yang akhirnya membentuk karakternya yang kelam.\n" +"\nJam Tayang: \nSenin: 13.00, 15.35, 18.10 \nSelasa: 12.30, 14.40, 19.15",
            "Setelah bersahabat dengan hantu-hantu kecilnya selama bertahun-tahun, Risa (Prilly Latuconsina) yang semakin dewasa mulai merasa bahwa dirinya harus memiliki kehidupan normal seperti perempuan lainnya. Apalagi sekarang Risa sudah memiliki pacar bernama Dimas (Rizky Nazar), yang bekerja sebagai penyiar radio. Risa bahkan tidak menceritakan kepada Dimas tentang kemampuannya melihat hantu, dan kenyataan bahwa dia memiliki 5 sahabat kecil yang bukan manusia.\n"+
                    "Persahabatan Risa dan Peter CS menjadi goyah, setelah Risa merasa Peter CS mulai mengusili Dimas. Risa akhirnya memilih menutup mata batinnya agar dia bisa memulai hidup normal. Tapi ternyata, setelah menutup mata batinnya, mulai terjadi kejadian aneh. Semua dimulai dari hujan deras yang tak kunjung usai. Risa memang tidak bisa lagi melihat Peter CS, tapi Risa kembali mencium bau danur. Entah dari siapa.\n"+
                    "Ada seorang hantu jahat yang masuk ke rumah, lewat hujan deras. Seorang hantu perempuan jahat yang bukan hanya mengancam hidupnya Risa, tapi juga mengancam Peter CS. Entah apakah mungkin ini memang akhir dari persahabatan Risa dan Peter CS.\n" + "\nJam Tayang:\nRabu: 11.30, 13.00, 15.30 \nKamis: 13.00, 14.40, 18.10",
            "Kisah cinta Dilan dan Milea akan kembali berlanjut. Di film Dilan 1991 ini kisah cinta mereka akan mulai diuji. Banyak rintangan yang harus Dilan dan Milea lalui, mampukah cinta mereka bertahan?\n" + "\nJam Tayang: \nSabtu:13.00, 15.35, 18.10 \nMinggu:13.00, 15.35, 18.10",
            "Rumah produksi Lionsgate telah memulai persiapan untuk film sekuel ketiga Now You See Me setahun sebelum Now You see Me: The Second Act yang disutradarai Jon M Chu tayang di bioskop pada 2016. \"Kami telah memulai lebih awal dalam persiapan Now You See Me 3,\" kata CEO Lionsgate Jon Feltheimer.\n" +"\"Now You See Me 3\" Mulai Diproduksi sekarang\n" + "\nJam Tayang: \nRabu: 14.00, 16.35, 19.10 \nJumat: 15.00, 18.35, 20.10",
            "Metta dan Raga seharusnya tidak jatuh cinta. Raga benci cewek banyak drama seperti Metta. Metta pun hanya ingin membuktikan bahwa Raga tidak ada bagusnya. Tetapi, mereka tetap jatuh cinta. Kehidupan mereka pun menjadi kusut. Keluarga Raga menentang hubungan mereka, dan Metta mulai menjadi target musuh Raga. Tanpa alasan, Raga memutuskan hubungan dan menghilang. Saat Metta mengetahui alasan dibalik putusnya hubungan ini,\n"+
                    "Metta harus memilih antara menyerah pada takdir atau bahagia berdosa.\n" + "\nJam Tayang: \nSelasa: 12.00, 14.15, 18.40\nRabu: 10.00, 12.35, 16.30",
            "Seorang pria misterius bernama Toni Sacalu, dengan inisial P. Toni Sacalu mengundang 9 publik figur untuk ‘Hangout’ di villa di sebuah pulau terpencil,\n" + "\n" + "Hangout ini bertujuan untuk membicarakan sebuah proyek dengan sejumlah uang besar. Mereka pun berangkat memenuhi undangan tersebut.\n" + "\n" +
                    "Setibanya di sana, masalah muncul sejak malam pertama ketika Mathias Muchus mati diracun di hadapan mereka. Kendala berikutnya, mereka tidak bisa segera kembali karena perahu penjemput mereka, akan tiba lima hari kemudian. Mereka pun terjebak dalam pulau tersebut.\n" + "\nJam Tayang:\nSenin: 13.00, 15.35, 18.10 \nSelasa: 12.30, 14.40, 19.15",
            "Mereka diundang ke acara besar yang dikenal sebagai Pirates expo! Semua bajak laut terhebat berkumpul di pameran bajak laut,tiba-tiba angkatan laut ikut campur dalam kegiatan tersebut, Sebenarnya ada konspirasi apakah dibalik acara tersebut?\n"+"\nJam Tayang:\nSelasa: 11.30, 13.00, 15.30 \nRabu: 13.00, 14.40, 18.10",
            "Seorang anak muda dari desa terpencil di Shikoku, yang meninggalkan rumah dan memutuskan untuk tinggal di Tokyo. Dalam perjalanannya, dia bertemu dengan Keisuke, seorang pria aneh yang menawarkan bantuan kepadanya. Merasa aneh dengan orang tersebut, Hodoka memutuskan untuk mencoba mencari peruntungan yang lain."+
                    "Namun dia menghadapi kesulitan yang datang silih berganti dalam perjalannya\n"+"\nJam Tayang: \nSabtu: 14.00, 16.35, 19.10 \nMinggu: 15.00, 18.35, 20.10"
    };

    private static int[] bioskopImage = {
            R.drawable.joker,
            R.drawable.danur,
            R.drawable.dilan,
            R.drawable.now,
            R.drawable.sin,
            R.drawable.hangout,
            R.drawable.op,
            R.drawable.you
    };

    static ArrayList<ModelMovie> getListData() {
        ArrayList<ModelMovie> list = new ArrayList<>();
        for (int possition = 0; possition < bioskopNames.length; possition++) {
            ModelMovie shoes = new ModelMovie();
            shoes.setName(bioskopNames[possition]);
            shoes.setPrice(bioskopPrice[possition]);
            shoes.setTime(bioskopTime[possition]);
            shoes.setDetail(bioskopDetail[possition]);
            shoes.setPhoto(bioskopImage[possition]);

            list.add(shoes);
        }
        return list;
    }
}
