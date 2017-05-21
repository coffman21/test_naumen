package searcher;

import com.sun.deploy.util.ArrayUtil;
import searcher.Searcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static long getStamp() {
        // divide and multiply by 100000 for reaching repeatable timestamps
        long at2010 = 1262304000 / 100000L; // according to http://www.onlineconversion.com/unix_time.htm
        long now = (new Date().getTime() / 1000L) / 100000L;
        return ThreadLocalRandom.current().nextLong(at2010, now)*100000L;
    }

    public static void main(String[] args) {
        ArrayList<String> dict = new ArrayList<String>();
        ArrayList<Long> stamps = new ArrayList<Long>();
        File f = new File("./src/main/test/resources/dict");
        try {
            Scanner s = new Scanner(f);
            int i = 0;
            while (s.hasNext()) {
                String next = s.next();
                if (next.length() > 32) next = next.substring(0, 32);
                dict.add(next);
                stamps.add(getStamp());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.shuffle(dict);
        String[] classNames = dict.toArray(new String[100000]);
        long[] modificationDates = stamps.stream().mapToLong(Long::longValue).toArray();

        Searcher s = new Searcher();
        s.refresh(classNames, modificationDates);
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 100000); i++) {
            String start = classNames[ThreadLocalRandom.current().nextInt(100000)];
            start = start.substring(0, start.length()/2);
            s.guess(start);
        }
    }
}