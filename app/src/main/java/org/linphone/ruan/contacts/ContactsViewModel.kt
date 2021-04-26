package org.linphone.ruan.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.linphone.ruan.database.ContactRepository
import org.linphone.ruan.database.SIPAccount

class ContactsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "联系人"
    }
    val text: LiveData<String> = _text

    private val repository = ContactRepository.get()

    val contactListLiveData = repository.getContacts()

    fun saveSIPContact(sipAccount: SIPAccount) {
        repository.updateSIPContact(sipAccount)
    }
}
