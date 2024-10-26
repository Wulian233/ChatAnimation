package com.wulian.chatimpressiveanimation.neoforge.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.wulian.chatimpressiveanimation.ChatImpressiveAnimationExpectPlatform;
import com.wulian.chatimpressiveanimation.config.ConfigUtil;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mixin(ChatHud.class)
public class ChatHudMixin {
	@Shadow private int scrolledLines;
	@Shadow @Final private List<ChatHudLine.Visible> visibleMessages;
	@Shadow private int getLineHeight() { return 0; }
	@Unique private final ArrayList<Long> messageTimestamps = new ArrayList<>();

	@Unique private final float fadeOffsetYScale = 0.8f; // scale * lineHeight
	@Unique private final float fadeTime = 150;

	@Unique private int chatLineIndex;
	@Unique private int chatDisplacementY = 0;

	@Inject(method = "render", at = @At(
		value = "INVOKE",
		target = "Lnet/minecraft/client/gui/hud/ChatHudLine$Visible;addedTime()I"
	))
	public void getChatLineIndex(CallbackInfo ci, @Local(ordinal = 13) int chatLineIndex) {
		// Capture which chat line is currently being rendered
		this.chatLineIndex = chatLineIndex;
	}

	@Unique
	private void calculateYOffset() {
		// Calculate current required offset to achieve slide in from bottom effect
		try {
			int lineHeight = this.getLineHeight();
			float maxDisplacement = (float)lineHeight * fadeOffsetYScale;
			long timestamp = messageTimestamps.get(chatLineIndex);
			long timeAlive = System.currentTimeMillis() - timestamp;
			if (chatLineIndex == 0 && timeAlive < fadeTime && this.scrolledLines == 0) {
				chatDisplacementY = (int)(maxDisplacement - ((timeAlive/fadeTime)*maxDisplacement));
			}
		} catch (Exception ignored) {}
	}

	@ModifyArg(method = "render", index = 1, at = @At(
		value = "INVOKE",
		target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V",
		ordinal = 1
	))
	private float applyYOffset(float y) {
		if (!ConfigUtil.getConfig().enableChatSendingAnimation) return y;
		// Apply the offset
		calculateYOffset();

		// Raised mod compatibility
		if (ChatImpressiveAnimationExpectPlatform.getObjectShareItem("raised:hud") instanceof Integer distance) {
			// for Raised 1.2.0+
			y -= distance;
		} else if (ChatImpressiveAnimationExpectPlatform.getObjectShareItem("raised:distance") instanceof Integer distance) {
			y -= distance;
		}

		return y + chatDisplacementY;
	}

	@Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At("TAIL"))
	private void addMessage(Text message, MessageSignatureData signatureData, MessageIndicator indicator, CallbackInfo ci) {
		messageTimestamps.addFirst(System.currentTimeMillis());
		while (this.messageTimestamps.size() > this.visibleMessages.size()) {
			this.messageTimestamps.removeLast();
		}
	}
}
