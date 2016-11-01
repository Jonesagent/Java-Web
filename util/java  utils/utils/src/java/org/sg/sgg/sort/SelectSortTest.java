package org.sg.sgg.sort;
/**
 *  Java�����㷨��������ֱ��ѡ������

	ֱ��ѡ������Ļ�����������ÿһ�˴Ӵ����������Ԫ����ѡ����С������󣩵�һ��Ԫ�أ�
	˳��������ź�������е����ֱ��ȫ�������������Ԫ�����꣬����Ҫ����n-1�˱Ƚϡ�
	�㷨���ȶ���O(1)�Ķ���Ŀռ䣬�Ƚϵ�ʱ�临�Ӷ�ΪO(n^2)��������ʱ�临�Ӷ�ΪO(n)������������Ӧ�ġ�
	�ڴ��������¶����Ƽ�ʹ�á�ֻ����ϣ�����ٽ�������������¿����á�

	����˼��

	n����¼���ļ���ֱ��ѡ������ɾ���n-1��ֱ��ѡ������õ���������

	�ٳ�ʼ״̬��������ΪR[1..n]��������Ϊ�ա�

	�ڵ�1������

	��������R[1..n]��ѡ���ؼ�����С�ļ�¼R[k]���������������ĵ�1����¼R[1]������
	ʹR[1..1]��R[2..n]�ֱ��Ϊ��¼��������1�������������ͼ�¼��������1��������������

	����

	�۵�i������

	��i������ʼʱ����ǰ���������������ֱ�ΪR[1..i-1]��R(1��i��n-1)��
	��������ӵ�ǰ��������ѡ���ؼ�����С�ļ�¼ R[k]���������������ĵ�1����¼R������
	ʹR[1..i]��R�ֱ��Ϊ��¼��������1�������������ͼ�¼��������1��������������

	������n����¼���ļ���ֱ��ѡ������ɾ���n-1��ֱ��ѡ������õ���������

	�㷨ʵ��
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
			
			int minIndex = i; // ��¼��Сֵ������
			for(int j = i + 1; j < data.length; j++){
				if (data[j] < data[minIndex]) {
					 minIndex = j; // �������Ԫ��ֵС����Сֵ,��j��ֵ��minIndex  
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
