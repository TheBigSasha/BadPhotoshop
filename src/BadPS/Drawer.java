package BadPS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drawer {
    static Engine e;
     static Canvas canvas;

    static Color[][] pixelInCenterArr = new Color[][]{
        {null,null,null,null,null},
        {null,null,null,null,null},
        {null,null,Color.RED,null,null},
        {null,null,null,null,null},
        {null,null,null,null,null},
    };

    static Color[][] whiteScreenArr = new Color[][]{
            {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
            {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
            {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
            {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
            {Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE,Color.WHITE},
    };

    static Color[][] greenLineArr = new Color[][]{
            {Color.GREEN},
            {null}
    };


    static Color[][] redLineArr = new Color[][]{
            {Color.MAGENTA, null, null, null, null},
    };

    static Color[][] faceArr = new Color[][]{
            {null}, {null, Color.WHITE, Color.BLACK, null,
            null, Color.WHITE, Color.BLACK, null}, {null,
            Color.WHITE, Color.WHITE, null, null, Color.WHITE,
            Color.WHITE, null}, {null}, {null, Color.PINK,
            Color.PINK, Color.PINK, Color.PINK, Color.PINK, Color.PINK, null},
            {null, Color.PINK, Color.PINK, Color.PINK, Color.PINK.brighter(),
                    Color.PINK.brighter(), Color.PINK, null}, {null,
            null, Color.PINK, Color.PINK, Color.PINK.brighter().brighter(),
            Color.PINK.brighter().brighter(), null, null}, {null}};

    static Layer pixelInCenter = new Layer(pixelInCenterArr, 0);
    static Layer redLine = new Layer(redLineArr, 2);
    static Layer whiteScreen = new Layer(whiteScreenArr, -1);
    static Layer greenLine = new Layer(greenLineArr, 1);
    static Layer face = new Layer(faceArr, -4);
    static Layer yellow = new Layer(new Color[][]{
            {Color.YELLOW}
    }, -3);
    static Layer blue = new Layer(new Color[][]{{Color.BLUE}}, -3);


    static Layer[] layers = new Layer[]{pixelInCenter,redLine,whiteScreen,greenLine,face, yellow, blue};




    public static void main(String[] args){
        JFrame frame = new JFrame("Bad Photoshop");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored){ }

        JPanel layersPanel = new JPanel();
        layersPanel.setLayout(new BoxLayout(layersPanel, BoxLayout.Y_AXIS));
        e = new Engine();
        for(Layer l : layers){
            LayerControls lc = new LayerControls(l);
            layersPanel.add(lc.mainpanel);
            e.addLayer(l);
        }

        canvas = new EngineCanvas(e);
        canvas.setSize(640, 640);
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(layersPanel, BorderLayout.WEST);


        frame.pack();
        frame.setVisible(true);
    }

    public static  void redraw(){
        canvas.repaint();
    }

}

class EngineCanvas extends  Canvas{
    private final Engine e;
    public EngineCanvas(Engine eng){
        e = eng;
    }

    public Dimension getPreferredSize(){
        return new Dimension(40,40);
    }

    public void paint(Graphics g){
        g.clearRect(0, 0, getWidth(), getHeight());
        for(Layer l : e.getSortedLayers()){
            for (int i = 0; i < l.contents.length; i++) {
                int pxPerHor = getWidth() / l.contents[i].length;
                int lenAtIdx = l.contents.length;
                int pxPerVer = getHeight() / lenAtIdx;
                for (int j = 0; j <  l.contents[i].length; j++) {
                    if(l.contents[i][j] != null) {
                        g.setColor(l.contents[i][j]);
                        g.fillRect(j * pxPerHor, i * pxPerVer, pxPerHor, pxPerVer );
                    }
                }
            }
        }
    }
}
