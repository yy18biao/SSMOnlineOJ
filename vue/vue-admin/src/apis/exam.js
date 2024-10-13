import service from "@/utils/request";

export function getExamListService(params) {
    return service({
        url: "/exam/list",
        method: "get",
        params,
    });
}

export function delExamService(examId) {
    return service({
        url: "/exam/delete",
        method: "delete",
        params: { examId },
    });
}

export function publishExamService(examId) {
    return service({
        url: "/exam/publishExam",
        method: "put",
        params: { examId },
    });
}

export function cancelPublishExamService(examId) {
    return service({
        url: "/exam/cancelExam",
        method: "put",
        params: { examId },
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

export function searchExamService(examId) {
    return service({
        url: "/exam/search",
        method: "get",
        params: { examId },
    });
}

export function updateExamService(examId) {
    return service({
        url: "/exam/cancel",
        method: "put",
        params: { examId },
    });
}

export function delExamQuestionService(examId, questionId) {
    return service({
        url: "/exam/deleteQuestion",
        method: "delete",
        params: { examId, questionId },
    });
}
