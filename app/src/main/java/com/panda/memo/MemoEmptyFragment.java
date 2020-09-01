package com.panda.memo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.panda.memo.databinding.FragmentMemoEmptyBinding;

public class MemoEmptyFragment extends Fragment {
    private FragmentMemoEmptyBinding mBinding;

    public MemoEmptyFragment() {
        mBinding = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo_empty, container, false);

        mBinding = DataBindingUtil.bind(view);

        return view;
    }
}