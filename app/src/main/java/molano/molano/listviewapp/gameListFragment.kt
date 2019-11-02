package molano.molano.listviewapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_item_list.*

import molano.molano.listviewapp.dummy.DummyContent
import molano.molano.listviewapp.dummy.DummyContent.DummyItem

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [gameListFragment.OnListFragmentInteractionListener] interface.
 */
class gameListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView_main.layoutManager = LinearLayoutManager(this.activity)
        val queue = Volley.newRequestQueue(this.activity)
        val stringRequest = StringRequest(
            Request.Method.GET, "https://my-json-server.typicode.com/bgdom/cours-android/games",
            Response.Listener<String> { response ->
                val games = Gson().fromJson(response, Array<Data>::class.java);
                val myObj = object : dataArray {
                    override val games: Array<Data> = games
                    override fun open(game: Data) {
                        this@gameListFragment
                            .fragmentManager!!
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.frame,DetailFragment(game))
                            .commit()
                    }
                }
                recyclerView_main.adapter = MainAdapter(myObj)
            },
            Response.ErrorListener {error-> Log.e("test", error.localizedMessage)
            })
        queue.add(stringRequest)
        super.onViewCreated(view, savedInstanceState)
    }
    companion object{

    }
}
