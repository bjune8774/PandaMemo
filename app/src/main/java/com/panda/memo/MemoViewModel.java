package com.panda.memo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class MemoViewModel extends ViewModel {
    // For Live Data
//    public List<MemoItem> memoItemList = MemoData.MEMO_LIST;

    public List<MemoItem> getMemoList() {
        return MemoData.MEMO_LIST;
    }

    public void saveMemo(String title, String content, int pos) {
        Log.d("JUNE", "title : " + title + ", content : " + content + ", pos : " + pos);
        if (title.equals("") && content.equals("")) {
            if (pos >= 0) {
                MemoData.removeMemo(pos);
            }
        } else {
            if (pos >= 0) {
                MemoData.editMemo(title, content, pos);
            } else {
                MemoData.addMemo(title, content);
            }
        }
    }

    public int getMemoCount() {
        return MemoData.MEMO_LIST.size();
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new MemoViewModel();
        }
    }
}
