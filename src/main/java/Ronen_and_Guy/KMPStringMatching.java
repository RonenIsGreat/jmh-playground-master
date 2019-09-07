package Ronen_and_Guy;

import javax.xml.stream.events.Characters;

public class KMPStringMatching {
    public static int KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                //System.out.println("Found pattern "
                //        + "at index " + (i - j));
                //j = lps[j - 1];
                return i-j;
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return -1;
    }

    // the Pre=Processing algorithm to find the LPS (Longest Prefix Suffix).
    private static void computeLPSArray(String pat, int M, int lps[]) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

/*
    // Driver program to test above function
    public static void main(String args[])
    {
        System.out.println("testing");
        String txt = "ABABDABACDABABCABAB";
        String pat = "ABABCABAB";
        new KMPStringMatching().KMPSearch(pat, txt);
    }
    */

    //Editing the original algorithm to get better results.
    public static int KMPSearchImprovedMemory(String pat, String txt) {
        // instead of creating variables that will hold the length of the strings, we
        // will use the list's implemented variable to retrieve the length of the string.
        // int M = pat.length();
        // int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[pat.length()];
        int j = 0; // index for pat[]
        // Preprocess the pattern (calculate lps[]
        // array)
        improvedMemoryComputeLPSArray(pat, lps);

        int i = 0; // index for txt[]
        while (i < txt.length()) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == pat.length()) {
                //System.out.println("Found pattern "
                //        + "at index " + (i - j));
                // j = lps[j - 1];
                return i-j;
            }
            // mismatch after j matches
            else if (i < txt.length() && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return -1;
    }

    // Improving the helpful function that turns the Pattern into LPS.
    private static void improvedMemoryComputeLPSArray(String pat, int lps[]) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i++] = ++len;
            }
            else // (pat[i] != pat[len])
            {
                if (len != 0) {
                    len = lps[len - 1];
                }
                else // if (len == 0)
                {
                    lps[i++] = len;
                }
            }
        }
    }

    public static int KMPSearchImprovedRunTime(String pat, String txt) {

        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        improvedRunTimeComputeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                //System.out.println("Found pattern "
                //        + "at index " + (i - j));
                //j = lps[j - 1];
                return i-j;
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return -1;
    }

    private static void improvedRunTimeComputeLPSArray(String pat, int M, int lps[]) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}