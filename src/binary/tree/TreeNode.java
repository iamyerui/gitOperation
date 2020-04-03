package binary.tree;

import java.util.ArrayDeque;
import java.util.Queue;

class TreeNode {
    String val;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(String _val) {
        this.val = _val;
    }



    // 获取最大深度
    public static int getMaxDepth(TreeNode root) {
        if (root == null)
            return 0;
        else {
            int left = getMaxDepth(root.left);
            int right = getMaxDepth(root.right);
            return 1 + Math.max(left, right);
        }
    }

    // 获取最大宽度
    public static int getMaxWidth(TreeNode root) {
        if (root == null)
            return 0;

        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        int maxWitdth = 1; // 最大宽度
        queue.add(root); // 入队

        while (true) {
            int len = queue.size(); // 当前层的节点个数
            if (len == 0)
                break;
            while (len > 0) {// 如果当前层，还有节点
                TreeNode t = queue.poll();
                len--;
                if (t.left != null)
                    queue.add(t.left); // 下一层节点入队
                if (t.right != null)
                    queue.add(t.right);// 下一层节点入队
            }
            maxWitdth = Math.max(maxWitdth, queue.size());
        }
        return maxWitdth;
    }

    public static void main(String[] args) {


        TreeNode childTwoleft = new TreeNode("c1");
        TreeNode childOneright = new TreeNode("b2");

        TreeNode childOneleft = new TreeNode("b1");
        childOneleft.left = childTwoleft;

        TreeNode root = new TreeNode("a1");
        root.left = childOneleft;
        root.right = childOneright;

        System.out.println(getMaxDepth(root));
        System.out.println(getMaxWidth(root));

    }
}