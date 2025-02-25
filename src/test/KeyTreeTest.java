package test;

import core.NAKT.KeyTree;
import core.NAKT.Util.NodeUtil;
import junit.framework.TestCase;
import org.junit.Test;

public class KeyTreeTest extends TestCase {

/*    @Test
    public void testGenerateChildKeyTree() {
        int lcnum = 5;
        KeyTree keyTree = new KeyTree(lcnum);
        String parentKey = "TestKey";
        String binaryPath = "01";

        String childKey1 = keyTree.generateChildKeyTree(parentKey, binaryPath);
        String childKey2 = keyTree.generateChildKeyTree(parentKey, binaryPath);

        // Ensure the generated key is consistent
        assertEquals(childKey1, childKey2);
        assertNotNull(childKey1);
    }*/

    @Test
    public void testGenerateKeyTree() {
        int lcnum = 3;
        String rootKey = "RootSecretKey";
        KeyTree keyTree = new KeyTree(lcnum);
        NodeUtil root = keyTree.generateKeyTree(0, 25, rootKey, "", 0);

        // Ensure root node is not null
        assertNotNull(root);
        assertEquals("", root.path);

        // Ensure depth of the tree is correct
        assertNotNull(root.left);
        assertNotNull(root.right);

        // Check some child nodes
        assertNotNull(root.left.key);
        assertNotNull(root.right.key);

        // Verify that the left and right keys are different
        assertNotSame(root.left.key, root.right.key);
    }

    @Test
    public void testTreeDepth1() {
        int lcnum = 3;
        String rootKey = "RootSecretKey";
        KeyTree keyTree = new KeyTree(lcnum);
        NodeUtil root = keyTree.generateKeyTree(0, 25, rootKey, "", 0);
        // keyTree.printTree(root, 0);
        // Tree should be balanced and reach the expected depth
        assertNotNull(root);
        assertNotNull(root.left);
        assertNotNull(root.right);
        assertNotNull(root.left.left);
        assertNotNull(root.left.right);
        assertNotNull(root.right.left);
        assertNotNull(root.right.right);

        assertNull(root.left.left.left);
        assertNull(root.right.right.right);
    }

    @Test
    public void testTreeDepth2() {
        int lcnum = 3;
        String rootKey = "b53e939e07e54d8ee61a23553a92a1c547214a00";
        KeyTree keyTree = new KeyTree(lcnum);
        NodeUtil root = keyTree.generateTreeFromRoot(rootKey);
        // keyTree.printTree(root, 0);
        // Tree should be balanced and reach the expected depth
        assertNotNull(root);
        assertNotNull(root.left);
        assertNotNull(root.right);
        assertNotNull(root.left.left);
        assertNotNull(root.left.right);
        assertNotNull(root.right.left);
        assertNotNull(root.right.right);

        assertNull(root.left.left.left);
        assertNull(root.right.right.right);
    }

}
