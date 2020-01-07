import java.lang.reflect.Array;
import java.util.*;

public class Sudoku {

    static Map<String, List<String>> map1=new HashMap();
    static Map<String, List<String>> map2=new HashMap();
    public static void main(String[] args) {
        // write your com.company.code here
        int[][] sudoku={
                {0,5,0,0,0,0,0,1,0},
                {6,0,0,0,7,0,2,0,5},
                {4,0,3,0,1,0,0,0,0},
                {0,9,0,0,8,0,0,0,0},
                {7,0,5,0,0,6,0,0,8},
                {0,6,0,0,0,7,5,0,0},
                {0,1,8,0,0,0,9,5,0},
                {0,0,0,0,9,0,6,0,0},
                {9,0,0,4,0,0,0,8,7},
        };
        init(sudoku);
        while(!map1.isEmpty()){
            for (Map.Entry<String, List<String>> entry:map1.entrySet()) {
                String value=entry.getValue().get(0);
                String row=entry.getKey().substring(0,1);
                String col=entry.getKey().substring(2,3);
                //去除行
                removeRow(row,value);
                //去除列
                removeCol(col,value);
                //去除格
                removeRowCol(row,col,value);
            }
            map1.clear();
            for (Map.Entry<String, List<String>> entry:map2.entrySet()) {
                if (entry.getValue().size()==1){
                    map1.put(entry.getKey(),entry.getValue());
                    String value=entry.getValue().get(0);
                    String row=entry.getKey().substring(0,1);
                    String col=entry.getKey().substring(2,3);
                    sudoku[Integer.parseInt(row)][Integer.parseInt(col)]=Integer.parseInt(value);
                    System.out.println("change key"+entry.getKey());
                    System.out.println("change value"+entry.getValue().get(0));
                }
            }
            for (String key:map1.keySet()) {
                map2.remove(key);
            }
            if (map1.isEmpty()){
                for (Map.Entry<String, List<String>> entry:map2.entrySet()) {
                    boolean flag=false;
                    String row=entry.getKey().substring(0,1);
                    String col=entry.getKey().substring(2,3);
                    for (String value:entry.getValue()) {
                        if(checkRow(row,value)){
                            List tem=new ArrayList();
                            tem.add(value);
                            map1.put(entry.getKey(),tem);
                            sudoku[Integer.parseInt(row)][Integer.parseInt(col)]=Integer.parseInt(value);
                            System.out.println("change "+entry.getKey());
                            System.out.println("change value: "+value);
                            flag=true;
                            break;
                        };
                        if(checkCol(col,value)){
                            List tem=new ArrayList();
                            tem.add(value);
                            map1.put(entry.getKey(),tem);
                            sudoku[Integer.parseInt(row)][Integer.parseInt(col)]=Integer.parseInt(value);
                            System.out.println("change "+entry.getKey());
                            System.out.println("change value: "+value);
                            flag=true;
                            break;
                        };
//                        checkRowCol(row,col,value);
                    }
                    if (flag){
                        break;
                    }
                }
                for (String key:map1.keySet()) {
                    map2.remove(key);
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            System.out.print("{");
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j]+",");
            }
            System.out.println("},");
        }
        System.out.println();
    }

    //判断是否为列独有
    private static boolean checkCol(String col, String value) {
        int count=0;
        for (int j = 0; j < 9; j++) {
            if (map2.containsKey(j+","+col)){
                if (map2.get(j+","+col).contains(value)){
                    count++;
                }
            }
        }
        if (count==1){
            return true;
        }
        return false;
    }
    //判断值是否为本行独有
    private static boolean checkRow(String row, String value) {
        int count=0;
        for (int j = 0; j < 9; j++) {
            if (map2.containsKey(row+","+j)){
                if (map2.get(row+","+j).contains(value)){
                    count++;
                }
            }
        }
        if (count==1){
            return true;
        }
        return false;
    }
    //    删除一个格子中重复的数字
    private static void removeRowCol(String row, String col, String value) {
        int rowint=Integer.parseInt(row)/3*3;
        int colint=Integer.parseInt(col)/3*3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map2.get((rowint + i) +","+ (colint + j))==null){
                    continue;
                }
                map2.get((rowint + i) +","+ (colint + j)).remove(value);
            }
        }
    }
    //删除一列中重复的数字
    private static void removeCol(String col, String value) {
        for (int i = 0; i < 9; i++) {
            if (map2.get(i+","+col)==null){
                continue;
            }
            map2.get(i+","+col).remove(value);
        }
    }
    //删除一行中重复的数字
    private static void removeRow(String row, String value) {
        for (int i = 0; i < 9; i++) {
            if (map2.get(row+","+i)==null){
                continue;
            }
            map2.get(row+","+i).remove(value);
        }
    }

    private static void init(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j]!=0){
                    List list=new ArrayList();
                    list.add(String.valueOf(sudoku[i][j]));
                    map1.put(i+","+j,list);
                }else{
                    List list=new ArrayList(){};
                    list.add("1");
                    list.add("2");
                    list.add("3");
                    list.add("4");
                    list.add("5");
                    list.add("6");
                    list.add("7");
                    list.add("8");
                    list.add("9");
                    map2.put(i+","+j,list);
                }
            }
        }
    }


}
