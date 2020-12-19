//import java.io.*;
//import java.math.*;
//import java.security.*;
//import java.text.*;
//import java.util.*;
//import java.util.concurrent.*;
//import java.util.function.*;
//import java.util.regex.*;
//import java.util.stream.*;
//import static java.util.stream.Collectors.joining;
//import static java.util.stream.Collectors.toList;
//
//
//class Result {
//
//  /*
//   * Complete the 'prison' function below.
//   *
//   * The function is expected to return a LONG_INTEGER.
//   * The function accepts following parameters:
//   *  1. INTEGER n
//   *  2. INTEGER m
//   *  3. INTEGER_ARRAY h
//   *  4. INTEGER_ARRAY v
//   */
//
//  static class Cell {
//    int h;
//    int v;
//
//    Cell(int h, int v) {
//      this.h = h;
//      this.v = v;
//    }
//  }
//
//  public static long prison(int n, int m, List<Integer> h, List<Integer> v) {
//    // Write your code here
//    ArrayList<ArrayList<Cell>> cells = new ArrayList<>();
//    for (int i=0; i<n; i++) {
//      cells.add(new ArrayList<>());
//    }
//
//    for (int i=0; i<n; i++) {
//      for (int j=0; j<m; j++) {
//        cells.get(i).add(new Cell(1, 1));
//      }
//
//      int count = 0;
//      for (Integer row : h) {
//        row = row - count;
//        for (int c = 0; c<cells.get(0).size(); c++) {
//          cells.get(row-1).get(c).h += 1;
//        }
//        cells.remove(row);
//        count++;
//      }
//
//      count = 0;
//      for (Integer col : v) {
//        col = col - count;
//        for (int r = 0; r<cells.size(); r++) {
//          cells.get(r).get(col-1).v += 1;
//          cells.get(r).remove(col);
//        }
//        count++;
//      }
//
//      int max = 0;
//      for (int a=0; a<n; a++) {
//        for (int j=0; j<m; j++) {
//          max = Math.max(cells.get(a).get(j).h * cells.get(a).get(j).v, max);
//        }
//      }
//
//      return max;
//    }
//
//    public class Solution {
//      public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int n = Integer.parseInt(bufferedReader.readLine().trim());
//
//        int m = Integer.parseInt(bufferedReader.readLine().trim());
//
//        int hCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<Integer> h = IntStream.range(0, hCount).mapToObj(i -> {
//          try {
//            return bufferedReader.readLine().replaceAll("\\s+$", "");
//          } catch (IOException ex) {
//            throw new RuntimeException(ex);
//          }
//        })
//            .map(String::trim)
//            .map(Integer::parseInt)
//            .collect(toList());
//
//        int vCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<Integer> v = IntStream.range(0, vCount).mapToObj(i -> {
//          try {
//            return bufferedReader.readLine().replaceAll("\\s+$", "");
//          } catch (IOException ex) {
//            throw new RuntimeException(ex);
//          }
//        })
//            .map(String::trim)
//            .map(Integer::parseInt)
//            .collect(toList());
//
//        long result = Result.prison(n, m, h, v);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
//      }
//    }
