package sohu.thread;

import java.util.Arrays;

/**
 * 输入一个有序数组和一个数,
 * 若在数组中返回索引,
 * 若不在返回应该插入的索引
 * @author hp
 *
 */
public class ArrayIndex {
	
	public static void main(String[] args){
		int[] a = new int[]{1, 2, 4, 5};
		System.out.println(getIndex(a, 3));
	}
	
	public static int getIndex(int[] array, int value){
		//value 在中
		for(int i = 0; i < array.length; i++){
			if(value == array[i]){
				return i;
			}
		}
		
		//value 不在数组中
		for(int i = 0; i < array.length - 1; i++){
			if(value < array[0]){
				return 0;
			}else if(value > array[array.length - 1]){
				return array.length;
			}else if(array[i] < value && value < array[i+1]){
				//移动
				/*int[] newArray = Arrays.copyOf(array, array.length + 1);
				for(int j = newArray.length - 2; j > i; j--){
					newArray[j + 1] = newArray[j]; 
				}
				newArray[i + 1] = value;*/
				return i + 1;
			}
		}
		
		return -1;
	}

}
