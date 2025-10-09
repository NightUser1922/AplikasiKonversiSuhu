package util;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.swing.Timer;
// import java.applet.AudioClip; // Uncomment jika ingin tambah sound

public class SurprisePanel extends JPanel {
    private final String[] pesan = {
        "🚨 WOOW-WOOW! Semangat konversi! 🚨",
        "🔥 SIRENE BERBUNYI! Kamu panas! 🔥",
        "❄️ DINGIN! Tapi jangan diam! ❄️",
        "⚡ HATI-HATI! Kecerdasan meledak! ⚡",
        "🎯 TARGET TERCAPAI! Excellent! 🎯"
    };
    
    private final Random rand = new Random();
    private String teks;
    private Timer stroboTimer;
    private boolean isRedPhase = true;
    private int flashCount = 0;
    private final int TOTAL_FLASHES = 15; // Sekitar 3 detik dengan interval 200ms

    // Warna strobo
    private final Color POLICE_RED = new Color(255, 0, 0);
    private final Color POLICE_BLUE = new Color(30, 30, 255);
    private final Color DARK_BLUE = new Color(0, 0, 80);

    public SurprisePanel() {
        this.teks = pesan[rand.nextInt(pesan.length)];
        setPreferredSize(new Dimension(700, 600));
        setBackground(Color.BLACK);
        
        startPoliceStrobo();
    }

    private void startPoliceStrobo() {
        // Efek strobo dengan pola yang lebih dinamis
        stroboTimer = new Timer(200, e -> {
            flashCount++;
            
            if (flashCount >= TOTAL_FLASHES) {
                stroboTimer.stop();
                return;
            }
            
            // Pola strobo: merah-biru-merah-hitam-biru-merah-dll
            switch (flashCount % 4) {
                case 0:
                    setBackground(POLICE_RED);
                    break;
                case 1:
                    setBackground(POLICE_BLUE);
                    break;
                case 2:
                    setBackground(POLICE_RED.brighter());
                    break;
                case 3:
                    setBackground(DARK_BLUE);
                    break;
            }
            
            repaint();
        });
        
        stroboTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // High quality rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        drawStroboEffects(g2d);
        drawTextWithEffects(g2d);
    }
    
    private void drawStroboEffects(Graphics2D g2d) {
        // Efek flashing lights di sudut
        if (flashCount % 2 == 0) {
            // Police light circles
            g2d.setColor(POLICE_RED);
            g2d.fillOval(20, 20, 40, 40);
            
            g2d.setColor(POLICE_BLUE);
            g2d.fillOval(getWidth() - 60, 20, 40, 40);
            
            g2d.setColor(Color.WHITE);
            g2d.fillOval(20, getHeight() - 60, 40, 40);
            
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(getWidth() - 60, getHeight() - 60, 40, 40);
        }
        
        // Rotating light bars
        int barCount = 8;
        int barWidth = getWidth() / barCount;
        for (int i = 0; i < barCount; i++) {
            if ((flashCount + i) % 4 == 0) {
                g2d.setColor(i % 2 == 0 ? POLICE_RED : POLICE_BLUE);
                g2d.fillRect(i * barWidth, 0, barWidth, getHeight());
            }
        }
    }
    
    private void drawTextWithEffects(Graphics2D g2d) {
        // Font untuk efek polisi
        Font policeFont = new Font("Arial Black", Font.BOLD, 24);
        g2d.setFont(policeFont);
        
        // Warna text dengan efek glow
        Color textColor = getBackground().equals(POLICE_RED) ? Color.YELLOW : Color.WHITE;
        g2d.setColor(textColor);
        
        // Shadow effect untuk readability
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(teks);
        int x = (getWidth() - textWidth) / 2 + 3;
        int y = getHeight() / 2 + 3;
        g2d.drawString(teks, x, y);
        
        // Main text
        g2d.setColor(textColor);
        g2d.drawString(teks, x - 3, y - 3);
        
        // Border effect selama strobo aktif
        if (stroboTimer.isRunning()) {
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(5, 5, getWidth() - 10, getHeight() - 10);
        }
    }
}