package me.vaimok.nekoplus.client.gui.components.items.buttons;

import me.vaimok.nekoplus.nekoplus;
import me.vaimok.nekoplus.client.setting.Setting;
import me.vaimok.nekoplus.client.gui.components.Component;
import me.vaimok.nekoplus.client.modules.client.ClickGui;
import me.vaimok.nekoplus.api.util.moduleUtil.RenderUtil;
import me.vaimok.nekoplus.api.util.moduleUtil.TextUtil;
import org.lwjgl.input.Mouse;

public class Slider extends Button {

    public Setting setting;
    private Number min;
    private Number max;
    private int difference;

    public Slider(Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = (Number) setting.getMin();
        this.max = (Number) setting.getMax();
        this.difference = max.intValue() - min.intValue();
        width = 15;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        dragSetting(mouseX, mouseY);
        RenderUtil.drawRect(x, y, x + width + 7.4F, y + height - 0.5f, !isHovering(mouseX, mouseY) ? 0x11555555 : 0x88555555);
        RenderUtil.drawRect(x, y, ((Number) setting.getValue()).floatValue() <= min.floatValue() ? x : x + (width + 7.4F) * partialMultiplier(), y + height - 0.5f, !isHovering(mouseX, mouseY) ? nekoplus.colorManager.getColorWithAlpha((nekoplus.moduleManager.getModuleByClass(ClickGui.class)).hoverAlpha.getValue()) : nekoplus.colorManager.getColorWithAlpha(((nekoplus.moduleManager.getModuleByClass(ClickGui.class)).alpha.getValue())));
        nekoplus.textManager.drawStringWithShadow(getName() + " " + TextUtil.GRAY + (setting.getValue() instanceof Float ? ((Number) setting.getValue()) : ((Number) setting.getValue()).doubleValue()), x + 2.3F, y - 1.7F - me.vaimok.nekoplus.client.gui.nekoplusGui.getClickGui().getTextOffset(), 0xFFFFFFFF);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isHovering(mouseX, mouseY)) {
            setSettingFromX(mouseX);
        }
    }

    @Override
    public boolean isHovering(int mouseX, int mouseY) {
        for (Component component : me.vaimok.nekoplus.client.gui.nekoplusGui.getClickGui().getComponents()) {
            if (component.drag) {
                return false;
            }
        }
        return mouseX >= getX() && mouseX <= getX() + getWidth() + 8 && mouseY >= getY() && mouseY <= getY() + height;
    }

    @Override
    public void update() {
        this.setHidden(!setting.isVisible());
    }

    private void dragSetting(int mouseX, int mouseY) {
        if(isHovering(mouseX, mouseY) && Mouse.isButtonDown(0)) {
            setSettingFromX(mouseX);
        }
    }

    @Override
    public float getHeight() {
        return 14;
    }

    private void setSettingFromX(int mouseX) {
        float percent = (mouseX - x) / (width + 7.4F);
        if(setting.getValue() instanceof Double) {
            double result = (Double) setting.getMin() + (difference * percent);
            setting.setValue(Math.round(10.0 * result) / 10.0);
        } else if (setting.getValue() instanceof Float) {
            float result = (Float) setting.getMin() + (difference * percent);
            setting.setValue(Math.round(10.0f * result) / 10.0f);
        } else if (setting.getValue() instanceof Integer) {
            setting.setValue(((Integer) setting.getMin() + (int)(difference * percent)));
        }
    }

    private float middle() {
        return max.floatValue() - min.floatValue();
    }

    private float part() {
        return ((Number) setting.getValue()).floatValue() - min.floatValue();
    }

    private float partialMultiplier() {
        return part() / middle();
    }
}
