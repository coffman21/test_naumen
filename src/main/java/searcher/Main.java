package searcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static String[] dictionary() {
        ArrayList<String> dict = new ArrayList<>();
        File f = new File("./src/main/test/resources/dict");
        try {
            Scanner s = new Scanner(f);
            for (int i = 0; i < 100000; i++) {
                if (s.hasNext()) {
                    String next = s.next();
                    if (next.length() > 32) next = next.substring(0, 32);
                    dict.add(next);
                }
            }

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dict.toArray(new String[100000]);
    }

    private static long getStamp() {
        // divide and multiply by 100000 for reaching repeatable timestamps
        long then = 1495600000000L / 1000L;
        long now = (new Date().getTime() / 1000L);
        return ThreadLocalRandom.current().nextLong(then, now);
    }
    private static long[] stamps() {
        long[] s = new long[100000];
        for (int i = 0; i < 100000; i++) {
            s[i] = getStamp();
        }
        return s;
    }




    public static void main(String[] args) {
        //String[] classNames = dictionary();
        //long[] modificationDates = stamps();
        String[] classNames = {"arthur", "clime", "aband", "array", "arth", "bike", "colata"};
        long[] modificationDates = {140, 137, 127, 120, 120, 120, 115};

        Searcher s = new Searcher();
        long now = new Date().getTime();
        s.refresh(classNames, modificationDates);
        System.out.println("time to refresh(): " + (new Date().getTime() - now) + " ms");

        ArrayList<Integer> execTimes = new ArrayList<>();
        //for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 100000); i++) {
        for (int i = 0; i < 15; i++) {
            String start = classNames[(ThreadLocalRandom.current().nextInt(classNames.length))];
            start = start.substring(0, ThreadLocalRandom.current().nextInt(1, start.length()-1));

            now = new Date().getTime();
            s.guess("a");
            execTimes.add((int) (new Date().getTime() - now));
            System.out.println("string: " + start);
            System.out.println("time to guess(): " + (new Date().getTime() - now));

        }
        System.out.println("average time to guess(): " + execTimes.stream().reduce(0, Integer::sum)/execTimes.size() + " ms");

    }
}