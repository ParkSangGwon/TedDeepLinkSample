package ted.gun0912.deeplink

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        setSavedInstanceState(savedInstanceState)
    }

    protected fun getBundle(savedInstanceState: Bundle?): Bundle? =
        savedInstanceState ?: intent.extras


    protected open fun setSavedInstanceState(savedInstanceState: Bundle?) {
        // no-op
    }

}