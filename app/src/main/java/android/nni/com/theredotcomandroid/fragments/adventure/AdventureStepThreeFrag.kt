package android.nni.com.theredotcomandroid.fragments.adventure

import android.app.Fragment
import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.dtos.AddressDto
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.adventure_fragment_step_three.view.*

/**
* Created by Marcus Garmon on 3/12/2018.
*/
class AdventureStepThreeFrag : Fragment(), View.OnClickListener {

    private val TAG = "StepThree-Fragment"

    private var buttonYes : Button? = null
    private var buttonNo : Button? = null

    private var textCountry : EditText? = null
    private var textCity : EditText? = null
    private var textState : EditText? = null
    private var textZipCode : EditText? = null

    private var buttonNext : Button? = null

    private var addressDto : AddressDto? = null

    private lateinit var mCallback: StepThreeListener

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater?.inflate(R.layout.adventure_fragment_step_three, container, false)

        buttonYes = v?.findViewById(R.id.adventureLocationYes)
        buttonNo = v?.findViewById(R.id.adventureLocationNo)
        buttonNext = v?.findViewById(R.id.stepThreeNextButton)

        buttonYes?.setOnClickListener(this)
        buttonNo?.setOnClickListener(this)
        buttonNext?.setOnClickListener(this)

        textCountry = v?.findViewById(R.id.adventureCountry)
        textCountry?.onFocusChangeListener = object : View.OnFocusChangeListener{
            override fun onFocusChange(v: View?, hasFocus: Boolean) {
                if(!hasFocus){
                    if((v as EditText)?.text.toString().equals("united states", true)){
                        textState?.visibility = EditText.VISIBLE
                        textZipCode?.visibility = EditText.VISIBLE
                    }
                }
            }

        }
        textCity = v?.findViewById(R.id.adventureCity)
        textState = v?.findViewById(R.id.adventureState)
        textZipCode = v?.findViewById(R.id.adventureZipCode)

        addressDto = AddressDto()

        return v
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.adventureLocationYes){
            textCountry?.visibility = EditText.VISIBLE
            textCity?.visibility = EditText.VISIBLE
        }

        if(v?.id == R.id.adventureLocationNo){
            textCountry?.visibility = EditText.GONE
            textCity?.visibility = EditText.GONE
            textState?.visibility = EditText.GONE
            textZipCode?.visibility = EditText.GONE
        }

        if(v?.id == R.id.stepThreeNextButton){
            onNextClicked()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as StepThreeListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement StepOneListener")
        }
    }

    interface StepThreeListener {
        fun onNextClicked(address: AddressDto)
    }

    private fun onNextClicked(){
        addressDto?.city = textCity?.text.toString()
        addressDto?.country = textCountry?.text.toString()
        addressDto?.state = textState?.text.toString()
        addressDto?.zipcode = textZipCode?.text.toString()

        mCallback.onNextClicked(this!!.addressDto!!)
    }
}