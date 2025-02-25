package core.NAKT.Util;

public class NodeUtil {
    public String path;
    public String key;
    public NodeUtil left;
    public NodeUtil right;

    public NodeUtil(String path, String key) {
        this.path = path;
        this.key = key;
    }
}
