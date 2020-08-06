package com.panda.memo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoData {
    public static final List<MemoItem> MEMO_LIST = new ArrayList<MemoItem>();
//    public static final Map<String, MemoItem> ITEM_MAP = new HashMap<String, MemoItem>();
//    private static final int COUNT = 3;

//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }

    public static void addMemo(String title, String content) {
        MEMO_LIST.add(new MemoItem(title, content));
    }

    public static void editMemo(String title, String content, int pos) {
        MEMO_LIST.get(pos).title = title;
        MEMO_LIST.get(pos).content = content;
    }

    public static void removeMemo(int pos) {
        MEMO_LIST.remove(pos);
    }

//    private static void addItem(MemoItem item) {
//        ITEMS.add(item);
//        ITEM_MAP.put(item.id, item);
//    }
//
//    private static MemoItem createDummyItem(int position) {
//        return new MemoItem("Item " + position, makeDetails(position));
//    }
//
//    private static String makeDetails(int position) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("Details about Item: ").append(position);
//        for (int i = 0; i < position; i++) {
//            builder.append("\nMore details information here.");
//        }
//        return builder.toString();
//    }

    /**
     * A dummy item representing a piece of content.
     */
//    public static class MemoItem {
//        public final String title;
//        public final String content;
//
//        public MemoItem(String title, String content) {
//            this.title = title;
//            this.content = content;
//        }
//    }
}