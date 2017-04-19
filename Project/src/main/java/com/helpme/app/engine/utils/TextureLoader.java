package com.helpme.app.engine.utils;

import com.helpme.app.engine.renderer.base.Texture;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_info_from_memory;
import static org.lwjgl.stb.STBImage.stbi_load_from_memory;

/**
 * Created by Olle on 2017-04-19.
 */
public class TextureLoader {
    public static Texture loadTexture(String fileName) {
        Texture texture = new Texture();
        ByteBuffer imageBuffer = MemoryUtil.memAlloc(8 * 1024);
        IntBuffer widthBuffer = MemoryUtil.memAllocInt(1);
        IntBuffer heightBuffer = MemoryUtil.memAllocInt(1);
        IntBuffer compBuffer = MemoryUtil.memAllocInt(1);;

        if(!stbi_info_from_memory(imageBuffer, widthBuffer, heightBuffer, compBuffer)) {
            throw new RuntimeException("Failed to read image information: " + fileName + " Reason::" + stbi_failure_reason());
        }

        ByteBuffer imageData;
        imageData = stbi_load_from_memory(imageBuffer, widthBuffer, heightBuffer, compBuffer, 0);
        if(imageData == null) {
            throw new RuntimeException("Failed to load image: " + fileName + " Reason::" + stbi_failure_reason());
        }
        int width = widthBuffer.get(0);
        int height = heightBuffer.get(0);

        texture.generate(imageData, width, height);

        return texture;
    }
}
