package ted.gun0912.deeplink

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView

class DetailActivity : BaseActivity(R.layout.activity_detail) {

    private lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.tv_value).text = name
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXTRA_NAME, name)
        super.onSaveInstanceState(outState)
    }

    override fun setSavedInstanceState(savedInstanceState: Bundle?) {
        val bundle = getBundle(savedInstanceState)

        bundle?.getString(EXTRA_NAME)?.let {
            name = it
        } ?: run {
            // EXTRA_NAME 정보 없음 오류로그 남기기
            // EXTRA_NAME
            finish()
        }
    }


    companion object {
        private const val QUERY_NAME = "name"
        private const val EXTRA_NAME = "EXTRA_NAME"

        fun startActivity(context: Context, name: String) {
            context.startActivity(getIntent(context, name))
        }

        private fun getIntent(context: Context, name: String) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
            }

        fun getIntent(context: Context, deepLink: Uri): Intent {
            val name = deepLink.getQueryParameter(QUERY_NAME) ?: run {
                // 딥링크 name 파라미터 없음 오류 로그남기기
                // DeepLink does'nt have name query parameter
                ""
            }
            return getIntent(context, name)
        }
    }
}