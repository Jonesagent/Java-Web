package org.sg.sgg.sort;
/**
 *  Java排序算法（二）：直接选择排序

	直接选择排序的基本操作就是每一趟从待排序的数据元素中选出最小（或最大）的一个元素，
	顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完，它需要经过n-1趟比较。
	算法不稳定，O(1)的额外的空间，比较的时间复杂度为O(n^2)，交换的时间复杂度为O(n)，并不是自适应的。
	在大多数情况下都不推荐使用。只有在希望减少交换次数的情况下可以用。

	基本思想

	n个记录的文件的直接选择排序可经过n-1趟直接选择排序得到有序结果：

	①初始状态：无序区为R[1..n]，有序区为空。

	②第1趟排序

	在无序区R[1..n]中选出关键字最小的记录R[k]，将它与无序区的第1个记录R[1]交换，
	使R[1..1]和R[2..n]分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区。

	……

	③第i趟排序

	第i趟排序开始时，当前有序区和无序区分别为R[1..i-1]和R(1≤i≤n-1)。
	该趟排序从当前无序区中选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，
	使R[1..i]和R分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区。

	这样，n个记录的文件的直接选择排序可经过n-1趟直接选择排序得到有序结果。

	算法实现
 * @author TEAMAX
 *
 */
public class SelectSortTest {
	public static void main(String[] args) {
		int[] data = new int[]{5, 3, 6, 2, 1, 9, 4, 8, 7 };
		print(data);
		selectSort(data);
		print(data);
	}
	
	public static void selectSort(int[] data){
		for (int i = 0; i < data.length - 1; i++) {
			
			int minIndex = i; // 记录最小值的索引
			for(int j = i + 1; j < data.length; j++){
				if (data[j] < data[minIndex]) {
					 minIndex = j; // 若后面的元素值小于最小值,将j赋值给minIndex  
				}
			}
			
			if (minIndex != i) {
				swap(data, i, minIndex);
				print(data);
			}
		}
	}
	
	public static void swap(int[] data,int i,int minIndex){
		if (minIndex == i) {return;}
		data[i] = data[i] + data[minIndex];  
        data[minIndex] = data[i] - data[minIndex];  
        data[i] = data[i] - data[minIndex];
	}
	
	public static void print(int[] data){
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i]+"\t");
		}
		System.out.println();
	}
}
