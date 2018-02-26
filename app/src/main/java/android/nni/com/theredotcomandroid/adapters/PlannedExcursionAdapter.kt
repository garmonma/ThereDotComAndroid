package android.nni.com.theredotcomandroid.adapters

import android.app.Activity
import android.content.Context
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.entities.Adventure
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
/**
* Created by Marcus Garmon on 2/25/2018.
*/
class PlannedExcursionAdapter(private var context: Activity, private var items: ArrayList<Adventure>)
    : BaseAdapter(){
    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return items[position].id!!
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View?
        val viewHolder: ViewHolder

        if(convertView == null){
            val inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.pe_list_item, null)

            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }


        val adventure = items[position]

        viewHolder.adventureName?.text = adventure.name
        viewHolder.location?.text = adventure.event?.address?.city + ", " + adventure.event?.address?.state
        viewHolder.event?.text = adventure.event?.name
        viewHolder.event?.text = adventure.date?.toString()
        viewHolder.event?.text = adventure.getTotalCost().toString()

        return view as View
    }

    private class ViewHolder(row: View?) {
        var adventureName: TextView? = null
        var location: TextView? = null
        var event: TextView? = null
        var date: TextView? = null
        var cost: TextView? = null

        init {
            adventureName = row?.findViewById(R.id.peListName)
            location = row?.findViewById(R.id.peListLocation)
            cost = row?.findViewById(R.id.peListCost)
            date = row?.findViewById(R.id.peListDate)
            event = row?.findViewById(R.id.peListEvent)
        }
    }
}