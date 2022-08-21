
import java.util.*;


class SortingAlgorithm {
    public static void main(String[] args) {
        
        int [] a = new int []  { -1, 5,10, -5, 3, 6, 44, 0, 2};
       
        //1.Quicksort 
        //Time Complexity::   Best: O(n log n) ; Average: O(n log n) ; Worst: O(n^2)
        //Space Complexity::  Worst: O(log n)
         quickSort(0, a.length -1, a );
        
        //2.Mergesort
        // Time Complexity::   Best: O(n log n) ; Average: O(n log n) ; Worst O(n log n)
        // Space Complexity:: Worst O(n)
         mergeSort(a, 0, a.length);
       
        //3.Heap Sort
        // Time Complexity::   Best: O(n log n) ; Average: O(n log n) ; Worst O(n log n)
        // Space Complexity:: Worst O(1)
         heapSortAsc(a);
        
        //4. Bubble Sort
        // Time Complexity:    Best: O(n) ; Average: O(n^2) ; Worst O(n^2)
        // Space Complexity:: Worst O(1)
        bubbleSort(a);
       
         //5.Insertion Sort
         // Time Complexity:    Best: O(n) ; Average: O(n^2) ; Worst O(n^2)
         // Space Complexity:: Worst O(1)
         insertionSort(a);
       
         //6.Selection Sort
         // Time Complexity:    Best: O(n^2) ; Average: O(n^2) ; Worst O(n^2)
         // Space Complexity:: Worst O(1)
         selectionSort(a);
       
        
        System.out.println(Arrays.toString(a));
       
    }
    
    public static void selectionSort(int [] a){
        int N =  a.length;
        
        for(int i = 0; i < N ; i++){
            
            int min = i;
            //Perform a linear search
            for(int j = i + 1; j < N; j++){
                if(a[j] < a[min]){
                    min =  j;
                }
            }
            //If min has changed swap
            if(i != min){
                //Swap
                int temp = a[i];
                a[i] =  a[min];
                a[min] = temp;
            }
        }
    }
    
    
    //Time Complexity: Best: O(n) Worst: O(n^2) Space Complexity: O(1)
    public static void insertionSort(int [] a){
        int N = a.length;
        for(int i = 1; i < N; i++){
            int temp = a[i];
            int j = i - 1;
            
            while(j >= 0 && a[j] > temp){
                a[j+1] = a[j];
                j--;
            }
            //Case where no elements greater than temp value
            a[j+1] = temp;
        }
        
    }
    
    //Time Complexity:  Best : O(n) Worst : O(n^2)
    public static void bubbleSort(int [] a){
        int N =  a.length;
        
        for(int i = 0; i < N; i++){
           
           int detectSwap = 0;
            for(int j = 0; j <  N - 1 - i; j++){
                
                if(a[j] > a[j + 1]){
                    //Swap
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    
                    detectSwap = 1;
                }
            }
            //If not swapping happened, the array is already sorted, break out of the loop
            if(detectSwap == 0){
                System.out.println("No Swapping happened");
                break;
            }
        }
    }
    
    //Time Complexity O(n log n)
    public static void heapSortAsc(int [] a){
        int N = a.length;
        //Build max Heap
        for(int i = N  / 2; i >= 0;i--){
            maxHeapify(a, N, i);
        }
        //Delete elements from heap
        for(int i = N - 1; i >=0; i--){
            //Swap Items
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            
            //Call maxheapify
            maxHeapify(a, i, 0);//We give i= 1 since deletion happens on root element
            
        }
    }
    
    //Time Complexity  O(n)
    public static void maxHeapify(int [] a, int N, int i){
        int largest = i; // represent the root element
        int left =  (2 * i) + 1;
        int right = (2 * i) + 2;
        
        while(left < N && a[left] > a[largest]){
            largest = left;
        }
        
        while(right < N &&  a[right] > a[largest]){
            largest = right;
        }
        
        //Check if the i has changed
        if(i != largest){
            //Swap
            int temp = a[largest];
            a[largest] = a[i];
            a[i] =  temp;
            
            //Continue with heapify
            maxHeapify(a, N, largest);
        }
    }
    
    
    public static void mergeSort(int [] nums, int low, int high){
        
        //Base case
        if(high - low == 1){
            return;
        }
        
        int mid = low + (high -  low ) /  2;
        
        //Sort left
        mergeSort(nums,low, mid);
        //Sort right
        mergeSort(nums, mid, high);
        
        //Merge result
        merge(nums, low, mid, high);
    }
    public static void merge(int [] nums, int low, int mid, int high){
        
        int [] merged = new int[high - low];
        int i = low;
        int j = mid;
        int k = 0;
        
        while(i <  mid && j < high){
            if(nums[i] < nums[j]){
                merged[k] = nums[i];
                i++;
            }else{
                merged[k] = nums[j];
                j++;
            }
            k++;
        }
        
        //Unequal partition
        while(i < mid){
            merged[k] =  nums[i];
            i++;
            k++;
        }
        
        while(j < high){
            merged[k] = nums[j];
            j++;
            k++;
        }
        
        //Copy merged to orginal array
        for(int  l = 0; l < merged.length; l++){
            nums[low + l] = merged[l];
        }
    }
    
    public static void  quickSort(int low, int high, int []  nums){
        
        if(low > high){
            return;
        }
    
        int start = low;
        int end = high;
        
        int pivotIndex =  low + (high - low) / 2;
        int pivot = nums[pivotIndex] ;
        
        while(start <= end){
            
            while(nums[start] < pivot){
                start++;
            }
            
            
            while(nums[end] > pivot){
                end--;
            }
            
            //Case there is violation , swap
            if(start <= end){
                //Swap
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                
                start++;
                end--;
            }
        }
        
        quickSort(low, end, nums);
        quickSort(start, high, nums);
        
    }
}
