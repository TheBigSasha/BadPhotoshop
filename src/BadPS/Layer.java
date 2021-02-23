package BadPS;

import java.awt.*;

public class Layer {
    Color[][] contents;
    int depth;

    public Layer(Color[][] contents, int depth){
        this.contents = contents;
        this.depth = depth;
    }
}
