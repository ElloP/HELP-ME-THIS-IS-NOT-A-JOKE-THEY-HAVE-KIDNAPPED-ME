package com.helpme.app.engine.utils;

import com.helpme.app.engine.renderer.base.Texture;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;
import sun.nio.ch.IOUtil;

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
    private static final String SHADERPATH = new File("").getAbsolutePath() + "/src/main/java/com/helpme/app/engine/renderer/textures/";

    public static Texture loadTexture(String fileName) {
        Texture texture = new Texture();
        try {

            ByteBuffer imageBuffer = ioResourceToByteBuffer(SHADERPATH + fileName, 8 * 1024);
            IntBuffer widthBuffer = MemoryUtil.memAllocInt(1);
            IntBuffer heightBuffer = MemoryUtil.memAllocInt(1);
            IntBuffer compBuffer = MemoryUtil.memAllocInt(1);

            if (!stbi_info_from_memory(imageBuffer, widthBuffer, heightBuffer, compBuffer)) {
                throw new RuntimeException("Failed to read image information: " + fileName + " Reason::" + stbi_failure_reason());
            }

            ByteBuffer imageData;
            imageData = stbi_load_from_memory(imageBuffer, widthBuffer, heightBuffer, compBuffer, 0);
            if (imageData == null) {
                throw new RuntimeException("Failed to load image: " + fileName + " Reason::" + stbi_failure_reason());
            }
            int width = widthBuffer.get(0);
            int height = heightBuffer.get(0);
            int comp = compBuffer.get(0);

            texture.generate(imageData, width, height, comp);

            stbi_image_free(imageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return texture;
    }


    /*
     * Note(Olle): the two functions below are fetched from:
     * https://github.com/LWJGL/lwjgl3/blob/master/modules/core/src/test/java/org/lwjgl/demo/stb/Image.java
     * Copyright © 2012-present Lightweight Java Game Library
     * All rights reserved.
     */
    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;

        Path path = Paths.get(resource);
        if ( Files.isReadable(path) ) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = createByteBuffer((int)fc.size() + 1);
                while ( fc.read(buffer) != -1 ) ;
            }
        } else {
            try (
                    InputStream source = IOUtil.class.getClassLoader().getResourceAsStream(resource);
                    ReadableByteChannel rbc = Channels.newChannel(source)
            ) {
                buffer = createByteBuffer(bufferSize);

                while ( true ) {
                    int bytes = rbc.read(buffer);
                    if ( bytes == -1 )
                        break;
                    if ( buffer.remaining() == 0 )
                        buffer = resizeBuffer(buffer, buffer.capacity() * 2);
                }
            }
        }

        buffer.flip();
        return buffer;
    }
}
