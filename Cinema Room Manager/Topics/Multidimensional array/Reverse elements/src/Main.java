
class ArrayOperations {
    public static void reverseElements(int[][] twoDimArray) {
        int n = twoDimArray.length;
        int m = twoDimArray[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                int tempDown = twoDimArray[i][j];
                int tempUp = twoDimArray[i][m - j - 1];
                twoDimArray[i][j] = tempUp;
                twoDimArray[i][m - j - 1] = tempDown;
            }
        }
    }
}