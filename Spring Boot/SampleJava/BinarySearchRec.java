class BinarySearchRec {

    // Returns index of x if it is present in arr[l..
    // r], else return -1
    int binarySearch(int arr[], int stIdx, int edIdx, int x)
    {
        if (edIdx >= stIdx) {
            int mid = stIdx + (edIdx - stIdx) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid] == x)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, stIdx, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, edIdx, x);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }

    // main function
    public static void main(String args[])
    {
        BinarySearchRec ob = new BinarySearchRec();

        int arr[] = { 2, 3, 4, 10, 40 };
        int n = arr.length;
        int x = 10;
        int result = ob.binarySearch(arr, 0, n - 1, x);

        if (result == -1)
            System.out.println(
                    "Element is not present in array");
        else
            System.out.println(
                    "Element is present at index " + result);
    }
}