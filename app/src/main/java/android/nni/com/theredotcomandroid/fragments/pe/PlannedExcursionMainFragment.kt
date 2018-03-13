package android.nni.com.theredotcomandroid.fragments.pe

import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.adapters.PlannedExcursionAdapter
import android.nni.com.theredotcomandroid.entities.Adventure
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

/**
* Created by Marcus Garmon on 2/25/2018.
*/
class PlannedExcursionMainFragment : Fragment(), OnItemClickListener {

    private var peList: ArrayList<Adventure>? = null
    private var peListView: ListView? = null

    private lateinit var mCallback: PlannedExcursionMainFragment.onListItemClicked

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.pe_fragment_main, container, false)

        peListView = v?.findViewById(R.id.peListView)
        peList = ArrayList()
        peList = arguments.getSerializable("plannedExcursions") as ArrayList<Adventure>

        var adapter = PlannedExcursionAdapter(this.activity, peList!!);
        peListView?.adapter = adapter;

        return v
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as PlannedExcursionMainFragment.onListItemClicked
        } catch (e: ClassCastException){
            throw ClassCastException(context.toString() + " must implement OnPEClicked")
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this.activity, "Clicked on " + peList!![position].name, Toast.LENGTH_SHORT).show()
        onPlannedExcursionClicked(id)
    }

    interface onListItemClicked {
        fun onListItemClicked(id: Long)
    }

    private fun onPlannedExcursionClicked(id: Long){
        mCallback.onListItemClicked(id)

    }
}