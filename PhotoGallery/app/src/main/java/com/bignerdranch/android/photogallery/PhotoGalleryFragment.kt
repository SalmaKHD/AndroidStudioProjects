package com.bignerdranch.android.photogallery

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.bignerdranch.android.photogallery.databinding.FragmentPhotoGalleryBinding
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val TAG = "PhotoGalleryFragment"
private const val POLL_WORK = "POLL_WORK"

class PhotoGalleryFragment : Fragment() {
    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null."
        }

    // obtain fragment's ViewModel instance
    private val photoGalleryViewModel: PhotoGalleryViewModel by viewModels()

    // get a reference to the SearchView object
    private var searchView: SearchView? = null
    private var pollingMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // let the fragment know that it should have an options menu
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // inflate the menu item to see it in the action bar
        inflater.inflate(R.menu.fragment_photo_gallery, menu)

        val searchItem: MenuItem = menu.findItem(R.id.menu_item_search)
        // get access to SearchView methods (originally of type MenuItem)
        searchView = searchItem.actionView as? SearchView

        pollingMenuItem = menu.findItem(R.id.menu_item_toggle_polling)

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // set a listener for when the user submits their string
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "QueryTextSubmit: $query")
                photoGalleryViewModel.setQuery(query ?: "")
                return true
            }
            // set a listener for when the letters in the search bar change
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "QueryTextChange: $newText")
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            // clear the query in the SearchView when the user chooses clear
            R.id.menu_item_clear -> {
                photoGalleryViewModel.setQuery("")
                true
            }
            R.id.menu_item_toggle_polling -> {
                photoGalleryViewModel.toggleIsPolling()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        // set the SearchView object to null
        searchView = null
        pollingMenuItem = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate the layout
        _binding = FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        // set the layout manager for the recycler view
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // use uiState property of ViewModel to access the data the fragment needs
                // upon the data being changed
                photoGalleryViewModel.uiState.collect { state ->
                    binding.photoGrid.adapter = PhotoListAdapter(
                        state.images
                    ) { photoPageUri -> // this lambda expression will be used for setting a listener for the ViewHolder
                        // open a web browser and show the selected image
                        findNavController().navigate(
                            PhotoGalleryFragmentDirections.showPhoto(
                                photoPageUri
                            )
                        )

                    }

                    searchView?.setQuery(state.query, false)
                        // update polling state when the UI state changes
                    updatePollingState(state.isPolling)
                }
            }
        }
    }

    private fun updatePollingState(isPolling: Boolean) {
        // change menu item string when the state of polling changes
        val toggleItemTitle = if (isPolling) {
            R.string.stop_polling
        } else {
            R.string.start_polling
        }
        pollingMenuItem?.setTitle(toggleItemTitle)

        if (isPolling) {
            // set constraints for the worker (the work will not run if these
            // constraints are not satisfied
            val constraints = Constraints.Builder()
                    // enforce network type
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build()

            val periodicRequest =
                // create a periodic work request
                // 15 -> min value for interval
                PeriodicWorkRequestBuilder<PollWorker>(15, TimeUnit.MINUTES)
                        // apply the constraints
                    .setConstraints(constraints)
                    .build()

            // get WorkManager instance and register the periodic work with it
            WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
                // this string allows the work to be uniquely identified ->
                // can be used later on to cancel the request
                POLL_WORK,
                // this determines what the WorkManger must do if you schedule a new worker
                // with the same name
                ExistingPeriodicWorkPolicy.KEEP,
                periodicRequest
            )
        } else {
            // if polling is set to off, and the worker is running -> cancel it using the worker's
            // unique id
            WorkManager.getInstance(requireContext()).cancelUniqueWork(POLL_WORK)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}