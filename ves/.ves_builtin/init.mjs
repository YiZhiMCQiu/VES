export var logger = console;
var _default_info = console.info;
logger.info = (text) => {
    if (typeof text == "object") {
        text = JSON.stringify(text);
    }
    _default_info("._"+text);
}
console = undefined;

const VERSION = "0.0.2"