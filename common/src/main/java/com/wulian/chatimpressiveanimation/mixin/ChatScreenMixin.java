package com.wulian.chatimpressiveanimation.mixin;

import com.wulian.chatimpressiveanimation.config.ConfigUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
	@Unique private boolean wasOpenedLastFrame = false;
	@Unique private boolean isClosing = false;
	@Unique private long lastOpenTime = 0;
	@Unique private long closeStartTime = 0;
	@Unique private float offsetY = 0;

	private static final float FADE_TIME = 170;
	private static final float FADE_OFFSET = 8;
	private static final float C1 = 1.70158f;
	private static final float C3 = C1 + 1;

	@Inject(method = "render", at = @At("HEAD"))
	private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		if (!ConfigUtil.getConfig().enableChatBarAnimation) return;

		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player != null) {
			if (!wasOpenedLastFrame && !client.player.isSleeping()) {
				wasOpenedLastFrame = true;
				lastOpenTime = System.currentTimeMillis();
				isClosing = false;
			}
		}

		float screenFactor = (float)client.getWindow().getHeight() / 1080;
		float timeSinceOpen = Math.min((float)(System.currentTimeMillis() - lastOpenTime), FADE_TIME);
		float alpha = 1 - (timeSinceOpen / FADE_TIME);

		float modifiedAlpha = C3 * alpha * alpha * alpha - C1 * alpha * alpha;

		offsetY = modifiedAlpha * FADE_OFFSET * screenFactor;

		if (!isClosing) {
			context.getMatrices().translate(0, offsetY, 0);
		} else {
			float timeSinceClose = Math.min((float)(System.currentTimeMillis() - closeStartTime), FADE_TIME);
			float closeAlpha = timeSinceClose / FADE_TIME;
			float modifiedCloseAlpha = C3 * closeAlpha * closeAlpha * closeAlpha - C1 * closeAlpha * closeAlpha;
			offsetY = modifiedCloseAlpha * FADE_OFFSET * screenFactor;
			// 将这里的位移方向改为正值，向下移动
			context.getMatrices().translate(0, offsetY, 0); // 正向位移，关闭时向下
		}
	}

	@Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
	private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
		if (keyCode == 256) { // ESC key
			if (ConfigUtil.getConfig().enableChatBarAnimation) {
				isClosing = true;
				closeStartTime = System.currentTimeMillis();
			} else {
				MinecraftClient.getInstance().setScreen(null);
			}
			cir.cancel(); // Cancel the default behavior to allow the animation to complete
		}
	}

	@Inject(method = "render", at = @At("TAIL"))
	private void renderEnd(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
		if (!ConfigUtil.getConfig().enableChatBarAnimation) return;
		if (isClosing) {
			MinecraftClient client = MinecraftClient.getInstance();
			if ((System.currentTimeMillis() - closeStartTime) >= FADE_TIME) {
				client.setScreen(null);
			}
		}
	}
}

