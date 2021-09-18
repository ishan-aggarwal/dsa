// https://www.interviewbit.com/problems/pick-from-both-sides/

//Problem Description
//
//Given an integer array A of size N.
//
//You can pick B elements from either left or right end of the array A to get maximum sum.
//
//Find and return this maximum possible sum.
//
//NOTE: Suppose B = 4 and array A contains 10 elements then
//
//You can pick first four elements or can pick last four elements or can pick 1 from front and 3 from back etc . you need to return the maximum possible sum of elements you can pick.
//
//
//Problem Constraints
//1 <= N <= 105
//
//1 <= B <= N
//
//-103 <= A[i] <= 103
//
//
//
//Input Format
//First argument is an integer array A.
//
//Second argument is an integer B.
//
//
//
//Output Format
//Return an integer denoting the maximum possible sum of elements you picked.
//
//
//
//Example Input
//Input 1:
//
// A = [5, -2, 3 , 1, 2]
// B = 3
//Input 2:
//
// A = [1, 2]
// B = 1
//
//
//Example Output
//Output 1:
//
// 8
//Output 2:
//
// 2
//
//
//Example Explanation
//Explanation 1:
//
// Pick element 5 from front and element (1, 2) from back so we get 5 + 1 + 2 = 8
//Explanation 2:
//
// Pick element 2 from end as this is the maximum we can get

package arrays;

import java.util.Arrays;
import java.util.List;

public class PickFromBothSides {

	public static int solve(List<Integer> A, int B) {

		int n = A.size();

		// hold array prefix sum
		Integer[] prefixSum = new Integer[n];

		// hold array suffix sum
		Integer[] suffixSum = new Integer[n];

		int leftSum = 0;
		int rightSum = 0;
		for (int i = 0; i < n; i++) {
			leftSum += A.get(i);
			prefixSum[i] = leftSum;

			rightSum += A.get(n - i - 1);
			suffixSum[n - i - 1] = rightSum;
		}

		int maxSum = Integer.MIN_VALUE;

		// check which B elements to include
		for (int i = 0; i <= B; i++) {
			// hold current elements sum
			int currSum = 0;

			// check to include the sum of B elements from suffix array
			if (B - i > 0) {
				currSum += suffixSum[n - (B - i)];
			}
			// add ith element from prefix array if less elements are included from suffix
			// array
			if (i > 0) {
				currSum += prefixSum[i - 1];
			}

			// check if current sum is greater than max sum or not
			// and hold in max sum
			maxSum = Math.max(maxSum, currSum);
		}

		return maxSum;
	}

	public static void main(String[] args) {

		Integer[] arr = new Integer[] { 5, -2, 3, 1, 2 };
		int B = 3;

		List<Integer> A = Arrays.asList(arr);
		System.out.println(solve(A, B));
	}

}
