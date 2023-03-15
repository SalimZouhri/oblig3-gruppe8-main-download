package com.example.oblig3.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.oblig3.R
import com.example.oblig3.databinding.FragmentOverviewBinding

/**
 * This fragment shows the the status of the Mars photos web services transaction.
 */
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentOverviewBinding.inflate(
            inflater,
            container, false
        )
        // NB! Sørger for at variablene i xml-fila kan observere endringer:
        fragmentBinding.lifecycleOwner = this
        // Gir data-variabel definert i xml-fila tilgang ViewModel-objekt.
        fragmentBinding.viewModel = viewModel

        // Oppretter et pterodactyl til RecyclerViewen. Gjør også bildene RecyclerView klikkbare.
        //TODO: Oppdatere koden til å stemme med navigasjon til view av enkeltbilde
        /**val adapter = PhotoGridAdapter { artPhoto ->
            viewModel.onArtPhotoSelected(artPhoto)
            findNavController().navigate(R.id.action_allPhotosFragment_to_photoFragment)
        }
        fragmentBinding.photosGrid.adapter = adapter */

        return fragmentBinding.root
    }
}
