package org.linphone.ruan.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.linphone.R

class ContactsAdapter() :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val contactName: TextView = itemView.findViewById(R.id.contact_name)

        fun bind() {
            contactName.text = "Simon"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_list_item, parent, false)
        return ContactsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 50
    }
}
