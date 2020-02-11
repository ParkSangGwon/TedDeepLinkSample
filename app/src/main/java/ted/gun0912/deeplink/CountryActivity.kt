package ted.gun0912.deeplink

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView

class CountryActivity : BaseActivity(R.layout.activity_country) {

    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.tv_value).text = id
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXTRA_ID, id)
        super.onSaveInstanceState(outState)
    }

    override fun setSavedInstanceState(savedInstanceState: Bundle?) {
        val bundle = getBundle(savedInstanceState)

        bundle?.getString(EXTRA_ID)?.let {
            id = it
        } ?: run {
            // EXTRA_COUNTRY 정보 없음 오류로그 남기기
            // EXTRA_COUNTRY
            finish()
        }
    }


    companion object {
        private const val QUERY_COUNTRY = "id"
        private const val EXTRA_ID = "EXTRA_ID"

        fun startActivity(context: Context, id: String) {
            context.startActivity(getIntent(context, id))
        }

        private fun getIntent(context: Context, id: String) =
            Intent(context, CountryActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
            }

        fun getIntent(context: Context, deepLink: Uri): Intent {
            val id = deepLink.getQueryParameter(QUERY_COUNTRY) ?: run {
                // 딥링크 id 파라미터 없음 오류 로그남기기
                // DeepLink does'nt have id query parameter
                ""
            }
            return getIntent(context, id)
        }
    }
}