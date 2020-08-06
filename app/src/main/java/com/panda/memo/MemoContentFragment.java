package com.panda.memo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

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
        return inflater.inflate(R.layout.fragment_memo_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("JUNE", "onViewCreated");
        mBinding = DataBindingUtil.bind(view);
        mBinding.setViewModel(mMemoViewModel);
        mBinding.setLifecycleOwner(requireActivity());

        ((MemoActivity) requireActivity()).setSupportActionBar(mBinding.toolBar);
        ActionBar toolbar = ((MemoActivity) requireActivity()).getSupportActionBar();
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();
        if (bundle == null) {
            mPos = -1;
            mBinding.titleEditText.setText(null);
            mBinding.contentEditText.setText(null);
        } else {
            mPos = bundle.getInt("pos");
            MemoItem memo = mMemoViewModel.getMemoList().get(mPos);

            mBinding.titleEditText.setText(memo.title);
            mBinding.contentEditText.setText(memo.content);

//            Toast.makeText(requireActivity(), "pos " + pos, Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackKeyPressed() {
        Toast.makeText(requireActivity(), "Data saved", Toast.LENGTH_SHORT).show();
        mMemoViewModel.saveMemo(mBinding.titleEditText.getText().toString(),
                mBinding.contentEditText.getText().toString(), mPos);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_content_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}