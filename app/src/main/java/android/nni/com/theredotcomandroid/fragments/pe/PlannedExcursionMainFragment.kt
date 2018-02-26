package android.nni.com.theredotcomandroid.fragments.pe

import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.adapters.PlannedExcursionAdapter
import android.nni.com.theredotcomandroid.entities.Adventure
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * Created by Marcus Garmon on 2/25/2018.
 */
class PlannedExcursionMainFragment : Fragment() {

    private var peListView: ListView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.pe_fragment_main, container, false)

        peListView = v?.findViewById(R.id.peListView)
        val peList = arguments.getSerializable("plannedExcursions") as ArrayList<Adventure>

        var adapter = PlannedExcursionAdapter(this.activity, peList);
        peListView?.adapter = adapter;

        return v
    }

}