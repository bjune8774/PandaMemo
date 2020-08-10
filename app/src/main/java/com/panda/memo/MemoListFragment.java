package com.panda.memo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panda.memo.databinding.FragmentMemoListBinding;

public class MemoListFragment extends Fragment {
    private MemoViewModel mMemoViewModel;
    private FragmentMemoListBinding mBinding;
    private MemoListAdapter mMemoListAdapter;

    public MemoListFragment(MemoViewModel memoViewModel) {
        mMemoViewModel = memoViewModel;
        mMemoListAdapter = new MemoListAdapter(mMemoViewModel.getMemoList());
    }

    public void setOnItemClickListener(MemoListAdapter.OnItemClickListener listener) {
        mMemoListAdapter.setOnItemClickListener(listener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memo_list, container, false);

        mBinding = DataBindingUtil.bind(view);
//        mBinding.setViewModel(mMemoViewModel);
//        mBinding.setLifecycleOwner(requireActivity());

        ((MemoActivity) requireActivity()).setSupportActionBar(mBinding.toolBar);
        ActionBar toolbar = ((MemoActivity) requireActivity()).getSupportActionBar();
        toolbar.setDisplayShowTitleEnabled(false);

        setHasOptionsMenu(true);

        // Set the adapter
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) mBinding.list;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mMemoListAdapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}