package com.panda.memo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panda.memo.databinding.FragmentMemoContentBinding;

public class MemoListFragment extends Fragment {
    private MemoViewModel mMemoViewModel;
    private FragmentMemoContentBinding mBinding;
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

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(mMemoListAdapter);
        }
        return view;
    }
}