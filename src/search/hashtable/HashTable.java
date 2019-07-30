package search.hashtable;

import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("quit: 退出");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("id");
                    int id = scanner.nextInt();
                    System.out.println("name");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    int findId = scanner.nextInt();
                    hashTable.find(findId);
                    break;
                case "quit":
                    scanner.close();
                    System.exit(0);
            }
        }
    }
}

class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmpLinkedList {
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp current = head;
        while (true) {
            if (current.next == null) {
                break;
            }
            current = current.next;
        }
        current.next = emp;
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "条链表为空");
            return;
        }
        Emp current = head;
        System.out.println("第" + no + "条链表");
        while (true) {
            System.out.printf("=> id=%d name=%s\t", current.id, current.name);
            if (current.next == null) {
                break;
            }
            current = current.next;
        }
        System.out.println();
    }

    public Emp find(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp current = head;
        while (true) {
            if (current.id == id) {
                break;
            }
            if (current.next == null) {
                current = null;
                break;
            }
            current = current.next;
        }
        return current;
    }
}

class HashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size;

    public HashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //初始化每一个链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        //根据员工id得到插入的位置
        int empLinkedListPos = hashFunc(emp.id);
        empLinkedLists[empLinkedListPos].add(emp);

    }

    //遍历
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    //散列函数
    private int hashFunc(int id) {
        return id % size;
    }

    //查找
    public void find(int id) {
        int pos = hashFunc(id);
        Emp emp = empLinkedLists[pos].find(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到雇员id=%d\n", pos + 1, id);
        } else {
            System.out.println("没有找到相关信息");
        }
    }
}
