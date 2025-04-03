package AnalyzeProgram;

import java.util.*;

public class Set2 {
  private static final int LastInput = -1;

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {
      // 집합 A 입력
      System.out.print("집합 A 입력: ");
      Set<Integer> setA = inputSet(sc);

      // 집합 B 입력
      System.out.print("집합 B 입력: ");
      Set<Integer> setB = inputSet(sc);

      // 결과 계산 및 출력
      printResult("합집합", union(setA, setB));
      printResult("교집합", intersection(setA, setB));
      printResult("차집합 (A - B)", difference(setA, setB));
    } catch (Exception e) {
      System.out.println("오류 발생: " + e.getMessage());
    }
  }

  // 입력 메서드 (공백으로 구분, -1 입력 시 종료)
  private static Set<Integer> inputSet(Scanner sc) {
    Set<Integer> resultSet = new HashSet<>();
    String[] inputs = sc.nextLine().split(" ");
    for (String input : inputs) {
      try {
        int value = Integer.parseInt(input);
        if (value == LastInput) break; // 종료 조건
        if (value >= 0) resultSet.add(value); // 음수는 제외
      } catch (NumberFormatException e) {
        System.out.println("유효하지 않은 입력: " + input);
      }
    }
    return resultSet;
  }

  // 합집합
  private static Set<Integer> union(Set<Integer> setA, Set<Integer> setB) {
    Set<Integer> unionSet = new HashSet<>(setA);
    unionSet.addAll(setB);
    return unionSet;
  }

  // 교집합
  private static Set<Integer> intersection(Set<Integer> setA, Set<Integer> setB) {
    Set<Integer> intersectionSet = new HashSet<>(setA);
    intersectionSet.retainAll(setB);
    return intersectionSet;
  }

  // 차집합 (A - B)
  private static Set<Integer> difference(Set<Integer> setA, Set<Integer> setB) {
    Set<Integer> differenceSet = new HashSet<>(setA);
    differenceSet.removeAll(setB);
    return differenceSet;
  }

  // 정렬 및 출력
  private static void printResult(String title, Set<Integer> set) {
    List<Integer> sortedList = new ArrayList<>(set); // Set을 List로 변환
    Collections.sort(sortedList); // Collections.sort로 정렬
    System.out.print(title + ": < ");
    for (Integer value : sortedList) {
      System.out.print(value + " ");
    }
    System.out.println(">");
  }
}