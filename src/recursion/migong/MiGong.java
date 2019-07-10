package recursion.migong;

public class MiGong {
    public static void main(String[] args) {
        //二维数组，模拟迷宫
        int[][] map = new int[8][7];
        generateMap(map);
        showMap(map);
        System.out.println();
        findWay(map,1,1);
        showMap(map);
    }

    private static void showMap(int[][] map) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void generateMap(int[][] map) {
        //1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
    }

    /**
     * @param map
     * @param i   从哪个位置开始找
     * @param j
     * @return 成功条件：如果能达到map[6][5]（最右下角）。则能找到路径。0表示还未走过；2表示行走的路径；3表示已经走过，走不通。
     * 策略：下->右->上->左
     */
    private static boolean findWay(int[][] map, int i, int j) {
        //终止条件
        if (map[6][5] == 2) {
            return true;
        } else {
            //按照策略走
            if (map[i][j] == 0) {
                //假定该点能走通
                map[i][j] = 2;
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    return true;
                } else {
                    //否则就是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //map[i][j]!=0，可能是1,2,3。
                return false;
            }
        }
    }
}
