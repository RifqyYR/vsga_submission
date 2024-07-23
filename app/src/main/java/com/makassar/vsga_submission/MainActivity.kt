package com.makassar.vsga_submission

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.WindowManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.makassar.vsga_submission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var textViews: Array<TextView>
    private var blankIndex: Int = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textViews = arrayOf(
            binding.tv0, binding.tv1, binding.tv2, binding.tv3,
            binding.tv4, binding.tv5, binding.tv6, binding.tv7,
            binding.tv8, binding.tv9, binding.tv10, binding.tv11,
            binding.tv12, binding.tv13, binding.tv14, binding.tv15
        )
        setStatusBarColor(R.color.blue)
        setSupportActionBar(binding.toolbar)
        randomizeText(textViews)
    }

    private fun randomizeText(puzzles: Array<TextView>) {
        val possibleTexts = ('A'..'O').toList().shuffled() + ""
        for (i in puzzles.indices) {
            puzzles[i].text = possibleTexts[i].toString()
            if (possibleTexts[i].toString().isEmpty()) {
                blankIndex = i
            }
        }
    }

    private fun setStatusBarColor(color: Int) {
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = getColor(color)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reset -> {
                randomizeText(textViews)
                true
            }

            R.id.action_exit -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}