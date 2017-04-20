package com.helpme.app.engine.utils;

import com.helpme.app.engine.renderer.base.Texture;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;
import sun.nio.ch.IOUtil;
import sun.security.provider.SHA;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.lwjgl.BufferUtils.createByteBuffer;
import static org.lwjgl.stb.STBImage.*;

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
                    int pixel = pixels[y * imageBuffer.getWidth() + x];
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
