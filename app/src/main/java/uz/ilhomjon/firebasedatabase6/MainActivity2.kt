package uz.ilhomjon.firebasedatabase6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.ilhomjon.firebasedatabase6.databinding.ActivityMain2Binding
import java.lang.ArithmeticException

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnError.setOnClickListener {
            //click
            throw ArithmeticException("Android 6 so'zini ozgartirdim")
        }
    }
}