package AnalyzeProgram;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem1{
  private static final int LAST_INPUT = -1;

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      // 입력 받기
      Set<Integer> setA = getIntSet(sc);
      Set<Integer> setB = getIntSet(sc);

      // 합집합
      Set<Integer> union = union(setA, setB);
      List<Integer> sortedUnion = sort(union);
      System.out.println("합집합: " + setToString(sortedUnion));

      // 교집합
      Set<Integer> intersection = intersection(setA, setB);
      List<Integer> sortedIntersection = sort(intersection);
      System.out.println("교집합: " + setToString(sortedIntersection));

      // 차집합(A-B)
      Set<Integer> difference = difference(setA, setB);
      List<Integer> sortedDifference = sort(difference);
      System.out.println("차집합: " + setToString(sortedDifference));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static List<Integer> sort(Set<Integer> set) {
    return set.stream().sorted().collect(Collectors.toList());
  }

  private static Set<Integer> getIntSet(Scanner sc) {
    Set<Integer> set = new HashSet<>();
    while (sc.hasNextInt()) {
      int input = sc.nextInt();
      if (input == LAST_INPUT) {
        break;
      }
      set.add(input);
    }
    return set;
  }

  private static Set<Integer> union(Set<Integer> setA, Set<Integer> setB) {
    Set<Integer> union = new HashSet<>(setA);
    union.addAll(setB);
    return union;
  }

  private static Set<Integer> intersection(Set<Integer> setA, Set<Integer> setB) {
    Set<Integer> intersection = new HashSet<>(setA);
    intersection.retainAll(setB);
    return intersection;
  }

  private static Set<Integer> difference(Set<Integer> setA, Set<Integer> setB) {
    Set<Integer> difference = new HashSet<>(setA);
    difference.removeAll(setB);
    return difference;
  }

  private static String setToString(List<Integer> set) {
    StringBuilder sb = new StringBuilder();
    sb.append("< ");
    for (Integer integer : set) {
      sb.append(integer).append(" ");
    }
    sb.append(">");
    return sb.toString();
  }
}