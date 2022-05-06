package uz.ilhomjon.firebasedatabase6.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.ilhomjon.firebasedatabase6.databinding.ItemRvBinding
import uz.ilhomjon.firebasedatabase6.modles.User

class UserAdapter(val list: List<User>) : RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: User) {
            Glide.with(itemRv.root).load(user.imageLink).into(itemRv.imageItem)
            itemRv.tvItem.text = user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}