# RedBlackTree 

代码：
```java
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/*
API:
无序符号表：
    // void put(K key, V value)
    // V get(K key)
    // void delete(K key)
    // void clear()
    // boolean contains(K key)
    // boolean isEmpty()
    // int size()
    // Set<K> keys()
有序符号表：
    // K min()
    // K max()
    // K floor(K key)
    // K ceiling(K key)
    // int rank(K key)
    // K select(int k)
    // void deleteMin()
    // void deleteMax()
    // int size(K low, K high)
    // Set<K> keys(K low, K high)
 */
public class RedBlackTree<K extends Comparable<? super K>, V> {
    // 常量
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    // 属性
    private TreeNode root;

    private class TreeNode {
        K key;
        V value;
        boolean color;
        int size; //这棵树包含结点的个数
        TreeNode left;
        TreeNode right;

        public TreeNode(K key, V value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    /***********************************************************
     *                Unordered table methods
     ***********************************************************/
    /**
     * 获取键关联的值
     *
     * @param key 键
     * @return 键关联的值
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        TreeNode x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    /**
     * 判断红黑树中是否包含指定的键
     *
     * @param key 键
     * @return 如果包含返回true，否则返回false
     */
    public boolean contains(K key) {
        return get(key) != null;
    }

    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value cannot be null.");
        }
        root = put(root, key, value);
        root.color = BLACK;
        check();
    }

    private TreeNode put(TreeNode x, K key, V value) {
        // 在底端插入
        if (x == null) return new TreeNode(key, value, RED, 1);
        // 自顶向下分解4-node
        // if (isRed(x.left) && isRed(x.right)) flipColors(x);
        // 查找过程
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        // 自底向上修复
        return fixUp(x);
    }

    public void delete(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        if (root == null) throw new NoSuchElementException("The tree is Empty!");
        if (!contains(key)) return;
        // key一定存在, 需要删除结点
        if (!isRed(root.left)) root.color = RED;
        root = delete(root, key);
        if (root != null) root.color = BLACK;
        check();
    }

    private TreeNode delete(TreeNode x, K key) {
        if (key.compareTo(x.key) < 0) {
            // 如果左孩子是2-node, 就需要从右孩子中借结点
            if (!isRed(x.left) && !isRed(x.left.left)) {
                x = moveRedLeft(x);
            }
            // 往左走
            x.left = delete(x.left, key);
        } else {
            // 右旋左倾的红色链接
            if (isRed(x.left)) x = rotateRight(x);
            // 在底端删除结点
            if (key.compareTo(x.key) == 0 && x.right == null) return null;
            // 如果右孩子是2-node，就需要从左孩子借结点过去
            if (!isRed(x.right) && !isRed(x.right.left)) {
                x = moveRedRight(x);
            }
            if (key.compareTo(x.key) == 0) {
                // 找右孩子的最小结点替换当前结点
                TreeNode minOfRight = min(x.right);
                x.key = minOfRight.key;
                x.value = minOfRight.value;
                // 在右子树中删除最小结点
                x.right = deleteMin(x.right);
            } else x.right = delete(x.right, key);
        }
        // 自底向上修复
        return fixUp(x);
    }

    /**
     * 获取红黑树中键值对的个数
     *
     * @return 键值对的个数
     */
    public int size() {
        return size(root);
    }

    /**
     * 判断红黑树是否为空
     *
     * @return 如果红黑树为空返回true, 否则返回false
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 清空所有键值对
     */
    public void clear() {
        root = null;
    }

    /**
     * 获取键的集合
     * @return 键的集合
     */
    public Set<K> keys() {
        if (isEmpty()) return new LinkedHashSet<>();
        return keys(min(), max());
    }
    /***********************************************************
     *                Ordered table methods
     ***********************************************************/
    /**
     * 获取红黑树中最小的键
     *
     * @return 最小的键
     */
    public K min() {
        if (isEmpty()) throw new NoSuchElementException("The tree is Empty!");
        return min(root).key;
    }

    private TreeNode min(TreeNode node) {
        TreeNode x = node;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /**
     * 获取红黑树中最大的键
     *
     * @return 最大的键
     */
    public K max() {
        if (isEmpty()) throw new NoSuchElementException("The tree is Empty!");
        return max(root).key;
    }

    private TreeNode max(TreeNode node) {
        TreeNode x = node;
        while (x.right != null) x = x.right;
        return x;
    }

    /**
     * 删除最大结点
     */
    public void deleteMax() {
        if (root == null) throw new NoSuchElementException("The tree is Empty!");
        // 如果根结点是2-node
        if (!isRed(root.left)) root.color = RED;
        root = deleteMax(root);
        if (root != null) root.color = BLACK;
        check();
    }

    private TreeNode deleteMax(TreeNode x) {
        // 右旋左倾的红色连接
        if (isRed(x.left)) x = rotateRight(x);
        // 如果x就是最大的结点,直接删除
        if (x.right == null) return null;
        // 先判断右孩子是不是2-node
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        // 往右走
        x.right = deleteMax(x.right);
        return fixUp(x);
    }


    /**
     * 删除最小结点
     */
    public void deleteMin() {
        if (root == null) throw new NoSuchElementException("The tree is Empty!");
        // 如果根结点是2-node
        if (!isRed(root.left)) root.color = RED;
        root = deleteMin(root);
        if (root != null) root.color = BLACK;
        check();
    }

    private TreeNode deleteMin(TreeNode x) {
        // 删除最小结点
        if (x.left == null) return null;
        // 判断左孩子是不是2-node，必要的时候需要从右孩子中借结点过去
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        // 往左走
        x.left = deleteMin(x.left);
        // 自底向上进行修复
        return fixUp(x);
    }

    /**
     * 获取小于等于key的最大键
     *
     * @param key 键
     * @return 小于等于key的最大键
     */
    public K floor(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
        TreeNode x = floor(root, key);
        if (x != null) return x.key;
        return null;
    }

    private TreeNode floor(TreeNode x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        // cmp > 0, x就是备选方法
        TreeNode t = floor(x.right, key);
        if (t != null) return t;
        return x;
    }

    /**
     * 获取大于等于key的最小键
     *
     * @param key 键
     * @return 大于等于key的最小键
     */
    public K ceiling(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        TreeNode x = ceiling(root, key);
        if (x != null) return x.key;
        return null;
    }

    private TreeNode ceiling(TreeNode x, K key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        // cmp < 0, x就是备选方案
        TreeNode t = ceiling(x.left, key);
        if (t != null) return t;
        return x;
    }

    /**
     * 获取key在树中排名
     *
     * @param key 键
     * @return key在树中排名
     */
    public int rank(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null.");
        return rank(root, key);
    }

    private int rank(TreeNode x, K key) { // rank：小于key的元素个数
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return size(x.left);
        if (cmp < 0) return rank(x.left, key);
        // cmp > 0
        return size(x.left) + 1 + rank(x.right, key);
    }

    /**
     * 获取排名为 k 的键
     *
     * @param k 排名
     * @return 排名为k的键
     */
    public K select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("k=" + k + ", size=" + size());
        }
        return select(root, k).key;
    }

    private TreeNode select(TreeNode x, int k) {
        if (x == null) return null;
        int rank = size(x.left);
        if (k == rank) return x;
        if (k < rank) return select(x.left, k);
        // k > rank
        return select(x.right, k - rank - 1);
    }

    /**
     * 获取大于等于low,小于等于high键值对的个数
     * @param low 下界
     * @param high 上界
     * @return 大于等于low,小于等于high键值对的个数
     */
    public int size(K low, K high) {
        if (low == null || high == null) {
            throw new IllegalArgumentException("Low or high cannot be null.");
        }
        if (low.compareTo(high) > 0) {
            throw new IllegalArgumentException("Low cannot greater than high.");
        }
        int k1 = rank(low); // 小于low的元素个数
        int k2 = rank(high); // 小于high的元素个数
        if (contains(high)) return k2 - k1 + 1;
        return k2 - k1;
    }

    /**
     * 获取小于等于low,大于等于high键的集合
     * @param low 下界
     * @param high 上界
     * @return 小于等于low,大于等于high键的集合
     */
    public Set<K> keys(K low, K high) {
        if (low == null || high == null) {
            throw new IllegalArgumentException("Low or high cannot be null.");
        }
        if (low.compareTo(high) > 0) {
            throw new IllegalArgumentException("Low cannot greater than high.");
        }
        Set<K> set = new LinkedHashSet<>();
        keys(root, low, high, set);
        return set;
    }

    private void keys(TreeNode x, K low, K high, Set<K> set) {
        if (x == null) return ;
        int cmplo = x.key.compareTo(low);
        int cmphi = x.key.compareTo(high);
        // 遍历左子树(剪枝)
        if (cmplo > 0) keys(x.left, low, high, set);
        // 遍历根结点
        if (cmplo >= 0 && cmphi <= 0) set.add(x.key);
        // 遍历右子树(剪枝)
        if (cmphi < 0) keys(x.right, low, high, set);
    }

    /***********************************************************
     *                Helper methods
     ***********************************************************/
    private boolean isRed(TreeNode x) {
        if (x == null) return false;
        return x.color;
    }

    private int size(TreeNode x) {
        if (x == null) return 0;
        else return x.size;
    }

    private TreeNode rotateLeft(TreeNode h) {
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        // 修改颜色
        x.color = h.color;
        h.color = RED;
        // 修改size
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private TreeNode rotateRight(TreeNode h) {
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        // 修改颜色
        x.color = h.color;
        h.color = RED;
        // 修改size
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(TreeNode x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private TreeNode moveRedRight(TreeNode x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private TreeNode moveRedLeft(TreeNode x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private TreeNode fixUp(TreeNode x) {
        if (isRed(x.right)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right)) flipColors(x);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**********************************************************
     *                     Check methods
     **********************************************************/
    private boolean check() {
        boolean is23 = is23();
        if (!is23) System.err.println("The tree is not 23!");
        boolean isBalanced = isBalanced();
        if (!isBalanced) System.err.println("The tree is not balanced!");
        return is23 && isBalanced;
    }

    private boolean is23() {
        return !isRed(root) && is23(root);
    }

    private boolean is23(TreeNode x) {
        if (x == null) return true;
        if (isRed(x.right)) return false;
        if (isRed(x.left) && isRed(x.left.left)) return false;
        return is23(x.left) && is23(x.right);
    }

    private boolean isBalanced() {
        // 先求取根结点到最左边叶子结点的黑高
        int black = 0;
        TreeNode x = root;
        while (x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        // 验证所有从根结点到叶子结点的黑高是不是和black相等
        return isBalanced(root, black);
    }

    private boolean isBalanced(TreeNode x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    public static void main(String[] args) {
        RedBlackTree<Character, String> tree = new RedBlackTree<>();
        String value = "刘亦菲";
        tree.put('A', value);
        tree.put('S', value);
        tree.put('E', value);
        tree.put('R', value);
        tree.put('C', value);
        tree.put('D', value);
        tree.put('I', value);
        tree.put('N', value);
        tree.put('B', value);
        tree.put('X', value);

        // V get(K key), boolean contains(K key)
        /*System.out.println(tree.get('D'));
        System.out.println(tree.get('0'));
        System.out.println(tree.contains('D'));
        System.out.println(tree.contains('0'));*/

        /*System.out.println(tree.size());
        System.out.println(tree.isEmpty());
        tree.clear();
        System.out.println(tree.size());
        System.out.println(tree.isEmpty());*/

        // void deleteMax()
        /*System.out.println(tree.size());
        System.out.println(tree.contains('X'));
        tree.deleteMax();
        System.out.println(tree.size());
        System.out.println(tree.contains('X'));
        tree.clear();
        tree.deleteMax();*/

        // void deleteMin()
        /*System.out.println(tree.size());
        System.out.println(tree.contains('A'));
        tree.deleteMin();
        System.out.println(tree.size());
        System.out.println(tree.contains('A'));
        tree.clear();
        tree.deleteMin();*/

        // void delete(K key)
        /*System.out.println(tree.size());
        System.out.println(tree.contains('B'));
        tree.delete('B');
        System.out.println(tree.size());
        System.out.println(tree.contains('B'));

        System.out.println(tree.size());
        System.out.println(tree.contains('C'));
        tree.delete('C');
        System.out.println(tree.size());
        System.out.println(tree.contains('C'));

        System.out.println(tree.size());
        System.out.println(tree.contains('E'));
        tree.delete('E');
        System.out.println(tree.size());
        System.out.println(tree.contains('E'));

        System.out.println(tree.size());
        System.out.println(tree.contains('0'));
        tree.delete('0');
        System.out.println(tree.size());
        System.out.println(tree.contains('0'));*/

        /*System.out.println(tree.min()); // A
        System.out.println(tree.max()); // X*/

        // K floor(K key), K ceiling(K key)
        /*System.out.println(tree.floor('D'));
        System.out.println(tree.floor('F'));
        System.out.println(tree.floor('0'));*/

        /*System.out.println(tree.ceiling('D'));
        System.out.println(tree.ceiling('M'));
        System.out.println(tree.ceiling('Z'));*/

        // int rank(K key), K select(int k)
        /*System.out.println(tree.rank('A'));
        System.out.println(tree.rank('X'));
        System.out.println(tree.rank('0'));
        System.out.println(tree.rank('F'));
        System.out.println(tree.rank('Z'));*/

        /*System.out.println(tree.select(1)); // B
        System.out.println(tree.select(9)); // X
        System.out.println(tree.select(4)); // E*/

        // int size(K low, K high)
        /*System.out.println(tree.size('C', 'N'));
        System.out.println(tree.size('A', 'X'));*/

        // Set<K> keys(K low, K high)
        // System.out.println(tree.keys('C', 'N'));

        // Set<K> keys()
        // System.out.println(tree.keys());
        /*tree.clear();
        System.out.println(tree.keys());*/
    }
}



```