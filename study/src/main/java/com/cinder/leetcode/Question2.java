package com.cinder.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Cinder
 * @Description:
 * @Date create in 15:02 2020/5/21/021
 * @Modified By:
 */
public class Question2 {
    public static void main(String[] args) {
        addTwoNumbers(null,null);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre,p = l1,q = l2;
        int carry = 0;         //进位
        while(p != null || q != null){
            int val1 = p == null ? 0 : p.val;
            int val2 = q == null ? 0 : q.val;
            int sum = val1+val2+carry;
            carry = (sum > 9)? 1:0;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            p = p == null ? p : p.next;
            q = q == null ? q : q .next;
        }
        if(carry > 0)
            cur.next = new ListNode(1);
        return pre.next;
    }


static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
}
