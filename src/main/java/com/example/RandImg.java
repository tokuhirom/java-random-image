package com.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RandImg {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        report(new Random());
        report(ThreadLocalRandom.current());
        report(SecureRandom.getInstance("NativePRNGNonBlocking"));

        SplittableRandom splittableRandom = new SplittableRandom();
        report("SplittableRandom", splittableRandom::nextInt);
    }

    private static void report(Random random) throws IOException {
        report(random.getClass().getSimpleName(), random::nextInt);
    }

    // Code from https://www.javamex.com/tutorials/random_numbers/lcg_bit_positions.shtml
    private static void report(String name, Supplier<Integer> random) throws IOException {
        BufferedImage bimg = new BufferedImage(256, 256,
                BufferedImage.TYPE_BYTE_BINARY);
        int w = bimg.getWidth();
        for (int y = 0; y < bimg.getHeight(); y++) {
            for (int x = 0; x < w; x++) {
                int bit = random.get() & 1;
                bimg.setRGB(x, y, (bit == 0) ? 0 : 0xffffff);
            }
        }
        ImageIO.write(bimg, "png", new File("out-" + name + ".png"));
    }
}
