package ted.gun0912.deeplink

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_detail).setOnClickListener {
            DetailActivity.startActivity(this, "Main Ted")
        }

        findViewById<Button>(R.id.btn_country).setOnClickListener {
            CountryActivity.startActivity(this, "Main Korea")
        }
    }


    companion object {

        fun getIntent(context: Context): Intent =
            Intent(context, MainActivity::class.java).apply {
                addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
                            or Intent.FLAG_ACTIVITY_SINGLE_TOP
                            or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
            }
    }
}
