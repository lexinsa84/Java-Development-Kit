package homework3.task2;

public class Main {
    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3};
        Integer[] arr3 = {1, 2, 4};

        String[] str1 = {"a", "b", "c"};
        String[] str2 = {"a", "b", "c"};
        String[] str3 = {"a", "b", "d"};

        System.out.println(CompArr.compareArrays(arr1, arr2));
        System.out.println(CompArr.compareArrays(arr1, str3));
        System.out.println(CompArr.compareArrays(str1, str2));
        System.out.println(CompArr.compareArrays(str1, arr3));
    }
}
