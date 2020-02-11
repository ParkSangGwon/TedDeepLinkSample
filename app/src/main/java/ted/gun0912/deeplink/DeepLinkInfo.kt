package ted.gun0912.deeplink

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.StringRes

enum class DeepLinkInfo(@StringRes val hostStringResId: Int) {

    MAIN(R.string.scheme_host_main) {
        override fun getIntent(context: Context, deepLinkUri: Uri) =
            getMainIntent(context)
    },

    DETAIL(R.string.scheme_host_detail) {
        override fun getIntent(context: Context, deepLinkUri: Uri) =
            DetailActivity.getIntent(context, deepLinkUri)
    },

    COUNTRY(R.string.scheme_host_country) {
        override fun getIntent(context: Context, deepLinkUri: Uri) =
            CountryActivity.getIntent(context, deepLinkUri)
    };

    private val host: String = TedApplication.instance.getString(hostStringResId)

    abstract fun getIntent(context: Context, deepLinkUri: Uri): Intent

    companion object {

        fun getMainIntent(context: Context) = MainActivity.getIntent(context)

        operator fun invoke(uri: Uri): DeepLinkInfo =
            values().find { it.host == uri.host } ?: run {
                logd("등록되지 않은 딥링크")
                logd("Not registered deep link host")
                MAIN
            }
    }
}