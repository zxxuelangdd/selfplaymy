package recursionjdk8.treeDemo;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-08-15 11:13
 **/

public class Treelo {
    public String key;
    public int val;
    public Treelo left, right;

    public Treelo(String k, int v, Treelo l, Treelo r) {
        this.key = k;
        this.val = v;
        this.left = l;
        this.right = r;
    }
}
