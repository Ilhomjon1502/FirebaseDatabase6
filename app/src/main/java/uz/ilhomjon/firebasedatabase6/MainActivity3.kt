package uz.ilhomjon.firebasedatabase6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.ilhomjon.firebasedatabase6.ui.main.MainFragment

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}