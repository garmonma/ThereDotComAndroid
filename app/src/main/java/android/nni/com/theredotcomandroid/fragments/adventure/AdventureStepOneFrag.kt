package android.nni.com.theredotcomandroid.fragments.adventure

import android.app.Fragment
import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.adapters.EventSuggestionAdapter
import android.nni.com.theredotcomandroid.entities.Event
import android.nni.com.theredotcomandroid.entities.EventType
import android.nni.com.theredotcomandroid.services.EventService
import android.nni.com.theredotcomandroid.services.callbacks.JSONArrayServerCallback
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.google.gson.Gson
import org.json.JSONArray


/**
* Created by Marcus Garmon on 3/10/2018.
*/
class AdventureStepOneFrag : Fragment(), View.OnClickListener{
    private val TAG = "StepOne-Fragment"

    private var spinner: Spinner? = null
    private var listView: ListView? = null
    private var otherSubmissionView: EditText? = null
    private var stepOneNextButton: Button? = null

    private var eventService: EventService? = null
    private var eventTypeList: ArrayList<EventType>? = null
    private var eventList: ArrayList<Event>? = null
    private var eventType: String? = null
    private var chosenEvent: Event? = null
    private val gson = Gson()

    private lateinit var mCallback: AdventureStepOneFrag.StepOneListener

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater?.inflate(R.layout.adventure_fragment_step_one, container, false)

        eventService = EventService(this.activity)
        eventTypeList = eventService?.getEventTypeList()

        spinner = v?.findViewById(R.id.eventTypeSpinner)
        listView = v?.findViewById(R.id.eventSuggestionList)
        otherSubmissionView = v?.findViewById(R.id.eventOtherSubmission)
        stepOneNextButton = v?.findViewById(R.id.stepOneNextButton)

        val spinnerAdapter = ArrayAdapter<EventType>(this.context, android.R.layout.simple_spinner_dropdown_item, eventTypeList)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = spinnerAdapter

        initViewListeners()

        return v
    }

    private fun initViewListeners(){
        spinner?.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                eventType = eventTypeList?.get(position)?.name as String

                if(eventType.equals("other", true)){
                    otherSubmissionView?.visibility = EditText.VISIBLE
                    return
                }

                eventService?.getEventSuggestions(eventType!!, object : JSONArrayServerCallback {
                    override fun onSuccess(results: JSONArray) {
                        Log.i(TAG, results.toString())
                        (0 until results.length())
                                .map { results.getJSONObject(it) }
                                .forEach { eventList!!.add(gson.fromJson<Event>(it.toString(), Event::class.java)) }
                    }
                } )

                if(eventList != null && eventList?.size!! > 0) {
                    listView?.visibility = ListView.VISIBLE
                    val listViewAdapter = EventSuggestionAdapter(activity, eventList!!)
                    listView?.adapter = listViewAdapter
                }

                otherSubmissionView?.visibility = EditText.GONE
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                eventList = null
                listView?.visibility = ListView.GONE
                otherSubmissionView?.visibility = EditText.GONE
            }
        }

        listView?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            chosenEvent = eventList?.get(position)
        }

        stepOneNextButton?.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.stepOneNextButton){
            onNextClicked()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            mCallback = context as StepOneListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement StepOneListener")
        }
    }

    interface StepOneListener {
        fun onNextClicked(event: Event?, eventType: String, otherSubmission: String?)
    }

    private fun onNextClicked(){
        mCallback.onNextClicked(chosenEvent, this!!.eventType!!, otherSubmissionView?.text.toString())
    }
}