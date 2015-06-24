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

    // Restituisce la lista di tutti i nodi dell'albero
    private static List<GenericTreeNode<NodeMod>> allNodes(GenericTree<NodeMod> tree) {
        return tree.build();
    }

    // Costruzione dell'albero sulla base del dizionario
    public static GenericTree<NodeMod> buildTreeByDict(String path) throws IOException {

        GenericTree<NodeMod> tree = new GenericTree<>();
        GenericTreeNode<NodeMod> root = new GenericTreeNode<>(new NodeMod('#'));
        root.getData().setLevel(0);
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
                        if (children.getData().getName() == c) {
                            exist = true;
                            int count = children.getData().getCount();
                            count++;
                            children.getData().setCount(count);
                            node = children;
                        }
                    }
                    if (!exist) {
                        NodeMod n = new NodeMod(c, level);
                        GenericTreeNode<NodeMod> nodeNew = new GenericTreeNode<>(n);
                        node.addChild(nodeNew);
                        node = nodeNew;
                    }
                } else {
                    NodeMod n = new NodeMod(c, level);
                    GenericTreeNode<NodeMod> nodeNew = new GenericTreeNode<>(n);
                    node.addChild(nodeNew);
                    node = nodeNew;
                }

            }
        }
//        System.out.println("NODE: " + tree.getRoot().getChildAt(1).getData() + " Children: " + tree.getRoot().getChildAt(1).getChildren());
        System.out.println("Word insered: " + words);
        return tree;
    }

    // Calcola la profondita massima dell'albero
    public static int depth(GenericTree<NodeMod> tree) {
        int max = 0;
        List<GenericTreeNode<NodeMod>> nodes = allNodes(tree);
        for (GenericTreeNode<NodeMod> node : nodes) {
            if (max < node.getData().getLevel()) max = node.getData().getLevel();
        }
        return max;
    }

    public static double entropyLevel(GenericTree<NodeMod> tree, int level) {

        // Calcola entropia per il primo livello
        if (level == 1) {
            double entropy = 0;
            List<GenericTreeNode<NodeMod>> nodes = allNodes(tree);
            for (GenericTreeNode<NodeMod> node : nodes) {
                if (node.getData().getLevel() == level) {
                    double propNode = (double) node.getData().getCount() / words;
                    entropy += propNode * (Math.log(1 / propNode) / Math.log(2));
                }
            }
            return entropy;

        }
        // Calcola entropia per tutti gli altri livelli
        else {
            double entropy = 0;

            List<GenericTreeNode<NodeMod>> nodes = allNodes(tree);

            for (GenericTreeNode<NodeMod> node : nodes) {
                if (node.getData().getLevel() == level - 1) {
                    int allOcc = 0;

                    for (GenericTreeNode<NodeMod> brothers : node.getChildren())
                        allOcc += brothers.getData().getCount();

                    for (GenericTreeNode<NodeMod> nodeLevel : node.getChildren()) {

                        double propNode = (double) nodeLevel.getData().getCount() / words;
                        double propNodeCond = (double) nodeLevel.getData().getCount() / allOcc;

                        double entropyCond = propNode*(Math.log(1 / propNodeCond) / Math.log(2));

                        entropy += entropyCond;
                    }
                }
            }

            return entropy;
        }

    }
}