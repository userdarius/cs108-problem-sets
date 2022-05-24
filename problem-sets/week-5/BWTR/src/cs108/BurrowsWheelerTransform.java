package cs108;

import java.util.*;

public final class BurrowsWheelerTransform {
    private BurrowsWheelerTransform() {}

    Queue<Character> listOfChars;

    public static Pair<Integer, String> forward(String s) {
        if(s.isEmpty()){
            throw new IllegalArgumentException();
        }

        List<String> rs = allRotations(s);
        Collections.sort(rs);
        StringBuilder b = new StringBuilder(s.length());
        for (String r: rs) {
            b.append(r.charAt(r.length()-1));
        }
        return new Pair<>(rs.indexOf(s), b.toString());

    }

    public static List<String> allRotations(String s){
        List<String> rs = new ArrayList<>(s.length());
        while (rs.size() < s.length()){
            rs.add(s);
            s = new StringBuilder(s.length())
                    .append(s, 1, s.length())
                    .append(s, 0 , 1)
                    .toString();
        }
        return rs;
    }

    public static String backward(Pair<Integer,String> p) {
        return null;
}}

