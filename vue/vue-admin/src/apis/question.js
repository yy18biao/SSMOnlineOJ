import service from "@/utils/request";

export function getQuestionListService(params) {
    return service({
        url: "/question/list",
        method: "get",
        params,
    });
}

export function addQuestionService(params = {}) {
    return service({
        url: "/question/add",
        method: "post",
        data: params,
    });
}

export function searchQuestionService(id) {
    return service({
        url: "/question/search",
        method: "get",
        params: { id },
    });
}

export function updateQuestionService(params = {}) {
    return service({
        url: "/question/update",
        method: "put",
        data: params,
    });
}

export function delQuestionService(id) {
    return service({
        url: "/question/delete",
        method: "delete",
        params: { id },
    });
}