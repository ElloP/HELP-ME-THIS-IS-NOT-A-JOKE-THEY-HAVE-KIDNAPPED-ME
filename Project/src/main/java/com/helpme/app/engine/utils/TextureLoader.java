package com.helpme.app.engine.utils;

import com.helpme.app.engine.renderer.base.Texture;
import org.lwjgl.system.MemoryUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
/**
 * Authored by Olle on 2017-04-19.
 */
public class TextureLoader {
    private static final String TEXTUREPATH = new File("").getAbsolutePath() + "/src/main/java/com/helpme/app/engine/renderer/textures/";

    public static Texture loadTexture(String fileName) {
        final int BYTES_PER_PIXEL = 4;
        Texture texture = new Texture();
        try {
            BufferedImage imageBuffer = ImageIO.read(new File(TEXTUREPATH + fileName));
            int[] pixels = new int[imageBuffer.getHeight() * imageBuffer.getWidth()];
            imageBuffer.getRGB(0,0,
                               imageBuffer.getWidth(),
                               imageBuffer.getHeight(),
                               pixels, 0,
                               imageBuffer.getWidth());

            ByteBuffer imageData = MemoryUtil.memAlloc(imageBuffer.getHeight() * imageBuffer.getWidth() * 4);
            for(int y = 0; y < imageBuffer.getHeight(); y++) {
                for(int x = 0; x < imageBuffer.getWidth(); x++) {
                    int pixel = pixels[y * imageBuffer.getWidth() + x]; //Note(Olle): uses RGBA8888, so 8 bits for every color channel, hence the 8 bif shuffling
                    imageData.put((byte) ((pixel >> 16) & 0xFF));
                    imageData.put((byte) ((pixel >> 8) & 0xFF));
                    imageData.put((byte) (pixel & 0xFF));
                    if(imageBuffer.getColorModel().hasAlpha()) {
                        imageData.put((byte) ((pixel >> 24) & 0xFF));
                    } else {
                        imageData.put((byte)(0xFF));
                    }
                }
            }
            imageData.flip();
            texture.generate(imageData, imageBuffer.getWidth(), imageBuffer.getHeight());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return texture;
    }
}
