package core.testLinkList;

public class SingleLinkedList {
    private int size;//链表节点的个数
    private Node head;//头节点
    
    public SingleLinkedList(){
        size = 0;
        head = null;
    }
    
    //链表的每个节点类
    private class Node{
        private Object data;//每个节点的数据
        private Node next;//每个节点指向下一个节点的连接
        
        public Node(Object data){
            this.data = data;
        }
    }
    
    //在链表头添加元素
    public Object addHead(Object obj){
        Node newHead = new Node(obj);
        if(size == 0){
            head = newHead;
        }else{
            newHead.next = head;
            head = newHead;
        }
        size++;
        return obj;
    }
    
    //在链表头删除元素
    public Object deleteHead(){
        Object obj = head.data;
        head = head.next;
        size--;
        return obj;
    }
    
    //查找指定元素，找到了返回节点Node，找不到返回null
    public Node find(Object obj){
        Node current = head;
        int tempSize = size;
        while(tempSize > 0){
            if(obj.equals(current.data)){
                return current;
            }else{
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }
    
    //删除指定的元素，删除成功返回true
    public boolean delete(Object value){
        if(size == 0){
            return false;
        }
        Node current = head;
        Node previous = head;
        while(current.data != value){
            if(current.next == null){
                return false;
            }else{
                previous = current;
                current = current.next;
            }
        }
        //如果删除的节点是第一个节点
        if(current == head){
            head = current.next;
            size--;
        }else{//删除的节点不是第一个节点
            previous.next = current.next;
            size--;
        }
        return true;
    }
    
    //判断链表是否为空
    public boolean isEmpty(){
        return (size == 0);
    }
    
    //显示节点信息
    public void display(){
        if(size >0){
            Node node = head;
            int tempSize = size;
            if(tempSize == 1){//当前链表只有一个节点
                System.out.println("["+node.data+"]");
                return;
            }
            while(tempSize>0){
                if(node.equals(head)){
                    System.out.print("["+node.data+"->");
                }else if(node.next == null){
                    System.out.print(node.data+"]");
                }else{
                    System.out.print(node.data+"->");
                }
                node = node.next;
                tempSize--;
            }
            System.out.println();
        }else{//如果链表一个节点都没有，直接打印[]
            System.out.println("[]");
        }
    }
    
    public void reverseLinkList() {  //逆序输出链表的所有内容  
        if (head == null || head.next == null) {  //当链表只有一个头节点或者只有一个结点，逆序还是原来的链表，所以直接返回  
            return;  
        } else {  
            Node p = head.next;  
            Node q = head.next.next;  
            p.next=null;//将第一个结点的next置为空，否则会出现一个环  
            Node temp = null;  
            while (q != null) {  
                temp = q.next;  
                q.next=p;  
                p = q;  
                q = temp;  
            }  
            if (q == null) {  
                head.next=p;  
                q = null;             
            }  
        }         
    }
    
    
    public static SingleLinkedList initData(){
    	SingleLinkedList singleList = new SingleLinkedList();
        singleList.addHead("A");
        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        singleList.addHead("E");
        singleList.addHead("F");
        singleList.addHead("G");
        singleList.addHead("H");
        singleList.addHead("I");
        singleList.addHead("J");
        return singleList;
    }
    
    public static void main(String[] args) {
    	
    	SingleLinkedList singleList =initData();
        //打印当前链表信息
        singleList.display();
        singleList.reverseLinkList();
        singleList.display();
	}

}