package com.panda.memo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    private int mCurrentPos;
    private boolean mIsPortrait;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_memo);
        mMemoViewModel = new ViewModelProvider(this, new MemoViewModel.Factory()).get(MemoViewModel.class);

        mListFragment = new MemoListFragment(mMemoViewModel);
        mContentFragment = new MemoContentFragment(mMemoViewModel);
        mEmptyFragment = new MemoEmptyFragment();

        setSupportActionBar(mBinding.toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        if (getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_0) {
            mIsPortrait = true;
        } else {
            mIsPortrait = false;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("pos", -1);
        mContentFragment.setArguments(bundle);

        if (savedInstanceState != null
                && savedInstanceState.containsKey("fragment")) {
            String savedString = savedInstanceState.getString("fragment");
//            Toast.makeText(this, savedString, Toast.LENGTH_SHORT).show();
        }

        mCurrentPos = -1;
        mCurrentFragment = getHomeFragment();

        mListFragment.setOnItemClickListener(new MemoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                mCurrentFragment = mContentFragment;
                mCurrentPos = pos;

                if (mIsPortrait) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("pos", pos);
                    mContentFragment.setArguments(bundle);

                    mBinding.fabAddMemo.hide();
                    replaceToCurrentFragment(R.id.portraitFrameLayout, TRANSIT_FRAGMENT_FADE);
                } else {
                    mContentFragment.updateContent(pos);
                }
            }
        });

        mBinding.fabAddMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentFragment = mContentFragment;
                mCurrentPos = -1;

                if (mIsPortrait) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("pos", -1);
                    mContentFragment.setArguments(bundle);

                    mBinding.fabAddMemo.hide();
                    replaceToCurrentFragment(R.id.portraitFrameLayout, TRANSIT_FRAGMENT_FADE);
                } else {
                    mContentFragment.updateContent(-1);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsPortrait) {
            replaceToCurrentFragment(R.id.portraitFrameLayout, TRANSIT_FRAGMENT_FADE);
        } else {
            replaceFragment(R.id.landscapeFrameLayoutLeft, mCurrentFragment, TRANSIT_FRAGMENT_FADE);
            replaceFragment(R.id.landscapeFrameLayoutRight, mContentFragment, TRANSIT_FRAGMENT_FADE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        removeFragment(mCurrentFragment);
//        mCurrentFragment = null;
    }

//    private void replaceMenu() {
//        MenuInflater inflater = getMenuInflater();
//
//        if (mCurrentFragment instanceof MemoContentFragment) {
//            inflater.inflate(R.menu.menu_content_fragment, menu);
//        }
//    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        Toast.makeText(this, "" + getCurrentFocus(), Toast.LENGTH_SHORT).show();
        outState.putString("fragment", "content");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            case R.id.menuSave:
                onBackPressed();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mIsPortrait && mCurrentFragment == mContentFragment) {
            mContentFragment.saveMemo();
            mBinding.fabAddMemo.show();

            mCurrentFragment = getHomeFragment();
            replaceToCurrentFragment(R.id.portraitFrameLayout, TRANSIT_FRAGMENT_FADE);
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

    private void removeFragment(int layoutId, Fragment fragment) {
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.remove(fragment);
        mTransaction.commit();
    }

    private void replaceToCurrentFragment(int layoutId, int transitionType) {
        replaceFragment(layoutId, mCurrentFragment, transitionType);
    }

    private void replaceFragment(int layoutId, Fragment fragment, int transitionType) {
        mTransaction = mFragmentManager.beginTransaction();
        mTransaction.replace(layoutId, fragment);
//        mTransaction.addToBackStack(null);
        mTransaction.setTransition(transitionType);
        mTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//
//        if (mCurrentFragment instanceof MemoContentFragment) {
//            inflater.inflate(R.menu.menu_content_fragment, menu);
//        } else if (mCurrentFragment instanceof MemoListFragment ||
//                mCurrentFragment instanceof MemoEmptyFragment) {
//            inflater.inflate(R.menu.menu_home_fragment, menu);
//        }
        return super.onCreateOptionsMenu(menu);
    }
}
