package com.panda.memo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.panda.memo.databinding.FragmentMemoContentBinding;

public class MemoContentFragment extends Fragment {
    private MemoViewModel mMemoViewModel;
    private FragmentMemoContentBinding mBinding;
    private int mPos;

    public MemoContentFragment(MemoViewModel memoViewModel) {
        mMemoViewModel = memoViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo_content, container, false);

        mBinding = DataBindingUtil.bind(view);
        mBinding.setViewModel(mMemoViewModel);
        mBinding.setLifecycleOwner(requireActivity());

        ((MemoActivity) requireActivity()).setSupportActionBar(mBinding.toolBar);
        ActionBar toolbar = ((MemoActivity) requireActivity()).getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayShowTitleEnabled(false);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();
        if (bundle == null) {
            // Set text view as null
            mPos = -1;
            mBinding.titleEditText.setText(null);
            mBinding.contentEditText.setText(null);
        } else {
            // Set text view as memo contents if memo item is clicked
            Log.d("JUNE", "pos " + mPos);
            mPos = bundle.getInt("pos");
            MemoItem memo = mMemoViewModel.getMemoList().get(mPos);

            mBinding.titleEditText.setText(memo.title);
            mBinding.contentEditText.setText(memo.content);
        }
    }

    public void onBackKeyPressed() {
        saveMemo(mBinding.titleEditText.getText().toString(),
                mBinding.contentEditText.getText().toString(), mPos);
    }

    private void saveMemo(String title, String content, int pos) {
        Log.d("JUNE", "title : " + title + ", content : " + content + ", pos : " + pos);
        if (isEmptyMemo(title, content)) {
            if (pos >= 0) {
                Toast.makeText(requireActivity(), "Memo is removed", Toast.LENGTH_SHORT).show();
                mMemoViewModel.removeMemo(pos);
            }
        } else {
            Toast.makeText(requireActivity(), "Memo is saved", Toast.LENGTH_SHORT).show();
            if (pos >= 0) {
                mMemoViewModel.editMemo(title, content, pos);
            } else {
                mMemoViewModel.addMemo(title, content);
            }
        }
    }

    private boolean isEmptyMemo(String title, String content) {
        return title.equals("") && content.equals("");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_content_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}