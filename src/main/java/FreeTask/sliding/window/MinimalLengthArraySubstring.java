package FreeTask.sliding.window;

public class MinimalLengthArraySubstring {

    static int minSubArrayLen(int y, int[] arr) {

        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        for (int right = 0; arr.length > right; right++) {
            sum += arr[right];
            while (sum >= y) {
                if (minLength > right - left+1) {
                    minLength = right - left+1;
                }
                sum -= arr[left];
                left++;
            }

        }
        return minLength==Integer.MAX_VALUE ? 0:minLength;
    }


    static void main(String[] args) {


        System.out.println(minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(minSubArrayLen(4, new int[]{1, 4, 4}));           // → 1 ✅
        System.out.println(minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1})); // → 0 ✅
    }
}
