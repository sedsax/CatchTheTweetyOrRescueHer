package com.sedsax.mykotlinproject
/*
* Atıl Samancıoğlu' nun "Android Mobil Uygulama Kursu: Kotlin & Java" Udemy Kursundan faydalanılarak yazılmıştır.
* Kendi algoritmam ve tasarımım olmadığı için belirtmek istedim
* Kurs sonunda kafamdaki fikirleri ekleyebilecek seviyeye geldiğimde geliştirmesini yapacağım
* Güncel Kotlin söz dizimine uygun yazdığım ve aralarda kendi notlarım bulunduğu için Githubımda dursun istedim.
* */
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    //val textView = findViewById<TextView>(R.id.scoreText) as TextView
   // var scoreText = TextView(this)
    var score = 0
    //imageViewların tutulduğu diziyi birkaç yerde çapıracağım için burada tanımlıyorum
    var imageArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper()) // neden Handler() şeklinde yazamadığımızı anlamadım Looper ne?
    var runnable = Runnable{ }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // imageView.visibility = View.INVISIBLE
        // Peki yukarıdaki gibi 9 görseli de görünmez yapmak için tek tek yazacak mıyız, hayır
        // Görsellerimizi yani ImageViewlarımızı array içine koyabiliriz

        //ImageArray
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        //şimdi bu görsellerin hepsini görünmez yapan bir fonksiyon yazalım
        hideImages()


        object : CountDownTimer(15000,1000){
            override fun onTick(p0: Long) {
                timeText.text = "Time: " + p0/1000

            }

            override fun onFinish() {
               timeText.text = "Time: 0"

                handler.removeCallbacks(runnable)
                for(image in imageArray) {
                    image.visibility = View.INVISIBLE
                }

                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Restart The Game?")
                alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                }

                alert.show()
            }

        }.start()
    }

    fun hideImages() {

        runnable = object :Runnable {
            override fun run() {
                for(image in imageArray) {
                    image.visibility = View.INVISIBLE
                }
                val random = Random
                val randomIndex = random.nextInt(0,9)
                //var randomIndex = Random().nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }

        }
        handler.post(runnable)
    }

    fun increaseScore(view : View) {
        score++
        scoreText.text = "Score: $score"
      //  scoreText.text = "Score: $score"
    }
}