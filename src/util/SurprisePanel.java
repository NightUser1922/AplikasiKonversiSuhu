package util;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import javax.swing.Timer;

public class SurprisePanel extends JPanel {
    private final String[] pesan = {
        "🌈 Hari cerah, semangat konversi suhu!",
        "🔥 Kamu panas banget hari ini! (suhunya ya 😄)",
        "❄️ Dingin banget, tapi tetap produktif!",
        "🎶 Coding itu seperti musik — nikmati ritmenya!",
        "💫 Surprise! Kamu hebat hari ini!"
    };
    private final Random rand = new Random();
    private String teks = pesan[rand.nextInt(pesan.length)];

    public SurprisePanel() {
        setPreferredSize(new Dimension(300, 150));
        setBackground(getRandomColor());

        // Timer animasi warna tiap 200ms
        Timer timer = new Timer(200, e -> {
            setBackground(getRandomColor());
            repaint();
        });
        timer.start();

        // Timer berhenti otomatis setelah 3 detik
        new Timer(3000, e -> timer.stop()).start();
    }

    private Color getRandomColor() {
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Segoe UI", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(teks)) / 2;
        int y = getHeight() / 2;
        g.drawString(teks, x, y);
    }
}
