public class Main{
        public static int isRotatedSorted(String[] books) {
            int n = books.length;

            // If the array is empty or has only one book, it is trivially sorted
            if (n == 0 || n == 1) {
                return 1;
            }

            // Find the point where the rotation occurred
            int rotationIndex = -1;

            // Check the number of points where the order is not in non-decreasing order
            int breakCount = 0;
            for (int i = 0; i < n; i++) {
                if (books[i].compareTo(books[(i + 1) % n]) > 0) {
                    breakCount++;
                    if (breakCount > 1) {
                        // More than one break point means it's not a single rotation from sorted order
                        return 0;
                    }
                    rotationIndex = (i + 1) % n;
                }
            }

            // If there are no break points, it means the array was not rotated
            if (breakCount == 0) {
                return 1;
            }

            // If there's exactly one break point, check if the array can be rotated
            // to match the sorted order
            if (breakCount == 1) {
                return (isSorted(books, rotationIndex)) ? 1 : 0;
            }

            return 0;
        }

        // Check if the array is sorted in non-decreasing order starting from rotationIndex
        private static boolean isSorted(String[] books, int rotationIndex) {
            int n = books.length;
            for (int i = 0; i < n - 1; i++) {
                if (books[(rotationIndex + i) % n].compareTo(books[(rotationIndex + i + 1) % n]) > 0) {
                    return false;
                }
            }
            return true;
        }

        public static void main(String[] args) {
            String[] books1 = {"a", "b", "c", "d", "e"}; // Originally sorted and not rotated
            String[] books2 = {"c", "d", "e", "a", "b"}; // Originally sorted and rotated by 2 positions
            String[] books3 = {"c", "d", "a", "b", "e"}; // Not sorted even if rotated

            System.out.println(isRotatedSorted(books1)); // Output: 1
            System.out.println(isRotatedSorted(books2)); // Output: 1
            System.out.println(isRotatedSorted(books3)); // Output: 0
        }
    }

