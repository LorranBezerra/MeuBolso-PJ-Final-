package com.example.meuapp.ui



import android.os.Binder
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.meuapp.R
import com.example.meuapp.R.id.login
import com.example.meuapp.R.id.loginFragment
import com.example.meuapp.R.id.main_graph
import com.example.meuapp.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(/* view = */ binding.root)
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}
