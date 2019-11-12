package com.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandImg {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        report("Random", new Random());
        report("ThreadLocalRandom", ThreadLocalRandom.current());
        report("SecureRandom", SecureRandom.getInstance("NativePRNGNonBlocking"));
    }

    private static void report(String name, Random random) throws IOException {
        BufferedImage bimg = new BufferedImage(256, 256,
                BufferedImage.TYPE_BYTE_BINARY);
        int w = bimg.getWidth();
        for (int y = 0; y < bimg.getHeight(); y++) {
            for (int x = 0; x < w; x++) {
                int bit = random.nextInt() & 1;
                bimg.setRGB(x, y, (bit == 0) ? 0 : 0xffffff);
            }
        }
        ImageIO.write(bimg, "png", new File("out-" + random.getClass().getSimpleName() + ".png"));
    }
}
