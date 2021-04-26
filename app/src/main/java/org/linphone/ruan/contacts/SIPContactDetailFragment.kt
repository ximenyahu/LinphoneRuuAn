package org.linphone.ruan.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.linphone.R

class SIPContactDetailFragment : Fragment() {

    companion object {
        fun newInstance() = SIPContactDetailFragment()
    }

    private lateinit var viewModel: SIPContactDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sipcontact_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SIPContactDetailViewModel::class.java)
    }
}
