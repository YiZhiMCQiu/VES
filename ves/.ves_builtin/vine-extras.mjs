// const VineDeobfMapper = Java.type("cn.yizhimcqiu.vine.deobf.VineDeobfMapper");
//
// export function type(name) {
//     let clazz = Java.type(name);
//     let obj = {
//         __class__: name
//     }
//     for (let key in clazz) {
//         obj["prop_"+key] = clazz[key];
//     }
//     console.log(Object.keys(obj));
// }