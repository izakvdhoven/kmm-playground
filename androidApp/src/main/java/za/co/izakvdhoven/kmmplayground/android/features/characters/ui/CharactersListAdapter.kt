package za.co.izakvdhoven.kmmplayground.android.features.characters.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import za.co.izakvdhoven.kmmplayground.android.databinding.ItemCharacterBinding

class CharactersListAdapter(
    private var characters: List<CharacterViewData> = listOf(),
    private val onClick: (CharacterViewData) -> Unit
) : RecyclerView.Adapter<CharactersListAdapter.CharacterViewHolder>() {

    fun update(drivers: List<CharacterViewData>) {
        this.characters = drivers
        notifyDataSetChanged()
    }

    override fun getItemCount() = characters.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(characters[position], onClick)

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterViewData, onClick: (CharacterViewData) -> Unit) {
            with(binding) {
                //TODO - Load image
                name.text = character.name
                status.text = character.status
                binding.root.setOnClickListener { onClick(character) }
            }
        }
    }
}
