package org.linphone.ruan.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.linphone.R

class ContactsFragment : Fragment() {

    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactsViewModel =
            ViewModelProvider(this).get(ContactsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
        contactsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })

        recyclerView = root.findViewById(R.id.recyclerview_contact)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactsViewModel.contactListLiveData.observe(viewLifecycleOwner, {
            contactsAdapter = ContactsAdapter(it)
            recyclerView.adapter = contactsAdapter
        })
    }
}
