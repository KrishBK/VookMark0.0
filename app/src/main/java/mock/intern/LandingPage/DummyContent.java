package mock.intern.LandingPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mock.intern.R;

public class DummyContent {

    public static final List<Movie> ITEMS = new ArrayList<Movie>();
    public static final Map<Integer, Movie> ITEM_MAP = new HashMap<Integer, Movie>();
    private static final int COUNT = 25;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Movie item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Movie createDummyItem(int position) {
        return new Movie(R.drawable.name);
    }

    public static class Movie {

        public int id;

        public Movie(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int  name) {
            this.id = name;
        }
    }
}
