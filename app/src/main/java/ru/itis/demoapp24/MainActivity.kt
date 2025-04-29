package ru.itis.demoapp24

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.androidbroadcast.vbpd.viewBinding
import ru.itis.demo24.navigation.Nav
import ru.itis.demoapp24.app.R
import ru.itis.demoapp24.app.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Nav.Provider {

    @Inject
    lateinit var nav: Nav

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    private val mainContainerId: Int = R.id.main_fragment_container

    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupNavigation()
    }

    private fun setupNavigation() {
        if (navController == null) {
            val navHost = supportFragmentManager.findFragmentById(mainContainerId) as NavHostFragment
            navController = navHost.navController
        }
        nav.setNavProvider(navProvider = this)
    }

    override fun getNavController(): NavController? {
        return navController
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::nav.isInitialized) {
            nav.clearNavProvider(navProvider = this)
        }
    }
}