const VineDeobfMapper = Java.type("cn.yizhimcqiu.vine.deobf.VineDeobfMapper");

export function type(name) {
    console.dir(Java.type(VineDeobfMapper.mapClassName(name)))
}