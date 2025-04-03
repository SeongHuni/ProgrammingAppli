package AnalyzeProgram;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem2{
  private static final int LAST_INPUT = -1;

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      // 입력 받기
      List<Integer> setA = getIntSet(sc);
      List<Integer> setB = getIntSet(sc);

      // 정렬
      List<Integer> sortedSetA = sort(setA);
      List<Integer> sortedSetB = sort(setB);

      // 합집합
      List<Integer> union = union(sortedSetA, sortedSetB);
      System.out.println("합집합: " + setToString(union));

      // 교집합
      List<Integer> intersection = intersection(sortedSetA, sortedSetB);
      System.out.println("교집합: " + setToString(intersection));

      // 차집합(A-B)
      List<Integer> difference = difference(sortedSetA, sortedSetB);
      System.out.println("차집합: " + setToString(difference));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static List<Integer> getIntSet(Scanner sc) {
    List<Integer> list = new ArrayList<>();
    while (sc.hasNextInt()) {
      int input = sc.nextInt();
      if (input == LAST_INPUT) {
        break;
      }
      if (input > 0 && !isContain(list, input)) {
        list.add(input);
      }
    }
    return list;
  }

  private static boolean isContain(List<Integer> list, int value) {
    for (Integer integer : list) {
      if (integer == value) {
        return true;
      }
    }
    return false;
  }

  private static List<Integer> sort(List<Integer> set) {
    List<Integer> sorted = new ArrayList<>(set);
    int maxIndexI = sorted.size() - 1;
    for (int i = 0; i < maxIndexI; i++) {
      int maxIndexJ = maxIndexI - i;
      for (int j = 0; j < maxIndexJ; j++) {
        if (sorted.get(j) > sorted.get(j + 1)) {
          int temp = sorted.get(j);
          sorted.set(j, sorted.get(j + 1));
          sorted.set(j + 1, temp);
        }
      }
    }
    return sorted;
  }

  private static List<Integer> union(List<Integer> setA, List<Integer> setB) {
    List<Integer> union = new ArrayList<>(setA.size() + setB.size());

    int indexA = 0, indexB = 0;
    while (indexA < setA.size() && indexB < setB.size()) {
      if (setA.get(indexA).equals(setB.get(indexB))) {
        union.add(setA.get(indexA));
        indexA++;
        indexB++;
      } else if (setA.get(indexA) < setB.get(indexB)) {
        union.add(setA.get(indexA));
        indexA++;
      } else {
        union.add(setB.get(indexB));
        indexB++;
      }
    }

    while (indexA < setA.size()) {
      union.add(setA.get(indexA++));
    }

    while (indexB < setB.size()) {
      union.add(setB.get(indexB++));
    }

    return union;
  }

  private static List<Integer> intersection(List<Integer> setA, List<Integer> setB) {
    List<Integer> intersection = new ArrayList<>();

    int indexA = 0, indexB = 0;
    while (indexA < setA.size() && indexB < setB.size()) {
      if (setA.get(indexA).equals(setB.get(indexB))) {
        intersection.add(setA.get(indexA));
        indexA++;
        indexB++;
      } else if (setA.get(indexA) < setB.get(indexB)) {
        indexA++;
      } else {
        indexB++;
      }
    }

    return intersection;
  }

  private static List<Integer> difference(List<Integer> setA, List<Integer> setB) {
    List<Integer> difference = new ArrayList<>();

    int indexA = 0, indexB = 0;
    while (indexA < setA.size() && indexB < setB.size()) {
      if (setA.get(indexA).equals(setB.get(indexB))) {
        indexA++;
        indexB++;
      } else if (setA.get(indexA) < setB.get(indexB)) {
        difference.add(setA.get(indexA));
        indexA++;
      } else {
        indexB++;
      }
    }

    while (indexA < setA.size()) {
      difference.add(setA.get(indexA++));
    }

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

