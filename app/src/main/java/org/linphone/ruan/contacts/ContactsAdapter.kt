package org.linphone.ruan.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.linphone.R
import org.linphone.ruan.database.SIPAccount

class ContactsAdapter(var SIPAccountList: List<SIPAccount>) :
    RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    class ContactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val contactName: TextView = itemView.findViewById(R.id.contact_name)

        fun bind(SIPAccount: SIPAccount) {
            contactName.text = SIPAccount.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.contacts_list_item, parent, false)
        return ContactsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        var account = SIPAccountList[position]
        holder.bind(account)
    }

    override fun getItemCount(): Int {
        return SIPAccountList.size
    }
}
