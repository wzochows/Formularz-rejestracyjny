import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

/*
    Borowy
    Żochowska
    Jęczmieniowski
 */

public class CustomButton extends JButton implements ActionListener{
    private Color color1 = Color.decode("#ff7e5f");
    private Color color2 = Color.decode("#feb47b");
    private final Timer timer;
    private float alpha=0.3f;
    private boolean mouseOver;

    CustomButton(String text){
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(10,20,10,20));
        addActionListener(this);
        setVisible(true);
        setText(text);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver=true;
                timer.start();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver=false;
                timer.start();
            }
        });
        timer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mouseOver) {
                    if(alpha<0.6f){
                        alpha += 0.05f;
                        repaint();
                    }else{
                        alpha=0.6f;
                        timer.stop();
                        repaint();
                    }
                }else {
                    if(alpha>0.3f){
                        alpha-=0.05f;
                        repaint();
                    }else{
                        alpha=0.3f;
                        timer.stop();
                        repaint();
                    }
                }
            }
        });
    }
    @Override
    protected void paintComponent(Graphics grphcs){
        int width=getWidth();
        int height=getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // gradient color
        GradientPaint gradient = new GradientPaint(0,0,color1, width, 0, color2);
        g2.setPaint(gradient);
        g2.fillRoundRect(0,0,width, height, height, height);
        createStyle(g2);
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
    }

    private void createStyle(Graphics2D g2){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        int width=getWidth();
        int height=getHeight();
        GradientPaint gradient = new GradientPaint(0,0,Color.WHITE, 0, height, new Color(255,255,255,60));
        g2.setPaint(gradient);
        Path2D.Float f = new Path2D.Float();
        f.moveTo(0,0);
        int controll = height + height/2;
        f.curveTo(0, 0, width/2, controll, width, 0);
        g2.fill(f);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
