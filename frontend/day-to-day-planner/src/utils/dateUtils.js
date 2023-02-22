export function getFormattedDate(date) {
    return date.toLocaleDateString('zh-Hans-CN', {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
    }).replaceAll("/", "-")
}