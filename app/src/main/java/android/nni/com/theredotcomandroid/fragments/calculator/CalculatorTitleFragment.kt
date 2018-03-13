package android.nni.com.theredotcomandroid.fragments.calculator

import android.support.v4.app.Fragment
import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button

/**
* Created by Marcus Garmon on 2/6/2018.
*/
class CalculatorTitleFragment : Fragment(), OnClickListener {

    private lateinit var mCallback: OnOptionClicked;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater?.inflate(R.layout.calculator_fragment_title, container, false);

        var buttonNewAdventure = v?.findViewById<Button>(R.id.buttonNewAdventure)
        buttonNewAdventure?.setOnClickListener(this)
        var buttonPlannedExcursion = v?.findViewById<Button>(R.id.buttonPlannedExcursion)
        buttonPlannedExcursion?.setOnClickListener(this)
        var buttonSignIn = v?.findViewById<Button>(R.id.buttonSignIn)
        buttonSignIn?.setOnClickListener(this)

        return v;
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.buttonNewAdventure) {
            onNewAdventureClicked()
        }

        if(v?.id == R.id.buttonPlannedExcursion){
            onPlannedExcursionClicked()
        }

        if(v?.id == R.id.buttonSignIn){
            onSignInClicked()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context);

        try {
            mCallback = context as OnOptionClicked;
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnContinueButtonClicked")
        }
    }

    interface OnOptionClicked {
        fun onOptionClicked(option: String)
    }


    private fun onPlannedExcursionClicked() {
        mCallback.onOptionClicked("PlannedExcursion")
    }

    private fun onNewAdventureClicked() {
        mCallback.onOptionClicked("NewAdventure")
    }

    private fun onSignInClicked(){
        mCallback.onOptionClicked("SignIn")
    }
}