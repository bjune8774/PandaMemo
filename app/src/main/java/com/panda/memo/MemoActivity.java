package com.panda.memo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.panda.memo.databinding.ActivityMemoBinding;

public class MemoActivity extends AppCompatActivity {
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private FragmentTransaction mTransaction;

    private MemoListFragment mListFragment;
    private MemoContentFragment mContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMemoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_memo);

        mListFragment = new MemoListFragment();
        mContentFragment = new MemoContentFragment();

        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.frameLayout, mListFragment).commitAllowingStateLoss();

    }
}