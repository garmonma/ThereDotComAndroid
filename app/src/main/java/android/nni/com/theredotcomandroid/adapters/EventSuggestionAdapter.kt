package android.nni.com.theredotcomandroid.adapters

import android.app.Activity
import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.entities.Event
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Created by Marcus Garmon on 3/11/2018.
 */
class EventSuggestionAdapter(private var context: Activity, private var items: ArrayList<Event>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder

        if(convertView == null){
            val inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.adventure_event_suggestion_list_item, null)

            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }


        val event = items[position]

        return view as View
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return items[position].id!!
    }

    override fun getCount(): Int {
        return items.size
    }

    private class ViewHolder(row: View?) {

        init {
        }
    }
}