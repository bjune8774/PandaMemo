package com.panda.memo;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.panda.memo.databinding.ActivityMemoBinding;

import static androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;

public class MemoActivity extends AppCompatActivity {
    private MemoViewModel mMemoViewModel;
    private ActivityMemoBinding mBinding;

    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private FragmentTransaction mTransaction;

    private MemoListFragment mListFragment;
    private MemoContentFragment mContentFragment;
    private MemoEmptyFragment mEmptyFragment;
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_memo);
        mMemoViewModel = new ViewModelProvider(this, new MemoViewModel.Factory()).get(MemoViewModel.class);

        mListFragment = new MemoListFragment(mMemoViewModel);
        mContentFragment = new MemoContentFragment(mMemoViewModel);
        mEmptyFragment = new MemoEmptyFragment();

        mCurrentFragment = getHomeFragment();

        mListFragment.setOnItemClickListener(new MemoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Bundle bundle = new Bundle();
                bundle.putInt("pos", pos);
                mContentFragment.setArguments(bundle);

                mBinding.fabAddMemo.hide();
                replaceFragment(mContentFragment, TRANSIT_FRAGMENT_FADE);
            }
        });

        mBinding.fabAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContentFragment.setArguments(null);

                mBinding.fabAddMemo.hide();
                replaceFragment(mContentFragment, TRANSIT_FRAGMENT_FADE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        replaceFragment(mCurrentFragment, TRANSIT_FRAGMENT_FADE);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment == mContentFragment) {
            mContentFragment.onBackKeyPressed();
            mBinding.fabAddMemo.show();

            replaceFragment(getHomeFragment(), TRANSIT_FRAGMENT_FADE);
        } else {
            super.onBackPressed();
        }
    }

    private Fragment getHomeFragment() {
        if (mMemoViewModel.getMemoCount() == 0) {
            return mEmptyFragment;
        } else {
            return mListFragment;
        }
    }

    private void replaceFragment(Fragment fragment, int transitionType) {
        mCurrentFragment = fragment;
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(R.id.frameLayout, mCurrentFragment);
//        mTransaction.addToBackStack(null);
        mTransaction.setTransition(transitionType);
        mTransaction.commit();
    }
}
