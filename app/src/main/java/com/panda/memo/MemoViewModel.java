package com.panda.memo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import static com.panda.memo.MemoData.MEMO_LIST;

public class MemoViewModel extends ViewModel {
    // For Live Data
//    public List<MemoItem> memoItemList = MemoData.MEMO_LIST;

    public List<MemoItem> getMemoList() {
        return MEMO_LIST;
    }

    public MemoItem getMemo(int pos) {
        return MEMO_LIST.get(pos);
    }

    public void addMemo(String title, String content) {
        MEMO_LIST.add(new MemoItem(title, content));
    }

    public void editMemo(String title, String content, int pos) {
        MEMO_LIST.get(pos).title = title;
        MEMO_LIST.get(pos).content = content;
    }

    public void removeMemo(int pos) {
        MEMO_LIST.remove(pos);
    }

    public int getMemoCount() {
        return MEMO_LIST.size();
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
