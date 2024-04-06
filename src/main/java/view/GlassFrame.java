package view;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.GLASS_FRAME_DIMENSION;

public final class GlassFrame extends JFrame {
    private static GlassFrame INSTANCE;
    private GlassFrame() throws HeadlessException {
        setUndecorated(true);
        setBackground(new Color(0,0,0,0));
        setSize(GLASS_FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
    }

    public static GlassFrame getINSTANCE() {
        if (INSTANCE==null) INSTANCE=new GlassFrame();
        return INSTANCE;
    }
}
