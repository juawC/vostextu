package com.app.juawcevada.vostextu.ui.postdetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.juawcevada.vostextu.databinding.PostDetailBinding
import com.app.juawcevada.vostextu.databinding.PostsListFragmentBinding
import com.app.juawcevada.vostextu.shared.extensions.setUpSnackbar
import com.app.juawcevada.vostextu.shared.extensions.viewModelProvider
import com.app.juawcevada.vostextu.ui.shared.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PostDetailFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<PostDetailViewModel>

    private lateinit var viewModel: PostDetailViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return PostDetailBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
            it.refresh.isEnabled = false

            setUpSnackbar(viewModel.errorMessage, it.root)
        }.root
    }

}