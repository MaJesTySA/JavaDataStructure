package linear.stack;

public class CalculatorStack {
    public static void main(String[] args) {
        String expression = "11+2*6-3/3-5";
        CalcArrStack numStack = new CalcArrStack(10);
        CalcArrStack operatorStack = new CalcArrStack(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        boolean flag=false;
        //入栈
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (ch=='-' && index==0) flag=true;
            //如果是符号
            if (CalcArrStack.isOperator(ch)) {
                if (!operatorStack.isEmpty()) {
                    //当前优先级小于等于栈顶优先级，拿出来计算
                    if (CalcArrStack.priority(ch) <= CalcArrStack.priority(operatorStack.getTopElement())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        res = CalcArrStack.cal(num1, num2, operator);
                        //运算结果入数栈
                        numStack.push(res);
                        /*
                            解决3-2*6+2的问题
                            为什么 3-12+2不会出错，3-2*6+2会出错？
                            韩老师的逻辑如下：
                            对于3-12+2：假设当前数字栈为[3 12]，符号栈为[-]，此时+准备入栈，由于优先级相等，先把-拿出来计算，得-9，+再入栈，
                                        此时数字栈为[-9]，符号栈为[+]。2入栈，数字栈[-9 2]，符号栈[+]，进行出栈操作，结果为-7正确。
                            对于3-2*6+2：假设当前数字栈为[3 2 6]，符号栈为[- *]，此时+准备入栈，由于优先级低，先把*拿出来计算，得12，···+再入栈···，
                                        此时数字栈为[3 12]，···符号栈为[- +]···（问题就出在这里，此时应该继续计算）。
                                        接着2入栈，数字栈[3 12 2]，符号栈[- +]，出栈操作，2+12=14,3-14=-11，结果出错。
                             所以，解决方法就是，当前符号优先级小于等于栈顶符号的时候，算完之后，在当前符号入栈之前，再进行一次判断。
                         */
                        //符号栈为空，直接加入，不涉及-+顺序问题
                        if (operatorStack.isEmpty()){
                            operatorStack.push(ch);
                        //不为空则要处理上述问题
                        }else{
                            if (CalcArrStack.priority(ch) <= CalcArrStack.priority(operatorStack.getTopElement())){
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                operator = operatorStack.pop();
                                res = CalcArrStack.cal(num1, num2,operator);
                                //运算结果入数栈
                                numStack.push(res);
                                operatorStack.push(ch);
                            }else{
                                operatorStack.push(ch);
                            }
                        }
                    } else {
                        //大于栈顶优先级，直接入符号栈
                        operatorStack.push(ch);
                    }
                } else {
                    //符号栈为空，直接入栈
                    operatorStack.push(ch);
                }
            } else {
                // 处理多位数，还要进行判断，后一位是符号才入栈
                // numStack.push(ch - 48);
                keepNum += ch;
                //ch是最后一位，不需要判断
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (CalcArrStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //处理第一位是负数的情况，就将第一个数字设为负数。
                        if (flag) {
                            int reverseNum=numStack.pop();
                            numStack.push(0-reverseNum);
                            operatorStack.pop();
                            flag=false;
                        }
                        keepNum = "";
                    }
                }

            }
            //让index+1，并判断是否扫描到表达式最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //出栈计算
        while (true) {
            //如果符号栈为空，则计算完毕
            if (operatorStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            res = CalcArrStack.cal(num1, num2, operator);
            numStack.push(res);
        }
        System.out.printf("表达式%s = %d", expression, numStack.pop());
    }

}

class CalcArrStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public int getTop() {
        return top;
    }

    public CalcArrStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //返回栈顶
    public int getTopElement() {
        return stack[top];
    }

    //遍历
    public void show() {
        if (isEmpty()) {
            System.out.println("空栈");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }


    //返回运算符优先级，数字越大，优先级越高
    public static int priority(int operator) {
        if (operator == '*' || operator == '/')
            return 1;
        else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否是运算符
    public static boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    //计算方法
    public static int cal(int num1, int num2, int operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
