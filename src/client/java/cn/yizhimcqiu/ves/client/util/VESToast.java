package cn.yizhimcqiu.ves.client.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class VESToast {
    public static VESToast current;
    private final long startTime;
    private final Text content;
    public VESToast(Text content) {
        this(System.currentTimeMillis(), content);
    }
    public VESToast(long startTime, Text content) {
        this.startTime = startTime;
        this.content = content;
    }

    public void render(DrawContext context) {
        Phase phase = getNowPhase();
        int w = MinecraftClient.getInstance().getFramebuffer().viewportWidth;
        int x = w - this.getLength();
        if (phase.shouldRenderFVB()) {
            context.fill(x - 2, 10, w, 10 + getHeight(), 0xFFFFFFFF);
        }
        if (phase.shouldRenderFBB()) {
            context.fill(x, 10, w, 10 + getHeight(), 0xFF000000);
        }
    }
    private int getLength() {
        return MinecraftClient.getInstance().textRenderer.getWidth(content.asOrderedText());
    }
    private int getHeight() {
        return MinecraftClient.getInstance().textRenderer.fontHeight * 3;
    }
    private Phase getNowPhase() {
        if (System.currentTimeMillis() - startTime <= 500) return Phase.VES_BACKGROUND_ANIMATION;
        else if (System.currentTimeMillis() - startTime <= 1000) return Phase.BLACK_BACKGROUND_ANIMATION;
        else return Phase.ALL;
    }

    private enum Phase {
        VES_BACKGROUND_ANIMATION, BLACK_BACKGROUND_ANIMATION, ALL;
        private boolean shouldRenderFVB() {
            return this == BLACK_BACKGROUND_ANIMATION || this == ALL;
        }
        private boolean shouldRenderFBB() {
            return this == ALL;
        }
    }
}
