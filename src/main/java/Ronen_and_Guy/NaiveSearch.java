package Ronen_and_Guy;

// Java program for Naive Pattern Searching
public class NaiveSearch {

    private static int search(String txt, String pat)
    {
        int M = pat.length();
        int N = txt.length();

        /* A loop to slide pat one by one */
        for (int i = 0; i <= N - M; i++) {

            int j;

            /* For current index i, check for pattern
              match */
            for (j = 0; j < M; j++)
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;

            if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                return i;
        }

        // match not found
        return -1;
    }

    public static int findIndexOf(String text, String pattern)
    {
        return search(pattern, text);
    }
}
