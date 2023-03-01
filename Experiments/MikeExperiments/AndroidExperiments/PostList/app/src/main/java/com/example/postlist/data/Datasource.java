package com.example.postlist.data;
import com.example.postlist.R;
import com.example.postlist.model.Posts;

import java.util.Arrays;
import java.util.List;

public class Datasource {
    public List<Posts> loadPosts() {
        return Arrays.asList(
                new Posts(R.string.ListEx1),
                new Posts(R.string.ListEx2),
                new Posts(R.string.ListEx3),
                new Posts(R.string.ListEx4),
                new Posts(R.string.ListEx5),
                new Posts(R.string.ListEx6),
                new Posts(R.string.ListEx7),
                new Posts(R.string.ListEx7),
                new Posts(R.string.ListEx8),
                new Posts(R.string.ListEx9)
        );
    }
}