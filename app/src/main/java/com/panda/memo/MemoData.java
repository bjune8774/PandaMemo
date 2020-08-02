package com.panda.memo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoData {

    public static final List<MemoItem> ITEMS = new ArrayList<MemoItem>();

    public static final Map<String, MemoItem> ITEM_MAP = new HashMap<String, MemoItem>();

    private static final int COUNT = 3;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(MemoItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static MemoItem createDummyItem(int position) {
        return new MemoItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class MemoItem {
        public final String id;
        public final String title;
        public final String content;

        public MemoItem(String id, String content, String details) {
            this.id = id;
            this.title = content;
            this.content = details;
        }

//        @Override
//        public String toString() {
//            return title;
//        }
    }
}