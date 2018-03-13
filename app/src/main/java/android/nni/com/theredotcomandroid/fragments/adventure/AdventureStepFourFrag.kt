package android.nni.com.theredotcomandroid.fragments.adventure

import android.app.Fragment
import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.RadioButton



/**
 * Created by Marcus Garmon on 3/12/2018.
 */
class AdventureStepFourFrag : Fragment(), View.OnClickListener {

    private var listView: ListView? = null

    private lateinit var mCallback: StepFourListener

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater?.inflate(R.layout.adventure_fragment_step_four, container, false)

        return v
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as StepFourListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement StepFourListener")
        }
    }



    interface StepFourListener {
        fun onNextClicked()
    }

    private fun onNextClicked(){
        mCallback.onNextClicked()
    }
}