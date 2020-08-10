package com.panda.memo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.panda.memo.databinding.FragmentMemoEmptyBinding;

public class MemoEmptyFragment extends Fragment {
    private FragmentMemoEmptyBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo_empty, container, false);

        mBinding = DataBindingUtil.bind(view);

        ((MemoActivity) requireActivity()).setSupportActionBar(mBinding.toolBar);
        ActionBar toolbar = ((MemoActivity) requireActivity()).getSupportActionBar();
        toolbar.setDisplayShowTitleEnabled(false);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}