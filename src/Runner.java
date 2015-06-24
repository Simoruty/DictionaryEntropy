import simo.tree.GenericTree;
import simo.tree.GenericTreeNode;

/**
 * Created by simo on 24/06/15.
 */
public class Runner {
    public static void main(String[] args) {
        /*
         We're building a tree that looks like this:

         I am root!
             /\
            A  B
              /\
             C  D
                 \
                 E
        */

        GenericTree<String> tree = new GenericTree<String>();

        GenericTreeNode<String> root = new GenericTreeNode<String>("I am root!");
        GenericTreeNode<String> childA = new GenericTreeNode<String>("A");
        GenericTreeNode<String> childB = new GenericTreeNode<String>("B");
        GenericTreeNode<String> childC = new GenericTreeNode<String>("C");
        GenericTreeNode<String> childD = new GenericTreeNode<String>("D");
        GenericTreeNode<String> childE = new GenericTreeNode<String>("E");

        childD.addChild(childE);

        childB.addChild(childC);
        childB.addChild(childD);

        root.addChild(childA);
        root.addChild(childB);

        tree.setRoot(root);

    }
}
