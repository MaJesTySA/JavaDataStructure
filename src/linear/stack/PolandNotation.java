package linear.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String expression = "1+((2+3)*4)-5";
        System.out.println("算术表达式: "+expression);
        System.out.println("中缀表达式List: "+infixExpToList(expression));
        System.out.println("后缀表达式List: "+infixExpToSuffixExp(infixExpToList(expression)));
        System.out.println("运算结果:"+calculate(infixExpToSuffixExp(infixExpToList(expression))));
    }

    //中缀表达式放入ArrayList中
    private static List<String> infixExpToList(String infxExp) {
        List<String> list = new ArrayList<>();
        int index = 0;
        String str;
        char c;
        do {
            //非数字直接加入
            if ((c = infxExp.charAt(index)) < 48 || (c = infxExp.charAt(index)) > 57) {
                list.add("" + c);
                index++;
            }
            //数字要考虑拼接
            else {
                str = "";
                while (index < infxExp.length() && (c = infxExp.charAt(index)) >= 48 && (c = infxExp.charAt(index)) <= 57) {
                    str += c;
                    index++;
                }
                list.add(str);
            }
        } while (index < infxExp.length());
        return list;
    }

    //逆波兰表达式放入到ArrayList中
    private static List<String> suffixExpToList(String suffixExp) {
        String[] split = suffixExp.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //中缀表达式List转后缀表达式List
    private static List<String> infixExpToSuffixExp(List<String> infixExp) {
        Stack<String> s1 = new Stack<>(); //符号栈
        // Stack<String> s2=new Stack<>(); //中间结果栈。由于该栈只进不出，而且需要逆序输出，用一个List来代替。
        List<String> s2 = new ArrayList<>();
        for (String item : infixExp) {
            //如果是一个数，直接入s2。
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈顶元素，并压入s2，直到遇到左括号位为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//还要将左括号弹出，不能丢
            } else {
                //若当前运算符优先级<=s1栈顶运算符，将s1栈顶弹出并压到s2，然后将新的栈顶与当前运算符比较
                while (s1.size() != 0 && comparePriority(item) <= comparePriority(s1.peek())){
                    s2.add(s1.pop());
                }
                //还需要将当前符号压入到s1
                s1.push(item);
            }
        }
        //遍历完毕后，将s1所有元素压入到s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }

    //逆波兰计算
    private static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            //数字入栈
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                //弹出两个数进行计算，算完再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException("运算符有误");
                }
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //优先级比较
    private static int comparePriority(String operator) {
        switch (operator) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                return 0;
        }
    }
}
