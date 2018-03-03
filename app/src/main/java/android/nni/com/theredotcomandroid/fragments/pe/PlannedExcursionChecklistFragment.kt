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
import android.widget.Button
import android.widget.ScrollView

/**
* Created by Marcus Garmon on 2/26/2018.
*/
class PlannedExcursionChecklistFragment: Fragment() {

    private var adventure = Adventure()
    private lateinit var mCallback: PlannedExcursionChecklistFragment.TodoFragmentListener

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.pe_fragment_checklist, container, false)

        adventure = this.arguments.getSerializable("adventure") as Adventure

        return v
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as PlannedExcursionChecklistFragment.TodoFragmentListener
        } catch (e: ClassCastException){
            throw ClassCastException(context.toString() + " must implement TodoFragmentListener")
        }
    }

    interface TodoFragmentListener {

    }

    private fun createView(){
        if(adventure != null){

            if(adventure.flightCost > 0){
            }
        }
    }



}