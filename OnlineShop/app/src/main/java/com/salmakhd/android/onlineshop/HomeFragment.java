package com.salmakhd.android.onlineshop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.salmakhd.android.onlineshop.claases.HomeFragmentViewModelFactory;
import com.salmakhd.android.onlineshop.claases.RetrofitClient;
import com.salmakhd.android.onlineshop.databinding.FragmentHomeBinding;
import com.salmakhd.android.onlineshop.model.CafeItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private CafeItemsAdapter adapter;
    private ArrayList<CafeItem> items = new ArrayList<>();
    private boolean isFirstTimeLoading = true;
    private FragmentHomeBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // add safe arg
        Bundle bundle = new Bundle();
        bundle.putString("First safe argument!", "First!!");
        // navigate
//        binding.getRoot().findNavController().navigate(R.id.action_details_fragment, bundle);
        // get safe arg
//        Bundle receivedBundle = arguments.getString("First safe argument!");
        // create a custom viewmodel factory
        HomeFragmentViewModelFactory factory = new HomeFragmentViewModelFactory();
        HomeFragmentViewModel viewmodel = new ViewModelProvider(this, factory).get(HomeFragmentViewModel.class);
//        viewmodel.helloTextWord.observe((LifecycleOwner) inflater.getContext(), new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                binding.setHelloText(s);
//            }
//        });
        // for two-way data binding
        binding = FragmentHomeBinding.inflate(inflater);
        binding.setLifecycleOwner(this);
        // set view model
        binding.setHomeFragmentViewModel(viewmodel);
        // set observable
        binding.setHelloText("Hello Salma!");
        binding.cafeItemCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getCafeItems(tab.getText().toString(), HomeFragment.this.getLayoutInflater());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //
            }
        });
        getCafeItems("Coffee", HomeFragment.this.getLayoutInflater());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void getCafeItems(String text, LayoutInflater inflater) {
        RetrofitClient.getInstance().getCafeApi()
                .getCafeItems(text)
                .enqueue(new Callback<List<CafeItem>>() {
                    @Override
                    public void onResponse(Call<List<CafeItem>> call, Response<List<CafeItem>> response) {
                        if(response.isSuccessful() && response.body()!=null) {
                            Log.i("OnlineShop", "Items are: " + response.body());
                           items = new ArrayList<>(response.body());
                            if(isFirstTimeLoading) {
                                binding.cafeItemsRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
                                adapter = new CafeItemsAdapter(inflater.getContext(), items);
                                binding.cafeItemsRecyclerView.setAdapter(adapter);
                                isFirstTimeLoading = false;
                            } else {
                                adapter.updateListItems(items);
                            }
                        }
                        else {
                            Log.i("OnlineShop", "Response wasn't successful or body is empty");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CafeItem>> call, Throwable t) {
                        Toast.makeText(HomeFragment.this.getContext(), "Error getting data", Toast.LENGTH_LONG).show();
                    }
                });
    }
}