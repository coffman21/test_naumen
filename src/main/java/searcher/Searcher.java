package searcher;

public class Searcher implements ISearcher {
    Searcher() {}

    @Override
    public void refresh(String[] classNames, long[] modificationDates) {

    }

    @Override
    public String[] guess(String start) {
        return new String[0];
    }
}