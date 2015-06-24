import simo.node.NodeMod;
import simo.tree.GenericTree;

import java.io.IOException;

import static simo.entropyDict.buildTreeByDict;
import static simo.entropyDict.entropyLevel;

/**
 * Created by simo on 24/06/15.
 */


public class Runner {

    public static final String DIRDICTPATH = "res/dictEng";


    public static void main(String[] args) throws IOException {
        GenericTree<NodeMod> aa = buildTreeByDict(DIRDICTPATH);
        entropyLevel(aa, 1);

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
