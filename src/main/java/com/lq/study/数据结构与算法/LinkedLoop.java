package com.lq.study.数据结构与算法;

/**
 * 链表如何判断有环？
 *
 * @author LQ
 * @date 2020/08/06 22:41
 */
public class LinkedLoop {
    public static void main(String[] args) throws Exception {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        System.out.println(isCycle(node1));
    }

    /**
     * 判断是否有环
     *
     * @param head 链表头
     * @return
     */
    public static Integer isCycle(Node head) {
        int num = 0;
        int temp = 0;

        Node p1 = head;
        Node p2 = head;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                // return true;
                num++;
                System.out.println(num + "相遇");
                if (num == 2) {
                    return temp;
                }
            }
            if (num == 1) {
                temp++;
            }
        }
        //return false;
        return temp;
    }

    /**
     * 链表节点
     */
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }


}
