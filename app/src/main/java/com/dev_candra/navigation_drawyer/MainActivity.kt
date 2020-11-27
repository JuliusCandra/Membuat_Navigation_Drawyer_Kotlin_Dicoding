package com.dev_candra.navigation_drawyer

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {


    /*
    AppBarConfiguration berisi kumpulan id yang ada didalam menu Navigaiton Drawer(baris3).
    Jika id yang ada di dalam menu NavigationDrawer ditambahkan di AppBarConfiguration, maka AppBar akan berbentuk Menu NavigationDrawer.
    Jika tidak ditambahkan, maka akan
     */

    /*
    NavigationView menampung semua menu dan sebuah header. Karena itulah jika Anda ingin mengubah komponen view yang terdapat di dalam header sebuah navigation view, maka proses casting/inisialisasi komponen harus dilakukan dengan cara seperti ini:
    Kenapa harus 0? Ini karena indeks header berada pada susunan teratas dari kumpulan list menu yang terdapat pada NavigationView.
     */
    private lateinit var appBarConfiguration: AppBarConfiguration
    internal lateinit var profilCirleImageView : CircleImageView
    internal var profilImage = "https://lh3.googleusercontent.com/-4qy2DfcXBoE/AAAAAAAAAAI/AAAAAAAABi4/rY-jrtntAi4/s640-il/photo.jpg"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener{view ->
            Snackbar.make(view,"Masukkan action anda",Snackbar.LENGTH_SHORT)
                .setAction("Akss"){Toast.makeText(this@MainActivity,"Candra Julius Sinaga",Toast.LENGTH_SHORT).show()}.show()
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        // Menginisiasi sebuah item terhadapa menu
        profilCirleImageView = navView.getHeaderView(0).findViewById(R.id.imageView)
        Glide.with(this)
            .load(profilImage)
            .into(profilCirleImageView)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home,R.id.nav_gallery,R.id.nav_slideshow,
            R.id.nav_tools,R.id.nav_send
        ).setDrawerLayout(drawerLayout)
            .build()


//        appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        val navController = findNavController(R.id.nav_host_fragment)
        /*
        setupActionBarWithNavController digunakan untuk mengatur judul AppBar agar sesuai dengan Fragment yang ditampilkan.
         */
        setupActionBarWithNavController(navController,appBarConfiguration)
        /*
        setupWithNavController digunakan supaya NavigationDrawer menampilkan Fragment yang sesuai ketika menu dipilih.
         */
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /*
    Sedangkan kode di atas digunakan untuk mengatur ketika tombol back ditekan. Misalnya ketika Anda di halaman CartFragment, jika Anda tekan tombol back, maka aplikasi tidak langsung keluar, melainkan akan menuju ke startDestination yang ada di Navigation Graph, yaitu HomeFragment.
     */

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}