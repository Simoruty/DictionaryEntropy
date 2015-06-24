import simo.node.NodeMod;
import simo.tree.GenericTree;

import java.io.IOException;

import static simo.entropyDict.*;

/**
 * Created by simo on 24/06/15.
 */

public class Runner {

    public static final String DIRDICTPATH = "res/dictEng";

    public static void main(String[] args) throws IOException {
        GenericTree<NodeMod> aa = buildTreeByDict(DIRDICTPATH);
        double enTot = 0;
        System.out.println(aa);

        for (int i = 1; i < depth(aa)+1; i++) {
            System.out.println(entropyLevel(aa,i));
            enTot += entropyLevel(aa, i);
        }
        System.out.println(enTot);
    }
}
