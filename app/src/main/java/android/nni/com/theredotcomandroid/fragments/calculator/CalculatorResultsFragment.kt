package android.nni.com.theredotcomandroid.fragments.calculator

import android.nni.com.theredotcomandroid.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Marcus Garmon on 2/11/2018.
 */
class CalculatorResultsFragment: Fragment(), View.OnClickListener  {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.calculator_fragment_init, container, false)

        return v
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}