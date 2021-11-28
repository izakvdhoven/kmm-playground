package za.co.izakvdhoven.kmmplayground.android.features.characters.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import za.co.izakvdhoven.kmmplayground.android.databinding.ActivityCharactersBinding
import za.co.izakvdhoven.kmmplayground.android.extensions.show
import za.co.izakvdhoven.kmmplayground.android.features.characters.CharactersViewData
import za.co.izakvdhoven.kmmplayground.android.features.characters.CharactersViewModel

class CharactersActivity: AppCompatActivity() {
    private val viewModel: CharactersViewModel by viewModel()
    private lateinit var binding: ActivityCharactersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCharactersBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setUpList()
        observeViewData()
        fetchCharacters()

        binding.swipeRefresh.setOnRefreshListener {
            fetchCharacters()
        }
    }

    private fun setUpList() {
        binding.list.adapter = CharactersListAdapter {
            Log.d("CharactersActivity", "Clicked: ${it.name}")
        }
    }

    private fun observeViewData() {
        viewModel.viewData.observe(this) { viewData ->
            binding.swipeRefresh.isRefreshing = viewData.isBusy

            when (viewData) {
                is CharactersViewData.Error -> showSnackBar(viewData.message)
                is CharactersViewData.Success -> {
                    (binding.list.adapter as? CharactersListAdapter)?.update(viewData.characters)
                    binding.list.show()
                }
            }
        }
    }

    private fun fetchCharacters() {
        viewModel.fetchCharacters()
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}