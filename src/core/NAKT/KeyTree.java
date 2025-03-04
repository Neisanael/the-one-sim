package core.NAKT;

import core.NAKT.Util.NodeUtil;

import static core.NAKT.Util.EncryptionUtil.hashSha1;

public class KeyTree {

    private final int lcnum;

    public KeyTree(int lcnum) {
        this.lcnum = lcnum;
    }

    // the key for an internal element with ktid = ξ k b recursively as
    // K ξkb num = H(Kξ num k b), for some ξ ∈ (0 + 1)∗ ,b = 0 or 1
    // and H is a one-way hash function (approximated by MD5 or SHA1)
    public NodeUtil generateKeyTree(int min, int max, String parentKey, String binaryPath, int depth) {

        // Stop recursion if we've reached the depth limit
        if (depth >= lcnum || min >= max) {
            return null;
        }

        int mid = (min + max) / 2;

        // Generate the key for the current node
        String key = generateChildKeyTree(parentKey, binaryPath);

        // Create the current node
        NodeUtil node = new NodeUtil(binaryPath, key);

        // Generate left and right children only if we haven't reached the depth limit
        if (depth + 1 < lcnum) {
            node.left = generateKeyTree(min, mid, key, "0", depth + 1);
            node.right = generateKeyTree(mid + 1, max, key, "1", depth + 1);
        } else {
            // If we're at the depth limit, set children to null
            node.left = null;
            node.right = null;
        }

        return node;
    }

    private String generateChildKeyTree(String parentKey, String binaryPath) {
        return hashSha1(parentKey.concat(binaryPath));
    }

    public void printTree(NodeUtil node, int level) {
        if (node == null) return;
        System.out.println("Level " + level + " | Path: " + node.path + " | Key: " + node.key);
        printTree(node.left, level + 1);
        printTree(node.right, level + 1);
    }

    public NodeUtil generateTreeFromRoot(String rootKey) {
        return generateTreeFromRootHelper(rootKey, "", 0);
    }

    private NodeUtil generateTreeFromRootHelper(String parentKey, String binaryPath, int depth) {
        // Stop recursion if we've reached the depth limit
        if (depth >= lcnum) {
            return null;
        }

        // Create the current node
        NodeUtil node = new NodeUtil(binaryPath, parentKey);

        // Generate left and right children
        if (depth + 1 < lcnum) {
            node.left = generateTreeFromRootHelper(generateChildKeyTree(parentKey, "0"), binaryPath + "0", depth + 1);
            node.right = generateTreeFromRootHelper(generateChildKeyTree(parentKey, "1"), binaryPath + "1", depth + 1);
        } else {
            // If we're at the depth limit, set children to null
            node.left = null;
            node.right = null;
        }

        return node;
    }
}
