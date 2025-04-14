package com.financify.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JLayeredPane;
import javax.swing.JRootPane;

import com.financify.utils.GlobalConstants;

public class CoverPanel extends RoundPanel {

    public CoverPanel() {
        setBackground(new Color(0, 0, 0, 180));
        setBounds(0, 0, GlobalConstants.WINDOW_WIDTH, GlobalConstants.WINDOW_HEIGHT);

        // This prevents mouse clicks from going through
        addMouseListener(new MouseAdapter() {});
    }

    public void cover(JRootPane rootPane) {
        /**
         * Puts the CoverPanel into the main JFrame.
         * Always fetch the current layeredPane to avoid stale references.
         */
        JLayeredPane layeredPane = rootPane.getLayeredPane();
        layeredPane.add(this, JLayeredPane.PALETTE_LAYER);
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public void uncover() {
        if (getParent() instanceof JLayeredPane) {
            JLayeredPane layeredPane = (JLayeredPane) getParent();
            layeredPane.remove(this);
            layeredPane.revalidate();
            layeredPane.repaint();
        }
    }
}
