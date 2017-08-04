/**
 * 分页提交
 * @param pageNo
 */
function submitPage(pageNo) {
    var form = $("#pageForm");
    var currentPage = $("#currentPage");
    if (form != null && currentPage != null) {
        currentPage.val(pageNo);
        form.submit();
        return;
    }
}

/**
 * 删除函数
 * @param elementId
 * @param url
 * @param title
 */
function del(elementId, url, id) {
    if (!confirm('是否删除编号为' + id + '的数据？')) {
        return;
    }
    $("#" + elementId).attr("action", url);
    $('#' + elementId).submit();
}
