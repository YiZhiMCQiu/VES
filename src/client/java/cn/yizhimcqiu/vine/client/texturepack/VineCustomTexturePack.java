package cn.yizhimcqiu.vine.client.texturepack;

import com.mojang.logging.LogUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;

@SuppressWarnings("unused")
public class VineCustomTexturePack {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static void registerTextureFor(String id, String texture) {
        try {
            NativeImageBackedTexture tex = new NativeImageBackedTexture(NativeImage.read(ByteBuffer.wrap(Base64.getDecoder().decode(texture))));
            MinecraftClient.getInstance().getTextureManager().registerTexture(Identifier.tryParse(id), tex);
        } catch (IOException e) {
            LOGGER.error("Error while parsing base64 texture {}", id, e);
        }
    }
}
