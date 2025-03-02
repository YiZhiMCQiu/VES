function of(name) {
    return Java.type("cn.yizhimcqiu.ves.event."+name).EVENT;
}

export const PlayerJoinWorldEvent = of("PlayerJoinWorldEvent");
export const PlayerPlaceBlockEvent = of("PlayerPlaceBlockEvent");