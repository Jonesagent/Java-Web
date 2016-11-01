package org.sg.sgg.sort;
/**
 * 
 * 堆积排序(Heapsort)是指利用堆积树（堆）这种资料结构所设计的一种排序算法，
 * 可以利用数组的特点快速定位指定索引的元素。堆排序是不稳定的排序方法，辅助空间为O(1)， 
 * 最坏时间复杂度为O(nlog2n) ，堆排序的堆序的平均性能较接近于最坏性能
 * 
 *  （1）用大根堆排序的基本思想

	① 先将初始文件R[1..n]建成一个大根堆,此堆为初始的无序区

	② 再将关键字最大的记录R[1](即堆顶)和无序区的最后一个记录R[n]交换，
		由此得到新的无序区R[1..n-1]和有序区R[n]，且满足R[1..n-1].keys≤R[n].key

	③由于交换后新的根R[1]可能违反堆性质，故应将当前无序区R[1..n-1]调整为堆。
		然后再次将R[1..n-1]中关键字最大的记录R[1]和该区间的最后一个记录R[n-1]交换，
		由此得到新的无序区R[1..n-2]和有序区R[n-1..n]，且仍满足关系R[1..n-2].keys≤R[n-1..n].keys，
		同样要将R[1..n-2]调整为堆。
 * 
 *  （2）大根堆排序算法的基本操作： 

		① 初始化操作：将R[1..n]构造为初始堆；

		② 每一趟排序的基本操作：将当前无序区的堆顶记录R[1]和该区间的最后一个记录交换，
			然后将新的无序区调整为堆(亦称重建堆)。

		注意： 

		①只需做n-1趟排序，选出较大的n-1个关键字即可以使得文件递增有序。

		②用小根堆排序与利用大根堆类似，只不过其排序结果是递减有序的。
			堆排序和直接选择排序相反：在任何时刻堆排序中无序区总是在有序区之前，
			且有序区是在原向量的尾部由后往前逐步扩大至整个向量为止。
 * @author TEAMAX
 *
 */
public class HeapSortTest {
	public static void main(String[] args) {
		int[] data = new int[]{5, 3, 6, 2, 1, 9, 4, 8, 7 };
		print(data);
		heapSort(data);
		System.out.println("排序后的数组：");
		print(data);
	}
	
	public static void heapSort(int[] data){
		for (int i = 0; i < data.length; i++) {
			createMaxdHeap(data, data.length - 1 - i);
			swap(data, 0,data.length - 1 - i);
			print(data);
		}
	}
	
	
	public static void createMaxdHeap(int[] data,int lastIndex){
		for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
			// 保存当前正在判断的节点   
			int k = i;
			while(2 * k + 1 <= lastIndex){
				//biggerIndex总是记录较大节点的值,先赋值为当前判断节点的左子节点
				int biggerIndex = 2 * k +1;
				if(biggerIndex < lastIndex){
					// 若右子节点存在，否则此时biggerIndex应该等于 lastIndex
					if (data[biggerIndex] < data[lastIndex + 1]) {
						// 若右子节点值比左子节点值大，则biggerIndex记录的是右子节点的值
						biggerIndex ++;
					}
				}
				if (data[k] < data[biggerIndex]) {
					 // 若当前节点值比子节点最大值小，则交换2者得值，交换后将biggerIndex值赋值给k 
					swap(data, k, biggerIndex);
					k = biggerIndex;
				}else {
					break;
				}
			}
		}
	}
	
	
	public static void swap(int[] data,int i , int j){
		if (j == i) {return;}
		data[i] = data[i] + data[j];
		data[j] = data[i] - data[j];
		data[i] = data[i] - data[j];
	}
	
	public static void print(int[] data) {  
        for (int i = 0; i < data.length; i++) {  
            System.out.print(data[i] + "\t");  
        }  
        System.out.println();  
    }  
	
}
