import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class lexiSmallString {
    public static void main(String[] args) {

        /*
         * Input: s1 = "parker", s2 = "morris", baseStr = "parser"
         * Output: "makkek"
         * 
         */
        /*
         * s1 =
         * "cgokcgerolkgksgbhgmaaealacnsshofjinidiigbjerdnkolc"
         * s2 =
         * "rjjlkbmnprkslilqmbnlasardrossiogrcboomrbcmgmglsrsj"
         * baseStr =
         * "bxbwjlbdazfejdsaacsjgrlxqhiddwaeguxhqoupicyzfeupcn"
         * 99 / 116 testcases passed
         * Output
         * "axawaaaaazaaaaaaaaaaaaaxbbaaawaaauxbbaubaayzaaubaa"
         * Expected
         * "axawaaaaazaaaaaaaaaaaaaxaaaaawaaauxaaauaaayzaauaaa"
         */
        // String s1 = "parker", s2 = "morris", baStr = "parser";

        // String s1 = "parker", s2 = "morris", baStr = "parser";
        String 
        s1 = "cgokcgerolkgksgbhgmaaealacnsshofjinidiigbjerdnkolc", 
        s2 = "rjjlkbmnprkslilqmbnlasardrossiogrcboomrbcmgmglsrsj", 
        baStr = "bxbwjlbdazfejdsaacsjgrlxqhiddwaeguxhqoupicyzfeupcn";
       
        String ans = smallestEquivalentString(s1, s2, baStr);

        System.out.println(" bas :" + baStr);
        
        System.out.println(" ans :" + ans);
        System.out.println(" exp :"+"axawaaaaazaaaaaaaaaaaaaxaaaaawaaauxaaauaaayzaauaaa");
    }
    public static String smallestEquivalentString(String s1, String s2, String baseStr) {

        List<Set<Character>> listSet = new ArrayList<>();

        for (int i = 0; i < s1.length(); i++) {
            char a = s1.charAt(i);
            char b = s2.charAt(i);
            fillSet(listSet, a, b);

        }

        System.out.println(listSet);
        String ans ="";



        for (int i = 0; i < baseStr.length(); i++) {
            char c = baseStr.charAt(i);
            
            char smallest = getSmallest(listSet,c);
            if(c=='b')
            {
                System.out.println( "Smallest : "+smallest);
            }
            ans=ans+smallest;


        }

        return ans;

    }

    private static  char getSmallest(List<Set<Character>> listSet, char c) {
        char  smallest=c;

        for (Set<Character> set : listSet) {
            if (set.contains(c)) {
                // char smallest =c;
                for (char character : set) {
                    if (character < smallest)
                        smallest = character;

                }

            }
            ;
        }
    
        return smallest;
    }

    private static void fillSet(List<Set<Character>> listSet, char a, char b) {

        int n = listSet.size();
        if (n == 0) {
            Set<Character> set = new HashSet<>();
            set.add(a);
            set.add(b);

            // System.out.println(" N=0 :  " + set + " a :-> " + a + "   b :-> " + b);
            listSet.add(set);
            // System.out.println(" After :  " + set + " listSet : " + listSet);
            // System.out.println();

        } else {
            boolean placed = false;
            for (int i = 0; i < listSet.size(); i++) {

                Set<Character> set = listSet.get(i);
                if (set.contains(a) || set.contains(b)) {
                    set.add(a);
                    set.add(b);
                    placed = true;
                    // System.out.println(" Before :  " + set + " a :-> " + a + "   b :-> " + b);
                    // listSet.add(i, set);
                    // System.out.println(" After :  " + set + " listSet : " + listSet);
                    // System.out.println();
                    break;

                }
            }

            if (!placed) {
                Set<Character> set = new HashSet<>();
                set.add(a);
                set.add(b);

                // System.out.println(" Before Not Found a and b :  " + set + " a :-> " + a + "   b :-> " + b);
                listSet.add(set);
                // System.out.println(" After :  " + set + " listSet : " + listSet);
                // System.out.println();

                return;
            }
        }

        return;

    }
}
 class lexiSmallString_Solution {
    public String smallestEquivalentString(String A, String B, String S) {
      int[] graph = new int[26];
      for(int i = 0; i < 26; i++) {
          graph[i] = i;
      }
      for(int i = 0; i < A.length(); i++) {
          int a = A.charAt(i) - 'a';
          int b = B.charAt(i) - 'a';
          int end1 = find(graph, b);
          int end2 = find(graph, a);
          if(end1 < end2) {
              graph[end2] = end1;
          } else {
              graph[end1] = end2;
          }
      }
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < S.length(); i++) {
          char c = S.charAt(i);
          sb.append((char)('a' + find(graph, c - 'a')));
      }
      return sb.toString();
  }
  
  private int find(int[] graph, int idx) {
      while(graph[idx] != idx) {
          idx = graph[idx];
      }
      return idx;
  }

}