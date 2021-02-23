package BadPS;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class LayerControls extends JComponent{

    private JPanel drawingArea;
    private JSpinner depthSpinner;
    public JPanel mainpanel;
    private Layer l;
    private EngineCanvas ec;

    public LayerControls(Layer l) {
        setLayer(l);
        setVisible(true);
        this.setSize(70, 30);

    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    public void setLayer(Layer l){
        this.depthSpinner.setValue(l.depth);
        Engine e = new Engine();
        e.addLayer(l);
        EngineCanvas ec = new EngineCanvas(e);
        drawingArea.add(ec);
        depthSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                l.depth = Integer.parseInt(depthSpinner.getValue().toString());
                Drawer.redraw();
            }
        });
        setVisible(true);

    }
    private void createUIComponents() {
        depthSpinner = new JSpinner(new SpinnerNumberModel(1, -20, 20, 1));
    }
}
