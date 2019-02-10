package com.app.juawcevada.vostextu.ui.posts

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.juawcevada.vostextu.databinding.PostsListFragmentBinding
import com.app.juawcevada.vostextu.shared.EventObserver
import com.app.juawcevada.vostextu.shared.extensions.viewModelProvider
import com.app.juawcevada.vostextu.testing.OpenClassOnDebug
import com.app.juawcevada.vostextu.ui.shared.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import androidx.recyclerview.widget.DividerItemDecoration
import com.app.juawcevada.vostextu.R


@OpenClassOnDebug
class PostsFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<PostsViewModel>

    private lateinit var viewModel: PostsViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelProvider(viewModelFactory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return PostsListFragmentBinding.inflate(inflater, container, false).also {
            it.setLifecycleOwner(this)
            it.viewModel = viewModel
            it.viewActions = viewModel
            it.list.apply {
                adapter = PostsListAdapter(viewModel::openDetail)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    ).apply {
                        setDrawable(ContextCompat.getDrawable(context, R.drawable.ic_post_item_divider)!!)
                    }
                )
            }

            viewModel.navigationAction.observe(viewLifecycleOwner, EventObserver { event ->

                when (event) {
                    is PostsNavigationActions.OpenDetail -> {
                        val action
                                = PostsFragmentDirections
                            .actionPostsFragmentToPostDetailFragment(event.item)
                        navController().navigate(action)
                    }
                }
            })
        }.root
    }


    /**
     * Created to be able to override in tests
     */
    @VisibleForTesting
    fun navController() = findNavController()

}