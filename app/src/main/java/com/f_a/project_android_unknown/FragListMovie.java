package com.f_a.project_android_unknown;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.f_a.project_android_unknown.adapter.AdapterMovie;
import com.f_a.project_android_unknown.model.ModelMovie;
import com.f_a.project_android_unknown.session_manager.SessionManagerAccount;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class FragListMovie extends Fragment {

    private AdapterMovie AM;
    private ArrayList<ModelMovie> ALMM;
    SessionManagerAccount SessionMA;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_frag_list_movie, container, false);

        setHasOptionsMenu(true);
        SessionMA = new SessionManagerAccount(getActivity().getApplicationContext());
        ALMM = new ArrayList<>();
        ALMM.addAll(DataMovie.getListData());

        RecyclerView RV = RootView.findViewById(R.id.rv_bioskop);

        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RV.setLayoutManager(layoutManager);
        RV.setHasFixedSize(true);

        //Membuat Underline pada Setiap Item Didalam List
        RV.setItemAnimator(new DefaultItemAnimator());
        AM = new AdapterMovie(ALMM);

        AM.setOnItemClickCallback(new AdapterMovie.OnItemClickCallback() {
            @Override
            public void onItemClicked(ModelMovie data) {
                Intent detail = new Intent(getActivity(), DetailMovie.class);
                detail.putExtra(DetailMovie.EXTRA_NAME, data.getName());
                detail.putExtra(DetailMovie.EXTRA_PRICE, data.getPrice());
                detail.putExtra(DetailMovie.EXTRA_DETAIL, data.getDetail());
                detail.putExtra(DetailMovie.EXTRA_PHOTO, data.getPhoto());

                startActivity(detail);
            }
        });

        //Memasang Adapter pada RecyclerView
        RV.setAdapter(AM);

//        showRcyclerList();

        return RootView;
    }


    //Code Program pada Method dibawah ini akan Berjalan saat Option Menu Dibuat
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_dashboard, menu);

        MenuItem searchItem = menu.findItem(R.id.ISearch);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AM.getFilter().filter(newText);
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.IAccount:
                Intent intent = new Intent(getActivity(), Account.class);
                startActivity(intent);
                break;
            case R.id.ILogout:
                SpannableStringBuilder snackbarText = new SpannableStringBuilder();
                snackbarText.append("Do You Want to ");
                int Span1 = snackbarText.length();
                snackbarText.append("Logout.");
                snackbarText.setSpan(new ForegroundColorSpan(Color.RED), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                snackbarText.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), Span1, snackbarText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                Snackbar snackbar = Snackbar
                        .make(getActivity().findViewById(android.R.id.content), snackbarText, Snackbar.LENGTH_LONG)
                        .setDuration(2000);
                snackbar.setAction("Logout", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                        SessionMA.clearSeassion();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                snackbar.show();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

}
