package recursionjdk8.treeDemo;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @program: selfplay
 * @description: 二叉树的方法
 * @author: zx
 * @create: 2018-08-15 10:43
 **/
public class TreeDemo {

    private  int lookup(String k, int defaultval, Treelo t) {
        if (t == null) return defaultval;
        if (k.equals(t.key)) return t.val;
        return lookup(k, defaultval,k.compareTo(t.key)<0?t.left:t.right);
    }



    public static Treelo update(String k, int newval, Treelo t) {
        if (t == null)
            t=new Treelo(k,newval,null,null);
        else if (k.equals(t.key))
            t.val = newval;
        else if (k.compareTo(t.key) < 0)
            t.left = update(k, newval, t.left);
        else
            t.right = update(k, newval, t.right);
        return t;
    }

    public static Treelo fupdate(String k, int newval, Treelo t) {
        return t==null?new Treelo(k,newval,null,null) :
                k.equals(t.key) ? new Treelo(k,newval,t.left,t.right) :
                        k.compareTo(t.key)<0?fupdate(k,newval,t.left):
                         fupdate(k,newval,t.right);

    }

}

