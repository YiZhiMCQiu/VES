package cn.yizhimcqiu.ves;

import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.net.URI;

public interface VESVersion {
    String VERSION = "0.1.0";
    Text VERSION_TEXT = ((MutableText) Text.of("Venti Script Mod"))
            .append("版本: "+VERSION).append("\n")
            .append("GitHub开源地址: ")
            .append(((MutableText) Text.of(URI.create("https://github.com/YiZhiMCQiu/VES"))).styled(
                    style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/YiZhiMCQiu/VES"))
            ));
}
