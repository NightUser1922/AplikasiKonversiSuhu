package util;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.swing.Timer;

public class SurprisePanel extends JPanel {
    
    private final Random rand = new Random();
    private String teks;
    private Timer stroboTimer;
    private int flashCount = 0;
    private final int TOTAL_FLASHES = 160;

    private final Color POLICE_RED = new Color(255, 0, 0);
    private final Color POLICE_BLUE = new Color(30, 30, 255);
    private final Color DARK_BLUE = new Color(0, 0, 80);


    public SurprisePanel() {
        this.teks = "Tunggu data suhu...";
        setPreferredSize(new Dimension(1500, 600));
        setBackground(Color.BLACK);
    }

    // Method untuk mengatur suhu dan menampilkan pesan yang sesuai
    public void setSuhu(double suhuCelcius) {
        if (suhuCelcius <= -50) {
            this.teks = "❄ SUHU NOL ABSOLUT! Hampir Mustahil! ❄";
        } else if (suhuCelcius <= -20) {
            this.teks = "❄ MEMBEKULAH! Hindari es batu! ❄";
        } else if (suhuCelcius <= 0) {
            this.teks = "❄ BRRR BRRR, DINGIN! Tetapi Tidak Kejam! ❄";
        } else if (suhuCelcius <= 10) {
            this.teks = "🌡 SUHU MASIH RENDAH, HATI-HATI! Mari lanjutkan! 🌡";
        } else if (suhuCelcius <= 25) {
            this.teks = "🌡 SUHU NORMAL! Mari lanjutkan! DEMI KEMULIAAN ! 🌡";
        } else if (suhuCelcius <= 35) {
            this.teks = "🔥 HANGAT! Stay cool! Menuju Tak Terbatas dan Melampauinya 🔥";
        } else if (suhuCelcius <= 60) {
            this.teks = "🚨 TOT - TOT WUK - WUK, PANAS! Awas overheating! 🚨";
        } else if (suhuCelcius <= 100) {
            this.teks = "💀 SUHU EKSTRIM! Evakuasi segera! 💀";
        } else {
            this.teks = "💀 I, AM, ATOMIC! SUHU TAK TERBENDUNG! 💀";
        }
        
        // Mulai efek strobo setiap kali suhu diupdate
        startPoliceStrobo();
    }

    private void startPoliceStrobo() {
        if (stroboTimer != null && stroboTimer.isRunning()) {
            stroboTimer.stop();
        }
        
        flashCount = 0;
        stroboTimer = new Timer(200, e -> {
            flashCount++;
            
            if (flashCount >= TOTAL_FLASHES) {
                stroboTimer.stop();
                setBackground(Color.WHITE);
                repaint();
                return;
            }
            
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
                    setBackground(POLICE_BLUE);
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
        
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        
        drawStroboEffects(g2d);
        drawTextWithEffects(g2d);
    }
    
    private void drawStroboEffects(Graphics2D g2d) {
        if (flashCount % 2 == 0) {
            g2d.setColor(POLICE_RED);
            g2d.fillOval(20, 20, 40, 40);
            
            g2d.setColor(POLICE_BLUE);
            g2d.fillOval(getWidth() - 60, 20, 40, 40);
            
            g2d.setColor(Color.WHITE);
            g2d.fillOval(20, getHeight() - 60, 40, 40);
            
            g2d.setColor(Color.YELLOW);
            g2d.fillOval(getWidth() - 60, getHeight() - 60, 40, 40);
        }
        
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
        Font policeFont = new Font("Arial Black", Font.BOLD, 24);
        g2d.setFont(policeFont);
        
        Color textColor = getBackground().equals(POLICE_RED) ? Color.YELLOW : Color.WHITE;
        g2d.setColor(textColor);
        
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(teks);
        int x = (getWidth() - textWidth) / 2 + 3;
        int y = getHeight() / 2 + 3;
        
        g2d.setColor(Color.BLACK);
        g2d.drawString(teks, x, y);
        
        g2d.setColor(textColor);
        g2d.drawString(teks, x - 3, y - 3);
        
        if (stroboTimer != null && stroboTimer.isRunning()) {
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(5, 5, getWidth() - 10, getHeight() - 10);
        }
    }
}