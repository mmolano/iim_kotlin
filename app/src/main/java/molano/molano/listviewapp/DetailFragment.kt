package molano.molano.listviewapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment(val game: Data) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val pref = this.activity!!.getSharedPreferences("navigator", Context.MODE_PRIVATE);
        val value = pref.getBoolean("value",false)

        pref.edit().putBoolean("value",!value).apply()
        description.text = game.description
        detail_title.text = game.name
        Picasso.get().load(this.game.img).into(image)


        super.onViewCreated(view, savedInstanceState)
    }
}
