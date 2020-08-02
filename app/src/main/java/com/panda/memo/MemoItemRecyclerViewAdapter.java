package com.panda.memo;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.panda.memo.MemoData.MemoItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MemoItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MemoItemRecyclerViewAdapter extends RecyclerView.Adapter<MemoItemRecyclerViewAdapter.MemoViewHolder> {

    private final List<MemoItem> mValues;

    public MemoItemRecyclerViewAdapter(List<MemoItem> items) {
        mValues = items;
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_memo_list_item, parent, false);
        return new MemoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MemoViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitle.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class MemoViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitle;
        public final TextView mContentView;
        public MemoItem mItem;

        public MemoViewHolder(View view) {
            super(view);
            mView = view;
            mTitle = (TextView) view.findViewById(R.id.title);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}