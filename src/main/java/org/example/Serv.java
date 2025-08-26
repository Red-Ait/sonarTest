package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class Serv {

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean isPalindrome(int x) {
        String xAsString = String.valueOf(x);
        for (int i = 0, j = xAsString.length() - 1; i < j; i++, j--) {
            if (xAsString.charAt(i) != xAsString.charAt(j)) return false;
        }
        return true;
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void qr(String data) throws IOException, WriterException {
        String filePath = "qrcode.png";

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        var bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 250, 250);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

        System.out.println("QR Code généré : " + filePath);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);

    }

    public void rotate(int[][] matrix) {
        int[][] result = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = result[j][matrix.length - i];
            }
        }
    }
    static Map<Integer, Integer> memo = new HashMap<>();
    public static int climbStairs(int n) {
        if(memo.containsKey(n)) return memo.get(n);
        if(n == 1) return 1;
        if(n == 2) return 2;
        int result = climbStairs(n - 1) + climbStairs(n - 2);
        memo.put(n, result);
        return result;
    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;

        if((p.right == null && q.left == null) ||(q.right == null && p.left == null))
            return isSameTree(p.right, q.left) && isSameTree(p.left, q.right);

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

/*        if(p.right == q.right && p.left == q.left)
        if(p.left == q.right && p.right == q.left) {
            return p.right == null || q.right == null;
        }
        return false;
  */  }
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode leftNode = null, beforLeftNode = null, beforRightNode = null, rightNode = null, cursor = head;
        if(left == 1) {
            leftNode = cursor;
        }
        int position = 1;
        while (cursor.next != null && rightNode == null) {
            if(position == left) {
                beforLeftNode = cursor;
                leftNode = cursor.next;
            }
            if(position == right) {
                beforRightNode = cursor;
                rightNode = cursor.next;
            }
            position++;
            cursor = cursor.next;
        }
        if(leftNode == null || rightNode == null) return head;
        if(beforLeftNode != null) {
            beforLeftNode.next = rightNode;
        }
        beforRightNode.next = leftNode;
        ListNode aux = leftNode.next;
        leftNode.next = rightNode.next;
        rightNode.next = aux;

        return head;
    }
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        for(int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>(Set.of(s.charAt(i)));
            for(int j = i + 1; j < s.length(); j++) {
                if(set.contains(s.charAt(j))) {
                    j = s.length();
                } else {
                    set.add(s.charAt(j));
                }
            }
            if(set.size() > maxLength) maxLength = set.size();
        }
        return maxLength;
    }
    public static String intToRoman(int num) {
        String[] symbols = new String[] {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] values = new int[] {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        int i = values.length - 1;
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            while (values[i] > num) {
                i--;
            }
            sb.append(symbols[i]);
            num -= values[i];
        }
        return sb.toString();
    }
    public int minimumTotal(List<List<Integer>> triangle) {
        int minPath = triangle.get(0).get(0);
        if(triangle.size() == 1) return minPath;
        return Math.max(brows(triangle, 1, 0, minPath), brows(triangle, 1, 1, minPath));

    }

    private int brows(List<List<Integer>> triangle, int row, int col, int path) {
        int sum = path + triangle.get(row).get(col);
        if(triangle.size() == row + 1) return sum;
        return Math.max(brows(triangle, row + 1, col, sum), brows(triangle, row + 1, col + 1, sum));
    }
    public int jump(int[] nums) {
        return jump(nums, 0);
    }

    private int jump(int[] nums, int index) {
        int res = 0;
        if(index < nums.length) {
            for(int i = 1 ; i <= nums[index] && index + i < nums.length; i++) {
                int aux = 1 + jump(nums, index + i);
                if(res > aux) res = aux;
            }
        }
        return res;
    }


}
