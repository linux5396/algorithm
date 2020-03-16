package bytedance.listntree;

/**
 * @author linxu
 * @date 2020/3/15
 * <tip>take care of yourself.everything is no in vain.</tip>
 * 复制带随机指针的链表
 */
public class RandomList {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        //step1 double list A->B->C  to A->A1->B->B1->C->C1
        Node p = head;
        while (p != null) {
            Node newNode = newNode(p);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }
        Node p1 = head;
        //step2 copy random
        while (p1 != null) {
            p1.next.random = (p1.random != null) ? p1.random.next : null;
            p1 = p1.next.next;
        }
        //step 3 spilt double list A->A1->B->B1->C->C1 to A1->B1->C1
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        //保存一下新链条的头节点
        Node headBackup = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = (ptr_old_list.next != null) ? ptr_old_list.next.next : null;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return headBackup;
    }

    private Node newNode(Node replication) {
        if (replication != null) {
            return new Node(replication.val);
        }
        return null;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
