import simo.tree.GenericTree;
import simo.tree.GenericTreeNode;
import simo.tree.GenericTreeTraversalOrderEnum;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by simo on 24/06/15.
 */
public class Runner {

    private static final String DIRDICTPATH = "res/dictEng";

    private static GenericTree<Character> buildTreeByDict() throws IOException {

        GenericTree<Character> tree = new GenericTree<>();
        GenericTreeNode<Character> root = new GenericTreeNode<>('#');
        tree.setRoot(root);

        List<String> lines = Files.readAllLines(Paths.get(DIRDICTPATH),
                Charset.defaultCharset());

        int countWord = 0;
        for (String line : lines) {
            countWord++;
            char[] wordChar = line.toCharArray();
            GenericTreeNode<Character> node = tree.getRoot();
            for (char c : wordChar) {
                GenericTreeNode<Character> nodeNew = new GenericTreeNode<>(c);
                if (node.hasChildren()) {
                    boolean exist = false;
                    for (GenericTreeNode<Character> children : node.getChildren()) {
                        if (children.getData().equals(c)) {
                            exist = true;
                            node = children;
                        }
                    }
                    if (!exist) {
                        node.addChild(nodeNew);
                        node = nodeNew;
                    }
                } else {
                    node.addChild(nodeNew);
                    node = nodeNew;
                }

            }
        }
//        System.out.println("NODE: "+ tree.getRoot().getChildAt(1).getData()+ " Children: "+ tree.getRoot().getChildAt(1).getChildren());
        System.out.println("Word insered: " + countWord);
        return tree;
    }


    public static void main(String[] args) throws IOException {
        GenericTree<Character> aa = buildTreeByDict();

//        /*
//         We're building a tree that looks like this:
//
//         I am root!
//             /\
//            A  B
//              /\
//             C  D
//                 \
//                 E
//        */
//
//        GenericTree<String> tree = new GenericTree<>();
//
//        GenericTreeNode<String> root = new GenericTreeNode<>("I am root!");
//        GenericTreeNode<String> childA = new GenericTreeNode<>("A");
//        GenericTreeNode<String> childB = new GenericTreeNode<>("B");
//        GenericTreeNode<String> childC = new GenericTreeNode<>("C");
//        GenericTreeNode<String> childD = new GenericTreeNode<>("D");
//        GenericTreeNode<String> childE = new GenericTreeNode<>("E");
//
//        childD.addChild(childE);
//
//        childB.addChild(childC);
//        childB.addChild(childD);
//
//        root.addChild(childA);
//        root.addChild(childB);
//
//        tree.setRoot(root);

    }
}
