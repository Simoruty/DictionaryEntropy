package simo;

import simo.node.NodeMod;
import simo.tree.GenericTree;
import simo.tree.GenericTreeNode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by simo on 24/06/15.
 */
public class entropyDict {

    private static int words = 0;

    private static List<GenericTreeNode<NodeMod>> allNodes(GenericTree<NodeMod> tree) {
        return tree.build();
    }

    public static GenericTree<NodeMod> buildTreeByDict(String path) throws IOException {

        GenericTree<NodeMod> tree = new GenericTree<>();
        GenericTreeNode<NodeMod> root = new GenericTreeNode<>(new NodeMod('#'));
        tree.setRoot(root);

        List<String> lines = Files.readAllLines(Paths.get(path),
                Charset.defaultCharset());

        for (String line : lines) {
            words++;
            char[] wordChar = line.toCharArray();
            GenericTreeNode<NodeMod> node = tree.getRoot();
            int level = 0;
            for (char c : wordChar) {
                level++;
                if (node.hasChildren()) {
                    boolean exist = false;
                    for (GenericTreeNode<NodeMod> children : node.getChildren()) {
                        if (children.getData().equals(new NodeMod(c))) {
                            exist = true;
                            int count = children.getData().getCount();
                            count++;
                            children.getData().setCount(count);
                            children.getData().setLevel(level);
                            node = children;
                        }
                    }
                    if (!exist) {
                        NodeMod n = new NodeMod(c);
                        n.setLevel(level);
                        GenericTreeNode<NodeMod> nodeNew = new GenericTreeNode<>(n);
                        node.addChild(nodeNew);
                        node = nodeNew;
                    }
                } else {
                    NodeMod n = new NodeMod(c);
                    n.setLevel(level);
                    GenericTreeNode<NodeMod> nodeNew = new GenericTreeNode<>(n);
                    node.addChild(nodeNew);
                    node = nodeNew;
                }

            }
//            if (words == 10) {
//                break;
//            }
        }
//        System.out.println("NODE: " + tree.getRoot().getChildAt(1).getData() + " Children: " + tree.getRoot().getChildAt(1).getChildren());
        System.out.println("Word insered: " + words);
        return tree;
    }


    public static double entropyLevel(GenericTree<NodeMod> tree, int level) {

        double entropy = 0;
        List<GenericTreeNode<NodeMod>> nodes = allNodes(tree);
        for (GenericTreeNode<NodeMod> node : nodes) {
            if (node.getData().getLevel() == level) {
                double propNode = (double) node.getData().getCount() / words;
                entropy += propNode * (Math.log(1 / propNode) / Math.log(2));
            }
        }
        System.out.println(entropy);
        return 0.0;
    }
}
