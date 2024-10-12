import service from "@/utils/request";

export function getExamListService(params) {
    return service({
        url: "/exam/list",
        method: "get",
        params,
    });
}

export function delExamService(id) {
    return service({
        url: "/exam/delete",
        method: "delete",
        params: { id },
    });
}

export function publishExamService(id) {
    return service({
        url: "/exam/publish",
        method: "put",
        params: { id },
    });
}

export function cancelPublishExamService(id) {
    return service({
        url: "/exam/cancel",
        method: "put",
        params: { id },
    });
}

export function addExamService(params = {}) {
    return service({
        url: "/exam/addExam",
        method: "post",
        data: params,
    });
}

export function addExamQuestionService(params = {}) {
    return service({
        url: "/exam/addQuestion",
        method: "post",
        data: params,
    });
}

export function searchExamService(id) {
    return service({
        url: "/exam/cancel",
        method: "put",
        params: { id },
    });
}

export function updateExamService(id) {
    return service({
        url: "/exam/cancel",
        method: "put",
        params: { id },
    });
}

export function delExamQuestionService(id) {
    return service({
        url: "/exam/cancel",
        method: "put",
        params: { id },
    });
}
